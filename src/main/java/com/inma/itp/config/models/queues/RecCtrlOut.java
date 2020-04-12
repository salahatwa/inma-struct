package com.inma.itp.config.models.queues;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecCtrlOut {
	@XmlElement(name = "MatchedRecs")
	private String matchedRecs;

	@XmlElement(name = "SentRecs")
	private String sentRecs;
}
