package com.bitcamp.temp.board;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.bitcamp.temp.Constants;

public class ReplyDAO {
	public JdbcTemplate template;
	public ReplyDAO() {
		this.template = Constants.jdbcTemplate;
	}
	//댓글 추가
	public int replyInsert(final ReplyVO vo) {
		String sql = "insert into replyboard(re_no, no, userid, content) "
				+ "values (a_sq.nextval, ?, ?, ?)";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, vo.getNo());
				ps.setString(2, vo.getUserid());
				ps.setString(3, vo.getContent());
			}
		});
	}
	//댓글 전체 선택						원글번호
	public List<ReplyVO> replyAllSelect(int no){
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		try {
		String sql = "select re_no, no, userid, content, writedate from replyboard where no=? order by re_no desc";
		list = template.query(sql, new BeanPropertyRowMapper<ReplyVO>(ReplyVO.class), no);		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//댓글 수정
	public int replyUpdate(ReplyVO vo) {
		String sql="update replyboard set content=? where re_no=? and userid=?";
		return template.update(sql, vo.getContent(), vo.getRe_no(), vo.getUserid());
	}
	//댓글 삭제
	public int replyDelete(int re_no) {
		String sql="delete from replyboard where re_no="+re_no;
		return template.update(sql);
	}
	
}
