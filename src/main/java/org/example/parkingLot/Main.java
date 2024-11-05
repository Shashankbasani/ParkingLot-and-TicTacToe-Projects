package org.example.parkingLot;

import org.example.parkingLot.controllers.TicketController;
import org.example.parkingLot.dtos.IssueTicketRequestDTO;
import org.example.parkingLot.dtos.IssueTicketResponseDTO;
import org.example.parkingLot.models.*;
import org.example.parkingLot.models.enums.GateType;
import org.example.parkingLot.models.enums.SlotAllocationStrategyType;
import org.example.parkingLot.models.enums.VehicleType;
import org.example.parkingLot.repositories.GateRepository;
import org.example.parkingLot.repositories.ParkingLotRepository;
import org.example.parkingLot.repositories.TicketRepository;
import org.example.parkingLot.repositories.VehicleRepository;
import org.example.parkingLot.services.TicketService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Dependencies
        TicketRepository ticketRepository = new TicketRepository();
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(ticketRepository, gateRepository, vehicleRepository, parkingLotRepository);

        TicketController ticketController = new TicketController(ticketService);

        // populate the data
        initialiseDatabase(gateRepository, parkingLotRepository);



        // 1st Request
        IssueTicketRequestDTO requestDTO = new IssueTicketRequestDTO();
        requestDTO.setOwnerName("Saharsh");
        requestDTO.setVehicleNumber("MH03AZ7775");
        requestDTO.setVehicleType(VehicleType.CAR);
        requestDTO.setGateId(1);

        // call the controller and get a response back
        IssueTicketResponseDTO responseDTO = ticketController.issueTicket(requestDTO);

        // deal with the response
        System.out.println(responseDTO.getTicketNumber());
        System.out.println(responseDTO.getResponseMessage());

        // 2nd request
        // Request
        requestDTO = new IssueTicketRequestDTO();
        requestDTO.setOwnerName("Saubhagya");
        requestDTO.setVehicleNumber("MH03AZ8899");
        requestDTO.setVehicleType(VehicleType.CAR);
        requestDTO.setGateId(1);

        // call the controller and get a response back
        responseDTO = ticketController.issueTicket(requestDTO);

        // deal with the response
        System.out.println(responseDTO.getTicketNumber());
        System.out.println(responseDTO.getResponseMessage());
    }

    private static void initialiseDatabase(GateRepository gateRepository, ParkingLotRepository parkingLotRepository){
        Operator entryGateOperator = new Operator("111", "op1");
        Operator exitGateOperator = new Operator("222", "op2");

        Gate gate1 = new Gate(1, entryGateOperator, GateType.ENTRY_GATE);
        Gate gate2 = new Gate(2, exitGateOperator, GateType.EXIT_GATE);

        List<Gate> gateList = new ArrayList<>();
        gateList.add(gate1);
        gateList.add(gate2);

        gateRepository.save(gate1);
        gateRepository.save(gate2);

        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.getParkingSlotList().add(new ParkingSlot(101, VehicleType.CAR));
        floor1.getParkingSlotList().add(new ParkingSlot(102, VehicleType.CAR));
        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.getParkingSlotList().add(new ParkingSlot(201, VehicleType.BIKE));
        floor2.getParkingSlotList().add(new ParkingSlot(202, VehicleType.BIKE));
        floor2.getParkingSlotList().add(new ParkingSlot(203, VehicleType.BIKE));
        ParkingFloor floor3 = new ParkingFloor(3);
        floor3.getParkingSlotList().add(new ParkingSlot(301, VehicleType.CAR));

        List<ParkingFloor> parkingFloorsList = new ArrayList<>();
        parkingFloorsList.add(floor1);
        parkingFloorsList.add(floor2);
        parkingFloorsList.add(floor3);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setGates(gateList);
        parkingLot.setSlotAllocationStrategyType(SlotAllocationStrategyType.NEAREST_TO_GATE);
        parkingLot.setParkingFloors(parkingFloorsList);

        parkingLotRepository.save(parkingLot);
    }
}
