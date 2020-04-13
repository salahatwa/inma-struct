package com.inma.itp.config.model.bussinessObject;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.utils.GenerateShortUUID;

import lombok.Data;

@Data
public class QueueReqMsg {

	@XmlPath("MsgRqHdr/RqUID/text()")
	private String rqUID;

	@XmlPath("MsgRqHdr/SCId/text()")
	private String scid;

	@XmlPath("MsgRqHdr/FuncId/text()")
	private String funcId;

	@XmlPath("MsgRqHdr/AgentId/text()")
	private String agentId;

	@XmlPath("MsgRqHdr/CustId/POI/POINum/text()")
	private String poiNum;

	@XmlPath("MsgRqHdr/CustId/POI/POIType/text()")
	private String poiType;

	public QueueReqMsg() {
		this.rqUID = GenerateShortUUID.id();
		this.scid = GenerateShortUUID.getChannel();
	}

	public QueueReqMsg(String fucId) {
		super();
		this.funcId = fucId;
	}
}
