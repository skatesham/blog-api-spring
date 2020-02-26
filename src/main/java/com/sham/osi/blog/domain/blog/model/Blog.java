package com.sham.osi.blog.domain.blog.model;

import static com.sham.osi.blog.domain.common.ValidationMessageUtil.getFieldCannotBeNull;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.Validate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Blog {

	/**
	 * Create a Blog
	 */
	public static Blog of(final String title, final String content) {
		Validate.notNull(title, getFieldCannotBeNull("title"));
		Validate.notNull(content, getFieldCannotBeNull("content"));
		return new Blog(title, content);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;
	@Column(nullable = false)
	private LocalDateTime moment;

	private Blog(final String title, final String content) {
		this.title = title;
		this.content = content;
		this.moment = LocalDateTime.now();
	}

}
