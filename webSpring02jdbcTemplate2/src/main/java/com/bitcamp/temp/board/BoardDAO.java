package com.bitcamp.temp.board;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.bitcamp.temp.Constants;

public class BoardDAO {
	public JdbcTemplate template;
	public BoardDAO() {
		template = Constants.jdbcTemplate;
	}
	/*
	public int insertBoard(final BoardVO vo) {
		int cnt=0;
		try {
		String sql = "insert into freeboard(no, userid, subject, content, ip) "
				+ "values(a_sq.nextval, ?, ?, ?, ?)";
		cnt= template.update(sql, new PreparedStatementSetter() {			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, vo.getUserid());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setString(4, vo.getIp());				
			}
		});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	*/
	public int insertBoard(final BoardVO vo) {
		String sql = "insert into freeboard(no, userid, subject, content, ip) "
				+ "values(a_sq.nextval, ?, ?, ?, ?)";
		return template.update(sql, vo.getUserid(), vo.getSubject(), vo.getContent(), vo.getIp());
	}
	public List<BoardVO> selectAll(){
		String sql = "select no, subject, userid, hit, to_char(writedate, 'MM-DD HH:MI') writedate "
				+ "from freeboard order by no desc";
		return template.query(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
		
	}
	public BoardVO selectBoard(int no) {
		String sql = "select no, subject, userid, hit, writedate, content from freeboard "
				+ "where no =?";
		
		return template.queryForObject(sql, new Object[] {no}, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	}	
	public BoardVO selectEditBoard(int no) {
		String sql = "select no, subject, content, userid from freeboard where no=?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class), no);
	}
	public int updateBoard(final BoardVO vo) {
		int cnt=0;
		try {
			String sql = "update freeboard set subject=?, content=? where no=? and userid=?";
			cnt= template.update(sql, new PreparedStatementSetter() {			
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, vo.getSubject());
					ps.setString(2, vo.getContent());
					ps.setInt(3, vo.getNo());
					ps.setString(4, vo.getUserid());
				}
			});
		}catch(Exception e) {
		}
		return cnt;
	}
	public int boardDelete(int no, String userid) {
		String sql="delete from freeboard where no="+no+" and userid='"+userid+"'";
		return template.update(sql);
	}
}
