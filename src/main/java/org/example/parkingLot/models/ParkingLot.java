package org.example.parkingLot.models;


import org.example.parkingLot.models.enums.FeeCalculationStrategyType;
import org.example.parkingLot.models.enums.ParkingLotStatus;
import org.example.parkingLot.models.enums.SlotAllocationStrategyType;
import org.example.parkingLot.models.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

// can use Builder.
// do it as exercise.
public class ParkingLot extends BaseModel{
    private String address;
    private List<ParkingFloor> parkingFloors;
    private List<Gate> gates;
    private List<VehicleType> allowedVehicleTypes;
    private ParkingLotStatus parkingLotStatus;
    private FeeCalculationStrategyType feeCalculationStrategyType;
    private SlotAllocationStrategyType slotAllocationStrategyType;

    public ParkingLot() {
        this.address = "";
        this.parkingFloors = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.allowedVehicleTypes = new ArrayList<>();
        this.parkingLotStatus = ParkingLotStatus.OPEN;
        this.feeCalculationStrategyType = FeeCalculationStrategyType.HOURLY;
        this.slotAllocationStrategyType = SlotAllocationStrategyType.RANDOM;
    }

    public SlotAllocationStrategyType getSlotAllocationStrategyType() {
        return slotAllocationStrategyType;
    }

    public void setSlotAllocationStrategyType(SlotAllocationStrategyType slotAllocationStrategyType) {
        this.slotAllocationStrategyType = slotAllocationStrategyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public List<VehicleType> getAllowedVehicleTypes() {
        return allowedVehicleTypes;
    }

    public void setAllowedVehicleTypes(List<VehicleType> allowedVehicleTypes) {
        this.allowedVehicleTypes = allowedVehicleTypes;
    }

    public ParkingLotStatus getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }
}
