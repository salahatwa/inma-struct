package com.inma.itp.portfolio.models.queue;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.inma.itp.config.models.queues.AcctId;
import com.inma.itp.config.models.queues.QueueResMsg;
import com.inma.itp.config.models.queues.RecCtrlOut;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement(name = "eTradeCustPortfoliosInqRs")
public class ETradeCustPortfoliosInqRs extends QueueResMsg {

	@XmlPath("Body/RecCtrlOut")
	private RecCtrlOut recCtrlOut = new RecCtrlOut();

	@XmlPath("Body/PortfoliosList/PortfolioDtls")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<PortfolioDtls> portfolioDtls = new ArrayList<>();

	@Data
	@NoArgsConstructor
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class PortfolioDtls {

		@XmlElement(name = "AcctId")
		private AcctId acctId = new AcctId();

		@XmlElement(name = "ExcBuyAmt")
		private String excBuyAmt;

		@XmlElement(name = "POINum")
		private String poiNum;

		@XmlElement(name = "BuyingPwr")
		private String buyingPwr;

		@XmlElement(name = "TotalMrktVal")
		private String totalMrktVal;

		@XmlElement(name = "PortfolioPositionAmt")
		private String portfolioPositionAmt;

		@XmlElement(name = "PortfolioNum")
		private String portfolioNum;

		@XmlElement(name = "PortfolioType")
		private String portfolioType;

		@XmlElement(name = "SAMAAcctNum")
		private String samaAcctNum;

		@XmlElement(name = "MobileNum")
		private String mobileNum;

		@XmlElement(name = "DiscountRate")
		private String discountRate;

		@XmlElement(name = "TotalCost")
		private String totalCost;

		@XmlElement(name = "ExcSellAmt")
		private String excSellAmt;

		@XmlElement(name = "TotalRealizedProfitLoss")
		private String totalRealizedProfitLoss;

		@XmlPath("Body/PortfoliosList/PortfolioDtls/TradeSecsList/TradeSec")
		@JacksonXmlElementWrapper(useWrapping = false)
		private List<TradeSec> tradeSec = new ArrayList<>();

		@XmlElement(name = "AvailBal")
		private String availBal;

		@XmlElement(name = "OutstandBuyAmt")
		private String outstandBuyAmt;

		@XmlElement(name = "TotalUnrealizedProfitLoss")
		private String totalUnrealizedProfitLoss;

		@XmlElement(name = "OutstandSellAmt")
		private String outstandSellAmt;

		@XmlElement(name = "PortfolioName")
		private String portfolioName;

		@XmlElement(name = "MarginLimit")
		private String marginLimit;

		@Data
		@NoArgsConstructor
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class TradeSec {

			@XmlElement(name = "OutstandBuyQuantity")
			private String outstandBuyQuantity;

			@XmlElement(name = "PledgedQuantity")
			private String pledgedQuantity;

			@XmlElement(name = "SecEnName")
			private String secEnName;

			@XmlElement(name = "UnrealizedProfitLossPercen")
			private String unrealizedProfitLossPercen;

			@XmlElement(name = "OutstandSellQuantity")
			private String outstandSellQuantity;

			@XmlElement(name = "TotalMrktVal")
			private String totalMrktVal;

			@XmlElement(name = "Symbol")
			private String symbol;

			@XmlElement(name = "SecArName")
			private String secArName;

			@XmlElement(name = "OwnedQuantity")
			private String ownedQuantity;

			@XmlElement(name = "TotalCost")
			private String totalCost;

			@XmlElement(name = "AvgCostPrice")
			private String avgCostPrice;

			@XmlElement(name = "AvailQuantity")
			private String availQuantity;

			@XmlElement(name = "UnrealizedProfitLoss")
			private String unrealizedProfitLoss;

			@XmlElement(name = "RealizedProfitLossPercen")
			private String realizedProfitLossPercen;

			@XmlElement(name = "UnsettledBuyQuantity")
			private String unsettledBuyQuantity;

			@XmlElement(name = "RealizedProfitLoss")
			private String realizedProfitLoss;

			@XmlElement(name = "MrktPrice")
			private String mrktPrice;

			@XmlElement(name = "UnsettledSellQuantity")
			private String unsettledSellQuantity;
		}

	}
}
