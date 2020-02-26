package com.sham.osi.blog.domain.authentication.model.usergroup;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

	COMMON("Cliente user"),
	ADMINISTRATOR("Administrator");

	private String description;

}
