package com.sham.osi.blog.infrastructure.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sham.osi.blog.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		return this.userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("Username: %s not found in the system.", username)));
	}

}
