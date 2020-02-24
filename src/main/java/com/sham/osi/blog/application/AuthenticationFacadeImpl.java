package com.sham.osi.blog.application;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sham.osi.blog.domain.user.User;
import com.sham.osi.blog.domain.user.UserGroup;
import com.sham.osi.blog.domain.user.UserService;
import com.sham.osi.blog.infrastructure.auth.JwtService;
import com.sham.osi.blog.interfaces.dto.LoginDTO;
import com.sham.osi.blog.interfaces.dto.TokenDTO;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	private final ObjectMapper objectMapper;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;

	@Override
	public UserDTO signUp(final UserDTO userDTO) {
		final User createdUser = this.userService.createUser(userDTO);
		return AuthenticatioDTOConverter.convertUserToDTO(createdUser, this.objectMapper);
	}

	@Override
	public UserGroupDTO createUserGroup(final UserGroupDTO userGroupDTO) {
		final UserGroup createdUserGroup = this.userService.createUserGroup(userGroupDTO);
		return AuthenticatioDTOConverter.convertUserGroupToDTO(createdUserGroup, this.objectMapper);
	}

	@Override
	public TokenDTO signIn(final LoginDTO loginDTO) {

		final Authentication credentials = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
				loginDTO.getPassword());
		final User user = (User) this.authenticationManager.authenticate(credentials).getPrincipal();
		String generateToken = null;
		try {
			generateToken = JwtService.generateToken(user);
			return TokenDTO.of(generateToken);
		} catch (final JsonProcessingException e) {
			log.error("Error During signIn, error:" + e.getMessage(), e);
		}
		return Optional.ofNullable(generateToken)
				.map(token -> TokenDTO.of(token))
				.orElse(null);
	}

}
