package com.sham.osi.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {

	private String key;

}