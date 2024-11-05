package org.example.parkingLot.repositories;

import org.example.parkingLot.models.Gate;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {
    private static Map<Long, Gate> gateMap;
    private long counter;

    public GateRepository() {
        this.gateMap = new HashMap<>();
        this.counter = 0;
    }

    public Gate save(Gate gate){
        counter++;
        gate.setId(counter);
        gateMap.put(counter, gate);
        return gate;
    }

    public Optional<Gate> get(long id){
        if(gateMap.containsKey(id)){
            return Optional.of(gateMap.get(id));
        }
        return Optional.empty();
    }
}
