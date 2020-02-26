package com.sham.osi.blog.application;

import com.sham.osi.blog.interfaces.dto.LoginDTO;
import com.sham.osi.blog.interfaces.dto.TokenDTO;
import com.sham.osi.blog.interfaces.dto.UserDTO;
import com.sham.osi.blog.interfaces.dto.UserGroupDTO;

public interface AuthenticationFacade {

	UserDTO signUp(UserDTO userDTO);

	UserGroupDTO createUserGroup(UserGroupDTO userGroupDTO);

	TokenDTO signIn(LoginDTO loginDTO);


}
