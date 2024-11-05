package org.example.parkingLot.repositories;

import org.example.parkingLot.models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Long, Ticket> ticketMap;
    private long counter = 0;

    public TicketRepository() {
        this.ticketMap = new HashMap<>();
        counter = 0;
    }

    public Ticket save(Ticket ticket) {
        counter++;
        ticket.setId(counter);
        ticketMap.put(counter, ticket);
        return ticket;
    }
}
