package com.bitcamp.webDbcp.board;

import java.util.List;

public interface BoardDAOInterface {
	//전체 리스트
	public List<BoardVO> getAllRecord();
	//글 쓰기
	public int writeBoard(BoardVO vo);
	//글 수정
	public int editBoard(BoardVO vo);
	//글 삭제
	public int deleteBoard(int no, String userid);
	//글 선택
	//public BoardVO selectBoard(int no);
	public void selectBoard(BoardVO vo);
	
	//조회수 증가
	public void hitCount(int no);
}
