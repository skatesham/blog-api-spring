package com.sham.osi.blog.domain.authentication;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sham.osi.blog.config.JwtProperties;
import com.sham.osi.blog.domain.authentication.model.auth.AuthenticatioDTOConverter;
import com.sham.osi.blog.domain.authentication.model.user.User;
import com.sham.osi.blog.domain.authentication.model.user.UserService;
import com.sham.osi.blog.domain.authentication.model.usergroup.UserGroup;
import com.sham.osi.blog.infrastructure.auth.JwtService;
import com.sham.osi.blog.interfaces.dto.LoginDTO;
import com.sham.osi.blog.interfaces.dto.TokenDTO;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final ObjectMapper objectMapper;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;

	private final JwtProperties jwtProperties;

	@Override
	public UserDTO signUp(final UserDTO userDTO) {
		final User createdUser = this.userService.createUser(userDTO);
		return AuthenticatioDTOConverter.convertUserToDTO(createdUser, this.objectMapper);
	}

	@Override
	public TokenDTO signIn(final LoginDTO loginDTO) {

		final Authentication credentials = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
				loginDTO.getPassword());

		final User user = (User) this.authenticationManager.authenticate(credentials).getPrincipal();
		String generateToken = null;
		try {
			generateToken = JwtService.generateToken(user, this.jwtProperties.getKey());
			return TokenDTO.of(generateToken);
		} catch (final JsonProcessingException e) {
			log.error("Error During signIn, error:" + e.getMessage(), e);
		}
		return Optional.ofNullable(generateToken).map(token -> TokenDTO.of(token)).orElse(null);
	}

	@Override
	public UserGroupDTO createUserGroup(final UserGroupDTO userGroupDTO) {
		final UserGroup createdUserGroup = this.userService.createUserGroup(userGroupDTO);
		return AuthenticatioDTOConverter.convertUserGroupToDTO(createdUserGroup, this.objectMapper);
	}

}
