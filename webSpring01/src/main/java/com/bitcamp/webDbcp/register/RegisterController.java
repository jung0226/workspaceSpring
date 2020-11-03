package com.bitcamp.webDbcp.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
	//beans객체를 자동으로 대입시킨다.
	@Autowired
	RegisterDAO regDao;
	//로그인 폼으로 이동하는 컨트롤러
	@RequestMapping("/login")
	public String login() {
		return "register/login";
	}
	//로그인
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public ModelAndView loginOk(RegisterVO vo, HttpServletRequest req) {
		
		
		regDao.loginCheck(vo);
		ModelAndView mav = new ModelAndView();
		
		//로그인 성공시
		if(vo.getLogStatus().equals("Y")) {
			HttpSession session = req.getSession();
			session.setAttribute("userid", vo.getUserid());
			session.setAttribute("username", vo.getUsername());
			session.setAttribute("logStatus", vo.getLogStatus());
			//mav.setViewName("home"); 
			mav.setViewName("redirect:/");//컨트롤러에서 다른 매핑을 호출
		}else {
			//mav.setViewName("register/login");
			mav.setViewName("redirect:login");
		}
		return mav;
	}
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpServletRequest r) {
		HttpSession ses= r.getSession();
		ses.invalidate();
		return "home";
	}
	
}
