package com.sham.osi.blog.domain.blog;

import java.util.List;
import java.util.Optional;

import com.sham.osi.blog.interfaces.dto.BlogDTO;

public interface BlogViewerService {

	List<Blog> getAllBlogs();

	Optional<Blog> getBlog(Long id);

	Blog create(BlogDTO blogDTO);

}
