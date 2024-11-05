package org.example.parkingLot.dtos;

import org.example.parkingLot.dtos.enums.ResponseStatus;

public class IssueTicketResponseDTO {
    private String ticketNumber;

    private String responseMessage;
    private ResponseStatus responseStatus;

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
