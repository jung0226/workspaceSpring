package com.bitcamp.temp.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@RequestMapping("/boardList")
	public ModelAndView boardList(){
		//레코드 선택
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.selectAll();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("board/boardList");
		
		return mav;
	}
	@RequestMapping("boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping(value="/boardWriteOk", method=RequestMethod.POST)
	public ModelAndView boardWirteOk(BoardVO vo, HttpServletRequest r) {
		//아이디
		HttpSession session = r.getSession();
		vo.setUserid((String)session.getAttribute("logId"));
		//ip
		vo.setIp(r.getRemoteAddr());
		
		BoardDAO dao = new BoardDAO();
		int cnt = dao.insertBoard(vo);
		
		ModelAndView mav = new ModelAndView();
		if(cnt>0) {
			//레코드 추가
			mav.setViewName("redirect:boardList");
		}else {
			mav.addObject("msg","글등록이 실패하였습니다.");
			mav.setViewName("board/resultePage");
		}
		return mav;		
	}
	@RequestMapping("/boardView")
	public ModelAndView boardView(@RequestParam("no") int no ) {
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.selectBoard(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("board/boardView");
		
		return mav;
	}
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(@RequestParam("no") int no) {
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.selectEditBoard(no);		
		
		ModelAndView mav = new ModelAndView();	
		
		mav.addObject("vo", vo);		
		mav.setViewName("board/boardEdit");
		
		return mav;
	}
	@RequestMapping(value="/boardEditOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo, HttpSession ses) {
		vo.setUserid((String)ses.getAttribute("logId"));
		
		BoardDAO dao = new BoardDAO();
		int result = dao.updateBoard(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", vo.getNo());
		mav.addObject("result", result);
		mav.setViewName("board/editResult");
		
		return mav;		
	}
	@RequestMapping("/boardDelete")
	public ModelAndView boardDelete(@RequestParam("no") int no, HttpSession ses) {
		BoardDAO dao = new BoardDAO();
		int cnt = dao.boardDelete(no, (String)ses.getAttribute("logId"));
		
		ModelAndView mav = new ModelAndView();
		if(cnt>0) {
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("redirect:boardView");
			mav.addObject("no", no);
		}
		return mav;
	}
}
