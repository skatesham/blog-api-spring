package com.sham.osi.blog.domain.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

@Repository
public interface UserGroupService {

	UserGroup getByAuthority(RoleEnum authority);

	List<UserGroup> getAllByAuthorities(List<RoleEnum> authorities);

	UserGroup create(UserGroupDTO userGroupDTO);

	// List<UserGroup> getByUserDTO(UserDTO userDTO);

}
