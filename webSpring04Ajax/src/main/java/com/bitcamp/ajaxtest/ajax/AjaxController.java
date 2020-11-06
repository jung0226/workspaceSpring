package com.bitcamp.ajaxtest.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	@RequestMapping("/ajaxHome")
	public String startAjax() {
		return "ajax/ajaxView";
	}
	@RequestMapping(value="/ajaxString", method=RequestMethod.GET, produces="application/text;charset=UTF-8")
	@ResponseBody //ajax 매핑 메소드는 view리턴이 안 됨.
	public String startString(int no, String username, String userid) {
		System.out.println("클라이언트가 서버로 보낸 데이터-> "+ no+", "+username+", "+userid);
		
		String txt = "번호: "+no+"<br/>이름: "+username+"<br/>아이디: "+userid+"<br/>";
		return txt;
	}
	@RequestMapping("/ajaxObject")
	@ResponseBody
	public TestVO ajaxObject() {
		return new TestVO(555,"세종대왕","서울시 마포구");
	}
	//@RequestMapping(value="/ajaxList", method=RequestMethod.GET)
	@RequestMapping(value="/ajaxList", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<TestVO> ajaxList(TestVO vo){
		List<TestVO> list = new ArrayList<TestVO>();
		
		list.add(vo);
		list.add(new TestVO(1000,"홍길동","서울시 서대문구"));
		list.add(new TestVO(2000,"주몽","고구려"));
		list.add(new TestVO(3000,"김연아","서울시 영등포구"));
		
		return list;
	}
	@RequestMapping("/ajaxMap")
	@ResponseBody
	public HashMap<String, TestVO> ajaxMap() {
		HashMap<String, TestVO> map = new HashMap<String, TestVO>();
		map.put("k1", new TestVO(111,"라이언","경기도 성남"));
		map.put("k2", new TestVO(222,"어피치","경기도 판교"));
		map.put("k3", new TestVO(333,"무지", "서울시 중구"));
		
		return map;
	}
	@RequestMapping(value="/ajaxJson", method=RequestMethod.GET, produces="application/text;charset=UTF-8")
	@ResponseBody
	public String ajaxJson() {
		//		{"no":"1234", "username":"홍길동", "tel":"010-4587-5454", "addr":"서울시 동작구"}
		int no =1234;
		String username="홍길동";
		String tel ="010-4545-7878";
		String addr = "서울시 동작구";
		
		String jsonText="{\"no\":\""+no+"\", \"username\":\""+username
						+"\", \"tel\":\""+tel+"\", \"addr\":\""+addr+"\"}";
		System.out.println(jsonText);
		return jsonText;
	}
}
