package com.sham.osi.blog.infrastructure.auth;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
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

	private static final int EXPIRATION_TIME = 1;
	@Value("auth.jwt.key")
	private static String KEY;

	public static String generateToken(final User user) throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String userJson = mapper.writeValueAsString(user);
		final LocalDateTime now = LocalDateTime.now(); // set time from now
		final Date expiration = new Date(
				now.plus(EXPIRATION_TIME, ChronoUnit.HOURS).toInstant(ZoneOffset.UTC).toEpochMilli());
		final String token = Jwts.builder().claim("userDetails", userJson).setIssuer("com.sham.osi.blog")
				.setSubject(user.getName()).setExpiration(expiration).signWith(SignatureAlgorithm.HS512, KEY).compact();
		return token;
	}

	public static User parseToken(final String token) throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final String credentialsJson = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody()
				.get("userDetails", String.class);
		return mapper.readValue(credentialsJson, User.class);
	}

}
