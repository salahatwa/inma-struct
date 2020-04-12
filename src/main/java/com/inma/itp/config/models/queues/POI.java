package com.inma.itp.config.models.queues;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
public  class POI {
	@XmlElement(name = "POINum")
	private String poiNum;
	
	@XmlElement(name = "POIType")
	private String poiType;
}
