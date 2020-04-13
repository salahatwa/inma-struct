package com.inma.itp.config.model.bussinessObject;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
public class RecCtrlIn {
	@XmlElement(name = "MaxRecs")
	private String maxRecs;

	@XmlElement(name = "Offset")
	private String offset;
}
