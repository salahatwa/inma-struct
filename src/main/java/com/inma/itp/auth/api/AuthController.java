package com.inma.itp.auth.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inma.itp.auth.model.dto.LoginRequest;
import com.inma.itp.auth.service.AuthService;

@RestController
@RequestMapping("${api.context.path}/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginReq) {

		return ResponseEntity.ok(authService.login(loginReq.getUsername(), loginReq.getPassword()));
	}

}
