package com.sham.osi.blog.domain.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sham.osi.blog.domain.user.User;
import com.sham.osi.blog.domain.user.UserGroup;
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
