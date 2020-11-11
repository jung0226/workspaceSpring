package com.bitcamp.test.answer;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnswerController {
	@Autowired
	SqlSession sqlSession;
	@RequestMapping("/answerList")
	public ModelAndView allList() {
		AnswerDaoImp dao= sqlSession.getMapper(AnswerDaoImp.class);
		
		List<AnswerVO> list = dao.allRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("answer/list");
		
		return mav;
	}
	@RequestMapping("/answerWrite")
	public String write() {

		return "answer/write";
	}
	@RequestMapping(value="/answerWriteOk", method = RequestMethod.POST)
	public ModelAndView writeOk(AnswerVO vo, HttpSession ses) {
		vo.setUserid((String)ses.getAttribute("userid"));
		AnswerDaoImp dao= sqlSession.getMapper(AnswerDaoImp.class);
		int result = dao.recordInsert(vo);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:answerList");
		return mav;
	}

}
