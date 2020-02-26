package com.sham.osi.blog;

import java.time.LocalDateTime;

import com.sham.osi.blog.domain.blog.model.Blog;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class BlogFixture {

	public static final String DEFAULT = LabelFixture.DEFAULT;

	public static void loadTemplates() {
		loadBlogTemplates();
	}

	private static void loadBlogTemplates() {
		Fixture.of(Blog.class).addTemplate(DEFAULT, new Rule() {
			{
				this.add("title", "title");
				this.add("content", "content");
				this.add("moment", LocalDateTime.now());
			}
		});
	}

}
