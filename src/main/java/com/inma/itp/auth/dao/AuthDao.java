package com.inma.itp.auth.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inma.itp.auth.models.queue.UsrAuthentRq;
import com.inma.itp.auth.models.queue.UsrAuthentRs;
import com.inma.itp.auth.models.queue.UsrAuthentRs.RoleInfo;
import com.inma.itp.config.messaging.MessageTemplateService;
import com.inma.itp.config.models.Role;
import com.inma.itp.config.models.User;
import com.inma.itp.config.utils.Constants;

@Service
public class AuthDao {

	@Autowired
	private MessageTemplateService msgTemplateService;

	public User login(UsrAuthentRq rq) {

		rq.setFuncId(Constants.FUNCTION_AUTHENT);

		UsrAuthentRs rs = msgTemplateService.sendMessage(rq, UsrAuthentRs.class);

		User user = new User();
		user.setId(rs.getUsrId());
		user.setLang(rs.getLangPref());
		user.setDeptCode(rs.getDeptCode());
		user.setNumOfFailedLogins(rs.getNumOfFailedLogins());

		List<RoleInfo> roles = rs.getRoles();

		for (RoleInfo role : roles) {
			user.getRoles().add(new Role(role.getRoleId()));
		}

		return user;
	}

}
