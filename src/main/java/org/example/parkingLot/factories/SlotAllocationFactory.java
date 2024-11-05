package org.example.parkingLot.factories;

import org.example.parkingLot.models.ParkingLot;
import org.example.parkingLot.models.enums.SlotAllocationStrategyType;
import org.example.parkingLot.strategies.slotAllocationStrategies.ClosestAvailableAllocation;
import org.example.parkingLot.strategies.slotAllocationStrategies.FarthestAvailableAllocation;
import org.example.parkingLot.strategies.slotAllocationStrategies.RandomSlotAllocation;
import org.example.parkingLot.strategies.slotAllocationStrategies.SlotAllocationStrategy;

public class SlotAllocationFactory {
    public static SlotAllocationStrategy getSlotAllocationStrategy(SlotAllocationStrategyType type) {
        if(type == SlotAllocationStrategyType.RANDOM){
            return new RandomSlotAllocation();
        }
        if(type == SlotAllocationStrategyType.NEAREST_TO_GATE){
            return new ClosestAvailableAllocation();
        }
        return new FarthestAvailableAllocation();
    }
}
