package com.inma.itp.order.models.queue;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.annotations.InmaQueue;
import com.inma.itp.config.models.queues.DtRange;
import com.inma.itp.config.models.queues.QueueReqMsg;
import com.inma.itp.config.models.queues.RecCtrlIn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "eTradeOrdsInqRq")
@InmaQueue(requestQueue = "eTradeOrdsInqRq", responseQueue = "eTradeOrdsInqRs")
public class ETradeOrdsInqRq extends QueueReqMsg {

	public ETradeOrdsInqRq(String funcId, String agentId) {
		super(funcId);
		this.setAgentId(agentId);
	}

	@XmlPath("Body/OrdSide/text()")
	private String ordSide;

	@XmlPath("Body/OrdStatus/text()")
	private String ordStatus;

	@XmlPath("Body/Symbol/text()")
	private String symbol;

	@XmlPath("Body/OMSRefNum/text()")
	private String omsRefNum;

	@XmlPath("Body/OwnOrder/text()")
	private String ownOrder;

	@XmlPath("Body/DtRange")
	private DtRange dtRange = new DtRange();

	@XmlPath("Body/RecCtrlIn")
	private RecCtrlIn recCtrlIn = new RecCtrlIn();

	@XmlPath("Body/PortfolioNum/text()")
	private String portfolioNum;

	@XmlPath("Body/Product/text()")
	private String product;

}
