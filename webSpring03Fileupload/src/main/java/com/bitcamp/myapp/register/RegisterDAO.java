package com.bitcamp.myapp.register;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bitcamp.myapp.Constants;

public class RegisterDAO {
	public JdbcTemplate template = null;

	public RegisterDAO() {
		template = Constants.template;
	}

	public RegisterVO login(RegisterVO vo) {

		RegisterVO resultVO = null;
		try {
			String sql2 = "select userid, username from register where userid=? and userpwd=?";
			resultVO = template.queryForObject(sql2, new Object[] {vo.getUserid(), vo.getUserpwd()}, new BeanPropertyRowMapper<RegisterVO>(RegisterVO.class));
			resultVO.setLogStatus("Y");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}
}
