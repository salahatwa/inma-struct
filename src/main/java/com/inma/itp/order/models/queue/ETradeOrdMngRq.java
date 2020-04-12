package com.inma.itp.order.models.queue;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.annotations.InmaQueue;
import com.inma.itp.config.models.queues.CurAmt;
import com.inma.itp.config.models.queues.QueueReqMsg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "eTradeOrdMngRq")
@InmaQueue(requestQueue = "eTradeOrdMngRq", responseQueue = "eTradeOrdMngRs")
public class ETradeOrdMngRq extends QueueReqMsg {

	public ETradeOrdMngRq(String funcId, String agentId) {
		super(funcId);
		this.setAgentId(agentId);
	}

	@XmlPath("Body/eTradeOrdDtls/OrdQty/text()")
	private String ordQty;

	@XmlPath("Body/eTradeOrdDtls/MaxFloor/text()")
	private String maxFloor;

	@XmlPath("Body/eTradeOrdDtls/CurAmt/text()")
	private CurAmt curAmt = new CurAmt();

	@XmlPath("Body/eTradeOrdDtls/TIFType/text()")
	private String tifType;

	@XmlPath("Body/eTradeOrdDtls/OrdSide/text()")
	private String ordSide;

	@XmlPath("Body/eTradeOrdDtls/Symbol/text()")
	private String symbol;

	@XmlPath("Body/eTradeOrdDtls/OrdType/text()")
	private String ordType;

	@XmlPath("Body/eTradeOrdDtls/PortfolioNum/text()")
	private String portfolioNum;

	@XmlPath("Body/eTradeOrdDtls/Product/text()")
	private String product;

	@XmlPath("Body/eTradeOrdDtls/ExpDt/text()")
	private String expDt;

	@XmlPath("Body/eTradeOrdDtls/MinFillQty/text()")
	private String minFillQty;

}
