package com.inma.itp.portfolio.model.bussinessObject;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.annotations.InmaQueue;
import com.inma.itp.config.model.bussinessObject.QueueReqMsg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "eTradeCustPortfoliosInqRq")
@InmaQueue(requestQueue = "eTradeCustPortfoliosInqRq", responseQueue = "eTradeCustPortfoliosInqRs")
public class ETradeCustPortfoliosInqRq extends QueueReqMsg {

	@XmlPath("Body/PortfolioType/text()")
	private String portfolioType;

	@XmlPath("Body/PortfolioNum/text()")
	private String portfolioNum;

	@XmlPath("Body/Symbol/text()")
	private String symbol;

	public ETradeCustPortfoliosInqRq(String funcId, String agentId, String poiNum) {
		super(funcId);
		this.setAgentId(agentId);
		this.setPoiNum(poiNum);
	}

}
