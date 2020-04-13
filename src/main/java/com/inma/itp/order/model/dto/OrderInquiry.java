package com.inma.itp.order.model.dto;

import com.inma.itp.config.model.bussinessObject.DtRange;

import lombok.Data;

@Data
public class OrderInquiry {
	private String ordRefNum;
	private String stockSymbol;

	private String walletNumber;

	/**
	 * SPR for BUY SSL for sell
	 */
	private String orderSide;

	private String ordStatus;

	private DtRange dtRange;

}
