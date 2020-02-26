package com.sham.osi.blog.interfaces.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class TokenDTO {

	@NonNull
	private String token;

}
