package com.sham.osi.blog.application;

import org.springframework.stereotype.Component;

import com.sham.osi.blog.domain.authentication.AuthenticationService;
import com.sham.osi.blog.interfaces.dto.LoginDTO;
import com.sham.osi.blog.interfaces.dto.TokenDTO;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	private final AuthenticationService authenticationService;

	@Override
	public UserDTO signUp(final UserDTO userDTO) {
		return this.authenticationService.signUp(userDTO);
	}

	@Override
	public TokenDTO signIn(final LoginDTO loginDTO) {
		return this.authenticationService.signIn(loginDTO);
	}

	@Override
	public UserGroupDTO createUserGroup(final UserGroupDTO userGroupDTO) {
		return this.authenticationService.createUserGroup(userGroupDTO);
	}

}
