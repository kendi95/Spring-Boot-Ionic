package com.kohatsu.cursomc.resources.auth;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kohatsu.cursomc.dto.EmailDTO;
import com.kohatsu.cursomc.security.JWTUtil;
import com.kohatsu.cursomc.security.UserSS;
import com.kohatsu.cursomc.servicies.UserService;
import com.kohatsu.cursomc.servicies.auth.AuthService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/auth")
public class AuthResources {

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer "+token);
		response.addHeader("access-control-expose-headers", "Authorization");
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) throws ObjectNotFoundException{
		
		authService.sendNewPassword(objDto.getEmail());
		
		return ResponseEntity.noContent().build();
		
	}
	
}
