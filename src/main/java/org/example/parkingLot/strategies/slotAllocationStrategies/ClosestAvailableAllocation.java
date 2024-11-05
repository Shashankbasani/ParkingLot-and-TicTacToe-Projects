package org.example.parkingLot.strategies.slotAllocationStrategies;

import org.example.parkingLot.models.Gate;
import org.example.parkingLot.models.ParkingFloor;
import org.example.parkingLot.models.ParkingLot;
import org.example.parkingLot.models.ParkingSlot;
import org.example.parkingLot.models.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClosestAvailableAllocation implements SlotAllocationStrategy{
    @Override
    public ParkingSlot getParkingSlot(ParkingLot parkingLot, VehicleType vehicleType, Gate gate) {
        List<ParkingSlot> emptySlots = new ArrayList<>();
        for(ParkingFloor floor : parkingLot.getParkingFloors()) {
            for(ParkingSlot slot: floor.getParkingSlotList()){
                if(slot.getVehicle() == null && slot.getVehicleType() == vehicleType){
                    emptySlots.add(slot);
                }
            }
        }
        if(emptySlots.size() == 0){
            return null;
        }
        return emptySlots.get(0);
    }
}
