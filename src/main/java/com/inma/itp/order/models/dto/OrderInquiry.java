package com.inma.itp.order.models.dto;

import com.inma.itp.config.models.queues.DtRange;

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
