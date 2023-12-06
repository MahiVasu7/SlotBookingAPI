package com.example.RestApplication.Entities;

import org.antlr.v4.runtime.misc.NotNull;

public class BookingRequest {
	@NotNull
    private Long consultantId;
	@NotNull
    private String date;
	@NotNull
    private Long clientId;
	
	public Long getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

    // Getters and setters
    
}