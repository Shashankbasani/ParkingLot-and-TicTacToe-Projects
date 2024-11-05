package org.example.parkingLot.strategies.slotAllocationStrategies;

import org.example.parkingLot.models.Gate;
import org.example.parkingLot.models.ParkingLot;
import org.example.parkingLot.models.ParkingSlot;
import org.example.parkingLot.models.enums.VehicleType;

public interface SlotAllocationStrategy {
    ParkingSlot getParkingSlot(
                        ParkingLot parkingLot,
                        VehicleType vehicleType,
                        Gate gate
                );
}
