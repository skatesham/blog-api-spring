package com.sham.osi.blog.interfaces.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String name;
	private String username;
	private String password;
	private String email;
	@JsonIgnore
	private boolean enabled;
	@JsonIgnore
	private boolean accountNonExpired;
	@JsonIgnore
	private boolean credentialsNonExpired;
	@JsonIgnore
	private boolean accountNonLocked;
	private String job;

	private List<UserGroupDTO> authorities;

	public void clearPassword() {
		this.password = null;
	}
}
