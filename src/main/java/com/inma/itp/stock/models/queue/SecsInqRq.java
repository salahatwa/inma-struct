package com.inma.itp.stock.models.queue;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.annotations.InmaQueue;
import com.inma.itp.config.models.queues.QueueReqMsg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "SecsInqRq")
@InmaQueue(requestQueue = "SecsInqRq", responseQueue = "SecsInqRs")
public class SecsInqRq extends QueueReqMsg {

	public SecsInqRq(String funcId, String agentId) {
		super(funcId);
		this.setAgentId(agentId);
	}

	@XmlPath("Body/ProductCode/text()")
	private String productCode;

	@XmlPath("Body/Symbol/text()")
	private String symbol;
}
