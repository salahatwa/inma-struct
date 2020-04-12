package com.inma.itp.config.models.queues;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.utils.GenerateShortUUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

	public QueueReqMsg(String fucId) {
		this.rqUID = "ITP" + GenerateShortUUID.id();
		this.scid = "ITP";
		this.funcId=fucId;
	}
}
