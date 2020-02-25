package com.sham.osi.blog.application.essential;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sham.osi.blog.domain.blog.model.Blog;
import com.sham.osi.blog.interfaces.dto.BlogDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BlogDTOConverter {

	public static BlogDTO convertBlogToDTO(final Blog blog, final ObjectMapper objectMapper) {
		return objectMapper.convertValue(blog, BlogDTO.class);
	}

	public static Optional<BlogDTO> convertBlogToOptDTO(final Blog blog, final ObjectMapper objectMapper) {
		return Optional.ofNullable(objectMapper.convertValue(blog, BlogDTO.class));
	}

	public static List<BlogDTO> convertListToDTO(final Collection<Blog> blogs, final ObjectMapper objectMapper) {
		return blogs.stream().map(blog -> convertBlogToDTO(blog, objectMapper)).collect(Collectors.toList());
	}
}
