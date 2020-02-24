package com.sham.osi.blog.domain.user;

import java.util.List;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserGroupUtil {

	public static List<RoleEnum> getAllAuthoritiesRoleEnum(final User user) {
		return user.getAuthorityEntitys().stream().map(authority -> authority.getRole()).collect(Collectors.toList());
	}

}
