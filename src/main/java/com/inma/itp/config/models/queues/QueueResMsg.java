package com.inma.itp.config.models.queues;

import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueueResMsg {

	@XmlPath("MsgRsHdr/RqUID/text()")
	private String rqUID;

	@XmlPath("MsgRsHdr/StatusCode/text()")
	private String statusCode;

	@XmlElement(name = "SPRefNum")
	@XmlPath("MsgRsHdr/SPRefNum/text()")
	private String spRefNum;

}
