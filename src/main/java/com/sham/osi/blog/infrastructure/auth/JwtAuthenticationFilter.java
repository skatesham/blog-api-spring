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

import com.sham.osi.blog.domain.user.User;

public class JwtAuthenticationFilter implements Filter {

	private static final String HEADER = "Authorization";

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
			final FilterChain filterChain) throws IOException, ServletException {

		try {
			final HttpServletRequest request = (HttpServletRequest) servletRequest;
			final String autorization = request.getHeader(HEADER);
			if (autorization != null) {
				final User user = JwtService.parseToken(autorization.replace("Bearer ", ""));
				final Authentication credentials = new UsernamePasswordAuthenticationToken(user.getUsername(),
						user.getPassword(), user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(credentials);
			}
			filterChain.doFilter(request, servletResponse);

		} catch (final Throwable t) {
			final HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, t.getMessage());
		}

	}

}
