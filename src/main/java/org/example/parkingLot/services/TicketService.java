package org.example.parkingLot.services;

import org.example.parkingLot.exceptions.GateNotFoundException;
import org.example.parkingLot.exceptions.ParkingLotNotFound;
import org.example.parkingLot.factories.SlotAllocationFactory;
import org.example.parkingLot.models.*;
import org.example.parkingLot.models.enums.SlotAllocationStrategyType;
import org.example.parkingLot.models.enums.VehicleType;
import org.example.parkingLot.repositories.GateRepository;
import org.example.parkingLot.repositories.ParkingLotRepository;
import org.example.parkingLot.repositories.TicketRepository;
import org.example.parkingLot.repositories.VehicleRepository;
import org.example.parkingLot.strategies.slotAllocationStrategies.SlotAllocationStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    TicketRepository ticketRepository;
    GateRepository gateRepository;
    VehicleRepository vehicleRepository;
    ParkingLotRepository parkingLotRepository;

    public TicketService(
            TicketRepository ticketRepository,
            GateRepository gateRepository,
            VehicleRepository vehicleRepository,
            ParkingLotRepository parkingLotRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket issueTicket(
            String vehicleNumber,
            VehicleType vehicleType,
            String ownerName,
            long gateId
    ) throws Exception {
        Ticket ticket = new Ticket();

        // set the attributes one by one
        ticket.setEntryTime(new Date());

        Optional<Gate> optionalGate = gateRepository.get(gateId);
        if(optionalGate.isEmpty()) {
            throw new GateNotFoundException("Gate Not Found");
        }
        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);

        ticket.setGeneratedBy(gate.getCurrentOperator());

        Optional<Vehicle> optionalVehicle = vehicleRepository.getByNumber(vehicleNumber);
        Vehicle savedVehicle;
        if(optionalVehicle.isEmpty()) {
            // save the vehicle!
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setNameOfOwner(ownerName);

            savedVehicle = vehicleRepository.save(vehicle);
        } else {
            savedVehicle = optionalVehicle.get();
        }
        ticket.setVehicle(savedVehicle);

        // get the assigned parking slot
        // 1. find the corresponding ParkingLot
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.getByGate(gate);
        if(parkingLotOptional.isEmpty()) {
            throw new ParkingLotNotFound("Parking lot not found");
        }
        ParkingLot parkingLot = parkingLotOptional.get();

        // 2. in this lot, there is slotAllocationStrategyType
        SlotAllocationStrategyType slotAllocationStrategyType =
                parkingLot.getSlotAllocationStrategyType();

        // 3. given this type, get the actual implementation
        SlotAllocationStrategy slotAllocationStrategy =
                SlotAllocationFactory.getSlotAllocationStrategy(slotAllocationStrategyType);

        // 4. from this implementation, get the actual slot.
        ParkingSlot parkingSlot = slotAllocationStrategy.getParkingSlot(parkingLot, vehicleType, gate);
        if(parkingSlot == null) {
            throw new Exception("Parking lot is full, cannot generate ticket");
        }
        ticket.setParkingSlot(parkingSlot);

        Ticket updatedTicket = ticketRepository.save(ticket);   // to get the actual id of the ticket
        updatedTicket.setTicketNumber("TicketID_" + ticket.getId());
        return updatedTicket;
    }
}
