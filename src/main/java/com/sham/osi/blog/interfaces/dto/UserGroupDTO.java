package com.sham.osi.blog.interfaces.dto;

import com.sham.osi.blog.domain.authentication.model.usergroup.RoleEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGroupDTO {

	private Long id;
	private RoleEnum authority;

}
