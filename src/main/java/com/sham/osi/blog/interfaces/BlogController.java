package com.sham.osi.blog.interfaces;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sham.osi.blog.application.BlogFacade;
import com.sham.osi.blog.interfaces.dto.BlogDTO;

import lombok.AllArgsConstructor;

/**
 * Blog is like you say - "I gonna blog here"", so posts call blog here dont
 * care.
 *
 * @author shazam
 */

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class BlogController {

	private final BlogFacade blogFacade;

	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public ResponseEntity<BlogDTO> create(@RequestBody final BlogDTO blogDTO) {
		final BlogDTO BlogCreated = this.blogFacade.create(blogDTO);
		final URI location = UriComponentsBuilder.fromUriString("/posts/{id}").buildAndExpand(BlogCreated.getId())
				.toUri();
		return ResponseEntity.created(location).body(BlogCreated);
	}

	@GetMapping
	public ResponseEntity<List<BlogDTO>> getAllBlogs() {
		final List<BlogDTO> allBlogs = this.blogFacade.getAllBlogs();
		return allBlogs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allBlogs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BlogDTO> getBlog(@PathVariable final Long id) {
		final Optional<BlogDTO> blog = this.blogFacade.getBlog(id);
		return ResponseEntity.of(blog);
	}

}
