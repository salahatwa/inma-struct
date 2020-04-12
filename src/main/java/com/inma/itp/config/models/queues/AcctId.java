package com.inma.itp.config.models.queues;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
public class AcctId {
	@XmlElement(name = "AcctNum")
	private String acctNum;

	@XmlElement(name = "AcctType")
	private String acctType;
}