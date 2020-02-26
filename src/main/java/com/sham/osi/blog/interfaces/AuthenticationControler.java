package com.sham.osi.blog.interfaces;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sham.osi.blog.application.AuthenticationFacade;
import com.sham.osi.blog.interfaces.dto.LoginDTO;
import com.sham.osi.blog.interfaces.dto.TokenDTO;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationControler {

	private final AuthenticationFacade authenticationFacade;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO userDTO) {
		final UserDTO createdUser = this.authenticationFacade.signUp(userDTO);
		final URI location = UriComponentsBuilder.fromUriString("/user/{id}").buildAndExpand(createdUser.getId())
				.toUri();
		return ResponseEntity.created(location).body(createdUser);
	}

	@PostMapping("/usergroup")
	public ResponseEntity<UserGroupDTO> createUserGroup(@RequestBody final UserGroupDTO userGroupDTO) {
		final UserGroupDTO createdUserGroup = this.authenticationFacade.createUserGroup(userGroupDTO);
		final URI location = UriComponentsBuilder.fromUriString("/usergroup/{id}")
				.buildAndExpand(createdUserGroup.getId()).toUri();
		return ResponseEntity.created(location).body(createdUserGroup);
	}

	@PostMapping("/signin")
	public ResponseEntity<TokenDTO> login(@RequestBody final LoginDTO loginDTO, final HttpServletResponse response)
			throws JsonProcessingException {
		System.out.println(this.passwordEncoder.encode("sham"));
		final TokenDTO token = this.authenticationFacade.signIn(loginDTO);
		response.setHeader("Token", token.getToken());
		return new ResponseEntity<>(token, HttpStatus.OK);

	}

}
