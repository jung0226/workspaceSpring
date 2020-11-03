package com.bitcamp.webDbcp.register;

public interface RegisterDaoInterface {
	//로그인
	public void loginCheck(RegisterVO vo);
	//회원가입
	public int regInsert(RegisterVO vo);
	//회원정보수정
	public int regUpdate(RegisterVO vo);
	//아이디 찾기
	
	//비밀번호 찾기
	
	//회원탈퇴
	public int regDelete(RegisterVO vo);
}
