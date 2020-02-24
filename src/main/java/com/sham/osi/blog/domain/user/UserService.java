package com.sham.osi.blog.domain.user;

import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

public interface UserService {

	User createUser(final UserDTO userDTO);

	UserGroup createUserGroup(UserGroupDTO userGroupDTO);

}
