package com.sham.osi.blog.domain.blog;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sham.osi.blog.domain.blog.model.Blog;
import com.sham.osi.blog.domain.blog.model.BlogRepository;
import com.sham.osi.blog.interfaces.dto.BlogDTO;

@Service
public class BlogViewerServiceImpl implements BlogViewerService {

	@Autowired
	private BlogRepository blogRepository;

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
