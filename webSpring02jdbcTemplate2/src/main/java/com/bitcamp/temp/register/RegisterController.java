package com.bitcamp.temp.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
	@RequestMapping("/login")
	public String login() {
		return "register/login";
	}
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public String loginOk(RegisterVO vo, HttpServletRequest req) {
		
		RegisterDAO dao = new RegisterDAO();
		//null:로그인 실패, null이 아닌 경우 로그인 성공
		RegisterVO resultVO = dao.login(vo);
		if(resultVO == null) {//로그인 실패
			return "register/login";
		}else {//로그인 성공
			HttpSession ses = req.getSession();
			ses.setAttribute("logId", resultVO.getUserid());
			ses.setAttribute("logName", resultVO.getUsername());
			ses.setAttribute("logStatus", "Y");
			return "home";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		ses.invalidate();
		return "home";
	}
}
