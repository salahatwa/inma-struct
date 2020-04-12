package com.inma.itp.config.models.queues;

import javax.xml.bind.annotation.XmlElement;

import com.inma.itp.config.utils.GenerateShortUUID;

import lombok.Data;

@Data
public class MsgRqHdr {

	@XmlElement(name = "RqUID")
	private String rqUID;

	@XmlElement(name = "SCId")
	private String scid;
	
	@XmlElement(name = "FuncId")
	private String funcId;
	
	@XmlElement(name = "AgentId")
	private String agentId;

	@XmlElement(name = "CustId")
	private CustId custId;
	
	public MsgRqHdr()
	{
		this.rqUID="ITP"+GenerateShortUUID.id();
		this.scid="ITP";
	}

	@Data
	public static class CustId {

		@XmlElement(name = "POI")
		private POI poi = new POI();

	}

}
