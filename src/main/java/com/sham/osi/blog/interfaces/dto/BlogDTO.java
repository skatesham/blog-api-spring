package com.sham.osi.blog.interfaces.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogDTO {

	private Long id;
	private String title;
	private String content;
	private LocalDateTime moment;

}
