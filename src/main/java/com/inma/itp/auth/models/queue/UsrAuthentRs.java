package com.inma.itp.auth.models.queue;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.inma.itp.config.models.queues.QueueResMsg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement(name = "UsrAuthentRs")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsrAuthentRs extends QueueResMsg{

	@XmlPath("Body/OneTmPswdFlg/text()")
	private String oneTmPswdFlg;

	@XmlPath("Body/BankUsrInfo/LastLoginTmstmp/text()")
	private String lastLoginTmstmp;

	@XmlPath("Body/BankUsrInfo/DeptCode/text()")
	private String deptCode;

	@XmlPath("Body/BankUsrInfo/RoleInfo")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<RoleInfo> roles;

	@XmlPath("Body/BankUsrInfo/UsrId/text()")
	private String usrId;

	@XmlPath("Body/BankUsrInfo/LangPref/text()")
	private String langPref;

	@XmlPath("Body/BankUsrInfo/NumOfFailedLogins/text()")
	private String numOfFailedLogins;

	@Data
	@NoArgsConstructor
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class RoleInfo {
		@XmlElement(name = "RoleId")
		private String roleId;

	}

}
