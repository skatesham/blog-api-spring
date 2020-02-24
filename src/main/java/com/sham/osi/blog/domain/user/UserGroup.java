package com.sham.osi.blog.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@NonNull
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class UserGroup implements GrantedAuthority {

	private static final long serialVersionUID = 5252329314312555918L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private RoleEnum authority;

	@Override
	public String getAuthority() {
		return this.authority.name();
	}

	public RoleEnum getRole() {
		return this.authority;
	}

}
