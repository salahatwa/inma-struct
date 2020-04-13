package com.inma.itp.stock.model.dto;

import lombok.Data;

@Data
public class StockDetails {
	
	private String symbol;

	private String mrktCode;

	private String prevClosure;

	private String bestBidPrice;

	private String openPrice;

	private String bestAskQty;

	private String secEnName;

	private String loserAlert;

	private String maxPriceLimit;

	private String tradableRightsAlert;

	private String tradableRights;

	private String bestBidQty;

	private String priceChangeRate;

	private String minPriceLimit;

	private String riskRate;

	private String mlenabled;

	private String tradedVol;

	private String tradable;

	private String priceChangeAmt;

	private String settlementDays;

	private String bestAskPrice;

	private String secArName;

	private String loserCompany;

	private String lastPrice;
}
