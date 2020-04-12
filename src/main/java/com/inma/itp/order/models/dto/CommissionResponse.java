package com.inma.itp.order.models.dto;

import lombok.Data;

@Data
public class CommissionResponse {
	
	private String bankCommVATPercentage;

	private String totalComm;

	private String totTradeAmt;

	private String bankComm;

	private String bankCommVAT;

	private String exchangeFees;
    
}
