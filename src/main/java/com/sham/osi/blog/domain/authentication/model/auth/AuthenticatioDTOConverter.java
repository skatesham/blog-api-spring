package com.sham.osi.blog.domain.authentication.model.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sham.osi.blog.domain.authentication.model.user.User;
import com.sham.osi.blog.domain.authentication.model.usergroup.UserGroup;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthenticatioDTOConverter {

	public static UserDTO convertUserToDTO(final User user, final ObjectMapper objectMapper) {
		return objectMapper.convertValue(user, UserDTO.class);
	}

	public static UserGroupDTO convertUserGroupToDTO(final UserGroup userGroup, final ObjectMapper objectMapper) {
		return objectMapper.convertValue(userGroup, UserGroupDTO.class);
	}
}
