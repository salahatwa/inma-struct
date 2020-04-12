package com.inma.itp.config.models.queues;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
public class MsgRsHdr {
	@XmlElement(name = "RqUID")
	private String rqUID;

	@XmlElement(name = "StatusCode")
	private String statusCode;

	@XmlElement(name = "SPRefNum")
	private String spRefNum;
}
