package com.inma.itp.order.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommissionRequest {
	
    @NotBlank
    private String stockSymbol;

    @NotBlank
    private String portfolioNum;

    /**
     * SPR for BUY
     * SSL for sell
     */
    @NotBlank
    private String orderSide;
    
    private int orderQty;

    /**
     * 
     */
    @NotBlank
    private int orderType;
    
    @NotBlank
    private double unitPrice;
    
}
