package org.example.parkingLot.controllers;

import org.example.parkingLot.dtos.IssueTicketRequestDTO;
import org.example.parkingLot.dtos.IssueTicketResponseDTO;
import org.example.parkingLot.dtos.enums.ResponseStatus;
import org.example.parkingLot.models.Ticket;
import org.example.parkingLot.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO) {
        IssueTicketResponseDTO responseDTO = new IssueTicketResponseDTO();
        try{
            Ticket ticket = ticketService.issueTicket(
                                    requestDTO.getVehicleNumber(),
                                    requestDTO.getVehicleType(),
                                    requestDTO.getOwnerName(),
                                    requestDTO.getGateId()
                            );

            responseDTO.setTicketNumber(ticket.getTicketNumber());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setResponseMessage("Ticket successfully generated");

        } catch (Exception e){
            responseDTO.setTicketNumber(null);
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setResponseMessage(e.getMessage());
        }

        return responseDTO;
    }
}
