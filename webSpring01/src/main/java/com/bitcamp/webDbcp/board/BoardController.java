package com.bitcamp.webDbcp.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@Autowired
	BoardDAO bDao;
	//게시판 리스트
	@RequestMapping("/boardList")
	public ModelAndView boardList() {
		List<BoardVO> list = bDao.getAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("board/boardList");
		
		return mav;
	}
	//글 내용보기
	@RequestMapping("/boardView")
	public ModelAndView boardview(BoardVO vo) {
		//조회수 증가
		bDao.hitCount(vo.getNo());
		bDao.selectBoard(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("board/boardView");
		
		return mav;
	}
	//글 쓰기 폼
	@RequestMapping("/boardWrite")
	public ModelAndView boardWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardWrite");
		return mav;
	}
	//글쓰기 DB
	@RequestMapping(value="/boardWriteOk", method=RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo, HttpServletRequest r) {
		vo.setIp(r.getRemoteAddr());
		HttpSession session  = r.getSession();
		
		vo.setUserid((String)session.getAttribute("userid"));
		
		int result = bDao.writeBoard(vo);
		ModelAndView  mav = new ModelAndView();
		
		//글등록->리스트
		//글 등록 실패->글 등록 페이지
		if(result>0) {//등록
			mav.setViewName("redirect:boardList");
		}else {
			mav.addObject("msg", "글 등록");
			mav.setViewName("board/result");
		}
		return mav;
	}
	//글 수정 폼
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(BoardVO vo) {
		bDao.selectBoard(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("board/boardEdit");
		return mav;
	}
	//글 수정
	@RequestMapping(value="/boardEditOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo) {
		int result = bDao.editBoard(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", vo.getNo());
		if(result>0) {//수정완료
			mav.setViewName("redirect:boardView");
		}else {
			mav.addObject("msg", "글 수정");
			mav.setViewName("board/result");
		}
		return mav;
	}
	//글 삭제
	@RequestMapping("/boardDel")
	public ModelAndView boardDelete(@RequestParam("no") int no, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userid = (String)session.getAttribute("userid");
		
		int result = bDao.deleteBoard(no, userid);
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {//글이 삭제된 경우 리스트
			mav.setViewName("redirect:boardList");
		}else {//삭제 실패시 글 내용 보기
			mav.addObject("msg", "글 삭제");
			mav.setViewName("board/result");
		}
		return mav;
	}
}
