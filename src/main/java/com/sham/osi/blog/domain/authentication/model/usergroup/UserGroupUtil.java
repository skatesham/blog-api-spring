package com.sham.osi.blog.domain.authentication.model.usergroup;

import java.util.List;
import java.util.stream.Collectors;

import com.sham.osi.blog.domain.authentication.model.user.User;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserGroupUtil {

	public static List<RoleEnum> getAllAuthoritiesRoleEnum(final User user) {
		return user.getAuthorityEntitys().stream().map(authority -> authority.getRole()).collect(Collectors.toList());
	}

}
