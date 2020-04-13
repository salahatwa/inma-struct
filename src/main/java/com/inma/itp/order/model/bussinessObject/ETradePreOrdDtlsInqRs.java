package com.inma.itp.order.model.bussinessObject;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.inma.itp.config.model.bussinessObject.QueueResMsg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "eTradePreOrdDtlsInqRs")
public class ETradePreOrdDtlsInqRs extends QueueResMsg {

	@XmlPath("Body/BankCommVATPercentage/text()")
	private String bankCommVATPercentage;

	@XmlPath("Body/TotalComm/text()")
	private String totalComm;

	@XmlPath("Body/TotTradeAmt/text()")
	private String totTradeAmt;

	@XmlPath("Body/BankComm/text()")
	private String bankComm;

	@XmlPath("Body/BankCommVAT/text()")
	private String bankCommVAT;

	@XmlPath("Body/ExchangeFees/text()")
	private String exchangeFees;
}
