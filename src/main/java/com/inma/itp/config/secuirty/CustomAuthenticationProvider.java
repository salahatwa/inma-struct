package com.inma.itp.config.secuirty;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.inma.itp.auth.service.AuthService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private AuthService authService;
 
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
    	
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
         
        
        return new UsernamePasswordAuthenticationToken(
        		authService.login(name, password), password, new ArrayList<>());
    } 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
