package org.example.parkingLot.repositories;

import org.example.parkingLot.exceptions.GateNotFoundException;
import org.example.parkingLot.models.Gate;
import org.example.parkingLot.models.ParkingLot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParkingLotRepository {
    private Map<Long, ParkingLot> parkingLotMap;
    private long counter;

    public ParkingLotRepository() {
        parkingLotMap = new HashMap<>();
        counter = 0;
    }

    public ParkingLot save(ParkingLot parkingLot) {
        counter++;
        parkingLot.setId(counter);
        parkingLotMap.put(parkingLot.getId(), parkingLot);
        return parkingLot;
    }

    public Optional<ParkingLot> getByGate(Gate gate) throws Exception {
        if(gate == null){
            throw new GateNotFoundException("Gate cannot be null");
        }
        for(ParkingLot parkingLot : parkingLotMap.values()) {
            List<Gate> gatesInThisParkingLot = parkingLot.getGates();
            for(Gate eachGate: gatesInThisParkingLot){
                if(eachGate.getId() == gate.getId()){
                    return Optional.of(parkingLot);
                }
            }
        }
        return Optional.empty();
    }
}
