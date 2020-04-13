package com.inma.itp.order.model.bussinessObject;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.annotations.InmaQueue;
import com.inma.itp.config.model.bussinessObject.QueueReqMsg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "eTradePreOrdDtlsInqRq")
@InmaQueue(requestQueue = "eTradePreOrdDtlsInqRq", responseQueue = "eTradePreOrdDtlsInqRs")
public class ETradePreOrdDtlsInqRq extends QueueReqMsg {

	@XmlPath("Body/OrdQty/text()")
	private String ordQty;

	@XmlPath("Body/UnitPrice/text()")
	private String unitPrice;

	@XmlPath("Body/OrdSide/text()")
	private String ordSide;

	@XmlPath("Body/Symbol/text()")
	private String symbol;

	@XmlPath("Body/OrdType/text()")
	private String ordType;

	@XmlPath("Body/PortfolioNum/text()")
	private String portfolioNum;

	public ETradePreOrdDtlsInqRq(String funcId, String agentId) {
		super(funcId);
		this.setAgentId(agentId);
	}
}
