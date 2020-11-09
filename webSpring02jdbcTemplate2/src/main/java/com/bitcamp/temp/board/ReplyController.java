package com.bitcamp.temp.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReplyController {
	@RequestMapping(value="/replyWrite", method=RequestMethod.GET)
	@ResponseBody
	public int replyInsert(ReplyVO vo, HttpSession ses){
		vo.setUserid((String)ses.getAttribute("logId"));
		
		ReplyDAO dao = new ReplyDAO();
		return dao.replyInsert(vo);
	}
	@RequestMapping("/replyList")
	@ResponseBody
	public List<ReplyVO> replyAllSelect(int no){
		ReplyDAO dao = new ReplyDAO();
		return dao.replyAllSelect(no);		
	}
	@RequestMapping("/replyEdit")
	@ResponseBody
	public int replyEdit(ReplyVO vo, HttpSession ses) {
		vo.setUserid((String)ses.getAttribute("logId"));
		ReplyDAO dao = new ReplyDAO();
		return dao.replyUpdate(vo);		
	}
	@RequestMapping("/replyDel")
	@ResponseBody
	public int replyDel(int re_no) {
		ReplyDAO dao = new ReplyDAO();
		return dao.replyDelete(re_no);
		
	}
}
