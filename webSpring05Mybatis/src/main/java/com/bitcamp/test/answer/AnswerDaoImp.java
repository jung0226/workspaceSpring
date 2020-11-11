package com.bitcamp.test.answer;

import java.util.List;

public interface AnswerDaoImp {
	public List<AnswerVO> allRecord() ;
	public int recordInsert(AnswerVO vo);
}
