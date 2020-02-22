package com.sham.osi.blog.application;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sham.osi.blog.domain.blog.Blog;
import com.sham.osi.blog.domain.blog.BlogViewerService;
import com.sham.osi.blog.interfaces.dto.BlogDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BlogFacadeImpl implements BlogFacade {

	private final ObjectMapper objectMapper;

	private final BlogViewerService blogViewService;

	@Override
	public List<BlogDTO> getAllBlogs() {
		final List<Blog> blogs = this.blogViewService.getAllBlogs();
		return BlogDTOConverter.convertListToDTO(blogs, this.objectMapper);
	}

	@Override
	public Optional<BlogDTO> getBlog(final Long id) {
		Validate.notNull(id, ValidationDTOMessages.ID_NOT_NULL_MESSAGE);
		final Blog blog = this.blogViewService.getBlog(id).orElse(null);
		return BlogDTOConverter.convertBlogToOptDTO(blog, this.objectMapper);
	}

	@Override
	public BlogDTO create(final BlogDTO blogDTO) {
		Validate.notNull(blogDTO, ValidationDTOMessages.NOT_NULL_MESSAGE);
		final Blog blog = this.blogViewService.create(blogDTO);
		return BlogDTOConverter.convertBlogToDTO(blog, this.objectMapper);
	}

}
