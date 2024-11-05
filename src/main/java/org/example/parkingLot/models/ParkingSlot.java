package org.example.parkingLot.models;


import org.example.parkingLot.models.enums.ParkingSlotStatus;
import org.example.parkingLot.models.enums.VehicleType;

public class ParkingSlot extends BaseModel{
    private int slotNumber;
    private ParkingSlotStatus parkingSlotStatus;
    private VehicleType vehicleType;
    private Vehicle vehicle;

    public ParkingSlot(int number, VehicleType vehicleType){
        this.slotNumber = number;
        this.parkingSlotStatus = ParkingSlotStatus.OPEN;
        this.vehicleType = vehicleType;
        this.vehicle = null;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public ParkingSlotStatus getParkingSlotStatus() {
        return parkingSlotStatus;
    }

    public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
        this.parkingSlotStatus = parkingSlotStatus;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
