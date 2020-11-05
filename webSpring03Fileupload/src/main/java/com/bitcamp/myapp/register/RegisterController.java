package com.bitcamp.myapp.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
	@RequestMapping("/loginForm")
	public String login() {
		return "/register/loginForm";
	}
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public ModelAndView loginOk(RegisterVO vo, HttpSession ses) {
		RegisterDAO dao = new RegisterDAO();
		RegisterVO resultVo = dao.login(vo);		
		
		ModelAndView mav = new ModelAndView();
		
		if(resultVo==null) {//로그인 실패
			mav.setViewName("redirect:loginForm");
		}else {
			ses.setAttribute("logId", resultVo.getUserid());
			ses.setAttribute("username", resultVo.getUsername());
			ses.setAttribute("logStatus", "Y");

			mav.setViewName("redirect:/");
		}	
		return mav;
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		ses.invalidate();
		return "home";
	}
}
