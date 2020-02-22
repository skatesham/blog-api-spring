package com.sham.osi.blog.application;

import java.util.List;
import java.util.Optional;

import com.sham.osi.blog.interfaces.dto.BlogDTO;

public interface BlogFacade {

	public List<BlogDTO> getAllBlogs();

	public Optional<BlogDTO> getBlog(final Long id);

	public BlogDTO create(final BlogDTO blog);
}
