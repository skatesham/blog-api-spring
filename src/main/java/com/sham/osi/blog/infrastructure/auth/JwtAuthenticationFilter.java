package com.sham.osi.blog.infrastructure.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sham.osi.blog.domain.authentication.model.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter implements Filter {

	private static final String HEADER = "Authorization";

	// FIXME: Use JwtProperties here
	private static final String KEY = "my-secret-key";

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain filterChain) throws IOException, ServletException {

		try {
			final HttpServletRequest request = (HttpServletRequest) servletRequest;
			final String autorization = request.getHeader(HEADER);
			if (autorization != null) {
				final String token = autorization.replace("Bearer ", "");
				final User user = JwtService.parseToken(token, KEY);
				final Authentication credentials = new UsernamePasswordAuthenticationToken(user.getUsername(),
						user.getPassword(), user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(credentials);
			}
			filterChain.doFilter(request, servletResponse);

		} catch (final Throwable t) {
			final HttpServletResponse response = (HttpServletResponse) servletResponse;
			log.error(t.getMessage(), t);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, t.getMessage());

		}

	}

}
