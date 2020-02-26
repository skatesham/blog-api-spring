package com.sham.osi.blog.domain.authentication.model.user;

import com.sham.osi.blog.domain.authentication.model.usergroup.UserGroup;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

public interface UserService {

	User createUser(final UserDTO userDTO);

	UserGroup createUserGroup(UserGroupDTO userGroupDTO);

}
