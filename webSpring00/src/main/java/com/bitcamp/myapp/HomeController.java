package com.bitcamp.myapp;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// 클래스 위에 @Controller 어노체이션을 표기하면 현재 클래스는 컨트롤러 클래스가 된다.
@Controller
public class HomeController {
	
	//메소드 위에 @RequestMapping를 적으면 접속자 주소를 매핑하는 메소드가 된다.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		//ModelAndView : 클라이언트로 보내는 데이터와 뷰 파일명을 가진다.
		ModelAndView mav = new ModelAndView();
		
		String name="홍길동";
		int data[] = {12,54,78,99,154,274,547,1};
		
		mav.addObject("username", name);
		mav.addObject("data",data);
		
		mav.setViewName("home");
		return mav;
	}
	
}
