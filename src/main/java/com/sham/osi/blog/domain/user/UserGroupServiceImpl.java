package com.sham.osi.blog.domain.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserGroupServiceImpl implements UserGroupService {

	private final UserGroupRepository userGroupRepository;

	@Override
	public UserGroup getByAuthority(final RoleEnum authority) {
		final String message = String.format("Not found authority for:%s.", authority);
		// FIXME: Create an specific exception
		return this.userGroupRepository.findByAuthority(authority).orElseThrow(() -> new RuntimeException(message));
	}

	@Override
	public List<UserGroup> getAllByAuthorities(final List<RoleEnum> authorities) {
		return authorities.stream().map(authority -> this.getByAuthority(authority)).collect(Collectors.toList());
	}

	@Override
	public UserGroup create(final UserGroupDTO userGroupDTO) {
		final UserGroup userGroupCreted = UserGroup.of(userGroupDTO.getAuthority());
		return this.userGroupRepository.save(userGroupCreted);
	}

}
