package com.bitcamp.webDbcp.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
	//로그인 폼으로 이동하는 컨트롤러
	@RequestMapping("/login")
	public String login() {
		return "register/login";
	}

}
