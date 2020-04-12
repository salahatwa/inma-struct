package com.inma.itp.config.models.queues;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
public class CurAmt
{
	@XmlElement(name = "CurCode")
    private String curCode;

	@XmlElement(name = "Amt")
    private String Amt;
}