package com.inma.itp.auth.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.inma.itp.auth.dao.AuthDao;
import com.inma.itp.auth.model.bussinessObject.UsrAuthentRq;
import com.inma.itp.auth.model.dto.UserData;
import com.inma.itp.config.exception.ResourceNotFoundException;
import com.inma.itp.config.model.User;
import com.inma.itp.config.secuirty.JwtTokenProvider;
import com.inma.itp.config.secuirty.UserPrincipal;
import com.inma.itp.config.utils.Constants;
import com.inma.itp.config.utils.Security;

@Service
public class AuthService {

	@Autowired
	private AuthDao authDao;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtTokenProvider tokenProvider;

	public UserData login(String username, String password) {

		UsrAuthentRq rq = new UsrAuthentRq();
		rq.setLoginAttribVal(username);
		rq.setLoginAttribType(Constants.AUTHENT_ATTR_TYPE);
		rq.setInfo(Security.byteToHex(Security.getHash(password)));
		rq.setInfoType(Constants.AUTHENT_INFO_TYPE);

		User user = authDao.login(rq);

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
