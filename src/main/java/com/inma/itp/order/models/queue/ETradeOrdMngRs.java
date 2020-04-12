package com.inma.itp.order.models.queue;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.models.queues.QueueResMsg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "eTradeOrdMngRs")
public class ETradeOrdMngRs extends QueueResMsg {

	@XmlPath("Body/CustLimit/text()")
	private String custLimit;
}
