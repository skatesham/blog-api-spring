package com.sham.osi.blog.infrastructure.auth;

import java.io.IOException;
import java.util.Date;

import org.springframework.boot.json.JsonParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sham.osi.blog.domain.authentication.model.user.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtService {

	private static final int EXPIRATION_TIME = 3600000;

	public static String generateToken(final User user, final String key) throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String userJson = mapper.writeValueAsString(user);
		final Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
		final String token = Jwts.builder().claim("userDetails", userJson).setIssuer("com.sham.osi.blog")
				.setSubject(user.getName()).setExpiration(expiration).signWith(SignatureAlgorithm.HS512, key).compact();
		return token;
	}

	public static User parseToken(final String token, final String key) throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final String credentialsJson = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody()
				.get("userDetails", String.class);
		return mapper.readValue(credentialsJson, User.class);
	}

}
