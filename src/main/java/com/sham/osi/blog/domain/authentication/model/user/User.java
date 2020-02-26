package com.sham.osi.blog.domain.authentication.model.user;

import static com.google.common.base.Preconditions.checkArgument;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import com.sham.osi.blog.domain.authentication.model.usergroup.UserGroup;
import com.sham.osi.blog.domain.common.ValidationMessageUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User implements UserDetails {

	private static final boolean DEFAULT_AUTH_VALUE = true;
	private static final long serialVersionUID = 8278876201651323148L;

	public static User of(final String username, final String password, final String name, final String email,
			final String job, final List<UserGroup> authorities) {
		final EmailValidator validator = new EmailValidator();
		Validate.notNull(username, ValidationMessageUtil.getFieldCannotBeNull("username"));
		Validate.notNull(email, ValidationMessageUtil.getFieldCannotBeNull("email"));
		Validate.notNull(password, ValidationMessageUtil.getFieldCannotBeNull("password"));
		checkArgument(validator.isValid(email, null), ValidationMessageUtil.EMAIL_NOT_VALID);
		Validate.notNull(authorities, ValidationMessageUtil.getFieldCannotBeNull("authorities"));
		// FIXME: Not Right message
		checkArgument(!CollectionUtils.isEmpty(authorities), ValidationMessageUtil.getFieldCannotBeNull("authorities"));
		return new User(username, password, name, email, job, authorities);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// Security fields
	@Column(nullable = false, unique = true)
	private String username;
	@JsonIgnore
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private boolean enabled;
	@Column(nullable = false)
	private boolean accountNonExpired;
	@Column(nullable = false)
	private boolean credentialsNonExpired;
	@Column(nullable = false)
	private boolean accountNonLocked;
	@ManyToMany(targetEntity = UserGroup.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "user_id", "authority_id" }) })
	private List<UserGroup> authorities;
	// Extra
	@Column
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Column
	private String job;

	@Column(columnDefinition = "TIMESTAMP", nullable = false)
	private LocalDateTime registered;

	private User(final String username, final String password, final String name, final String email, final String job,
			final List<UserGroup> authorities) {

		//// Security fields
		this.username = username;
		this.password = password;

		// Check not duplicated
		this.authorities = authorities.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());

		// with default
		this.enabled = DEFAULT_AUTH_VALUE;
		this.accountNonExpired = DEFAULT_AUTH_VALUE;
		this.credentialsNonExpired = DEFAULT_AUTH_VALUE;
		this.accountNonLocked = DEFAULT_AUTH_VALUE;

		//// User Extra field
		this.email = email;
		this.name = name;
		this.job = job;
		this.registered = LocalDateTime.now();
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Optional.ofNullable(this.authorities).map(ImmutableList::copyOf).orElse(null);
	}

	@JsonIgnore
	public Collection<UserGroup> getAuthorityEntitys() {
		return ImmutableList.copyOf(this.authorities);
	}

	public void clearRegistered() {
		this.registered = null;
	}

}
