package com.sham.osi.blog.domain.blog;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sham.osi.blog.interfaces.dto.BlogDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogViewerServiceImpl implements BlogViewerService {

	private final BlogRepository blogRepository;

	@Override
	public List<Blog> getAllBlogs() {
		return this.blogRepository.findAll();
	}

	@Override
	public Optional<Blog> getBlog(final Long id) {
		return this.blogRepository.findById(id);
	}

	@Override
	public Blog create(final BlogDTO blogDTO) {
		final Blog blog = Blog.of(blogDTO.getTitle(), blogDTO.getContent());
		return this.blogRepository.save(blog);
	}

}
