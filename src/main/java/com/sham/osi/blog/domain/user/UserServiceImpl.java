package com.sham.osi.blog.domain.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private static final RoleEnum authorityCommon = RoleEnum.COMMON;

	private final UserRepository userRepository;
	private final UserGroupService userGroupService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User createUser(final UserDTO userDTO) {
		final UserGroup initialUserGroup = this.userGroupService.getByAuthority(authorityCommon);
		final List<UserGroup> authorities = Lists.newArrayList(initialUserGroup);
		final String password = this.passwordEncoder.encode(userDTO.getPassword());
		final User userValidated = User.of(userDTO.getUsername(), password, userDTO.getName(),
				userDTO.getEmail(), userDTO.getJob(), authorities);
		this.userRepository.save(userValidated);
		return userValidated;
	}

	@Override
	public UserGroup createUserGroup(final UserGroupDTO userGroupDTO) {
		return this.userGroupService.create(userGroupDTO);
	}

}
