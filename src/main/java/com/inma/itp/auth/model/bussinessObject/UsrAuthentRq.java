package com.inma.itp.auth.model.bussinessObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.annotations.InmaQueue;
import com.inma.itp.config.model.bussinessObject.QueueReqMsg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "UsrAuthentRq")
@XmlAccessorType(XmlAccessType.FIELD)
@InmaQueue(requestQueue = "UsrAuthentRq", responseQueue = "UsrAuthentRs")
public class UsrAuthentRq extends QueueReqMsg {

	@XmlPath("Body/Sec/Info/text()")
	private String info;

	@XmlPath("Body/Sec/InfoType/text()")
	private String infoType;

	@XmlPath("Body/LoginId/LoginAttribVal/text()")
	private String loginAttribVal;

	@XmlPath("Body/LoginId/LoginAttribType/text()")
	private String loginAttribType;

	public UsrAuthentRq(String funcId) {
		super(funcId);
	}

}
