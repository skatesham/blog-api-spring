package com.sham.osi.blog.domain.authentication;

import com.sham.osi.blog.interfaces.dto.LoginDTO;
import com.sham.osi.blog.interfaces.dto.TokenDTO;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

public interface AuthenticationService {

	UserDTO signUp(UserDTO userDTO);

	UserGroupDTO createUserGroup(UserGroupDTO userGroupDTO);

	TokenDTO signIn(LoginDTO loginDTO);

}
