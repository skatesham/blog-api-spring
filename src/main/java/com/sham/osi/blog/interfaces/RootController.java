package com.sham.osi.blog.interfaces;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RootController {

	@GetMapping("/")
	public ResponseEntity<String> hello() {
		final String name = "Sham Vinicius Fiorin";
		final String hello = String.format("Hello %s!", name);
		return ResponseEntity.of(Optional.ofNullable(hello));
	}

}
