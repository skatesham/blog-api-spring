package com.sham.osi.blog.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RootController {

	@GetMapping
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}

}
