package com.bitcamp.temp.register;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bitcamp.temp.Constants;

public class RegisterDAO {
	public JdbcTemplate template = null;
	public RegisterDAO() {
		template = Constants.jdbcTemplate;
	}
	public RegisterVO login(RegisterVO vo) {
		String sql="select count(userid) cnt from register where userid=? and userpwd=?";
		// queryForObject : 선택된 레코드가 없을 경우 에러발생
		RegisterVO vo1 = template.queryForObject(sql, new BeanPropertyRowMapper<RegisterVO>(RegisterVO.class), vo.getUserid(), vo.getUserpwd());

		if(vo1.getCnt()<=0) {//일치하는 레코드가 없을 때
			return null;
		}else {
			String sql2 = "select userid, username from register where userid=? and userpwd=?";
			return template.queryForObject(sql2, new BeanPropertyRowMapper<RegisterVO>(RegisterVO.class), vo.getUserid(), vo.getUserpwd());
		}
	
	}
}
