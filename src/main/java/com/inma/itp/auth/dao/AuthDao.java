package com.inma.itp.auth.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.inma.itp.auth.models.dto.UserData;
import com.inma.itp.auth.models.queue.UsrAuthentRq;
import com.inma.itp.auth.models.queue.UsrAuthentRs;
import com.inma.itp.config.exception.ResourceNotFoundException;
import com.inma.itp.config.messaging.MessageTemplateService;
import com.inma.itp.config.models.Role;
import com.inma.itp.config.models.User;
import com.inma.itp.config.secuirty.JwtTokenProvider;
import com.inma.itp.config.secuirty.UserPrincipal;
import com.inma.itp.config.utils.Constants;
import com.inma.itp.config.utils.Security;
import com.inma.itp.auth.models.queue.UsrAuthentRs.RoleInfo;

@Service
public class AuthDao {

	@Autowired
	private MessageTemplateService msgTemplateService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtTokenProvider tokenProvider;

	public UserData login(String username, String password) {

		UsrAuthentRq rq = new UsrAuthentRq(Constants.FUNCTION_AUTHENT);
		rq.setLoginAttribVal(username);
		rq.setLoginAttribType(Constants.AUTHENT_ATTR_TYPE);
		rq.setInfo(Security.byteToHex(Security.getHash(password)));
		rq.setInfoType(Constants.AUTHENT_INFO_TYPE);

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

		saveUser(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(UserPrincipal.create(user), password,
				new ArrayList<>());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		String jwt = tokenProvider.generateToken(authentication);

		return new UserData(jwt, userPrincipal);
	}

	@Cacheable(value = "userCache", key = "#id")
	public UserDetails getUserById(String id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		return UserPrincipal.create(user);
	}

	@Transactional
	public void saveUser(User user) {
		if (!userRepo.findById(user.getId()).isPresent())
			userRepo.save(user);
	}

}
