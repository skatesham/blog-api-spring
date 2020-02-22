package com.sham.osi.blog.domain.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationMessageUtil {

	public static String EMAIL_NOT_VALID = "Email are not valid.";

	public static String getFieldCannotBeNull(final String field) {
		return String.format("Field %s cannot be null.", field);
	}

}
