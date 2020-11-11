package com.bitcamp.test.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@RequestMapping("/boardList")
	public ModelAndView boardAllRecord() {
		BoardDaoImp dao =  sqlSession.getMapper(BoardDaoImp.class);
		List<BoardVO> list = dao.boardAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("board/list");
		return mav;
	}
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	@RequestMapping(value="/boardWriteOk", method = RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo, HttpServletRequest r, HttpSession s) {
		vo.setIp(r.getRemoteAddr());
		vo.setUserid((String)s.getAttribute("userid"));
		
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		int result = dao.boardInsert(vo);
		ModelAndView mav = new ModelAndView();		
		if(result>0) {
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("board/result");
		}		
		return mav;
	}
	@RequestMapping("/boardView")
	public ModelAndView boardView(int no) {
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		dao.hitCount(no);
		BoardVO vo = dao.boardSelect(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("board/view");
		return mav;
	}
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(int no) {
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		
		BoardVO vo = dao.boardSelect(no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/edit");
		mav.addObject("vo", vo);
		
		return mav;
	}
	@RequestMapping(value="/editOk", method = RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo, HttpSession ses) {
		vo.setUserid((String)ses.getAttribute("userid"));
		
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		
		int result = dao.boardUpdate(vo);
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {//업데이트
			mav.addObject("no", vo.getNo());
			mav.setViewName("redirect:boardView");
		}else {
			mav.setViewName("board/result");
		}
		return mav;		
	}
	@RequestMapping("/boardDel")
	public ModelAndView boardDel(int no, HttpSession ses) {
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		int result = dao.boardDelete(no, (String)ses.getAttribute("userid"));
		ModelAndView mav = new ModelAndView();
		if(result>0) {//삭제 된 경우
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("board/result");
		}
		return mav;
	}	
}
