package com.inma.itp.portfolio.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PortfolioDetails {

	private String discountRate;

	private String totalUnrealizedProfitLoss;

	private String samaAcctNum;

	private String excSellAmt;

	private AcctId acctId=new AcctId();

	private String excBuyAmt;

	private String availBal;

	private String outstandSellAmt;

	private String totalMrktVal;

	private String mobileNum;

	private String portfolioNum;

	private TradeSecsList tradeSecsList=new TradeSecsList();

	private String buyingPwr;

	private String poiNum;

	private String totalRealizedProfitLoss;

	private String portfolioPositionAmt;

	private String portfolioName;

	private String totalCost;

	private String outstandBuyAmt;
	
	@Data
	public static class AcctId
	{
	    private String acctNum;
	}

	@Data
	public static class TradeSecsList {
		private List<TradeSec> tradeSec=new ArrayList<>();

		@Data
		public static class TradeSec {
			private String symbol;

			private String avgCostPrice;

			private String mrktPrice;

			private String availQuantity;

			private String secEnName;

			private String ownedQuantity;

			private String totalMrktVal;

			private String realizedProfitLoss;

			private String realizedProfitLossPercen;

			private String unsettledSellQuantity;

			private String pledgedQuantity;

			private String unrealizedProfitLossPercen;

			private String unsettledBuyQuantity;

			private String outstandBuyQuantity;

			private String outstandSellQuantity;

			private String secArName;

			private String totalCost;

			private String unrealizedProfitLoss;
		}
	}
}
