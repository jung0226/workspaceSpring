package com.bitcamp.myapp.data;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bitcamp.myapp.Constants;

public class DataDAO {
	public JdbcTemplate template;
	public DataDAO(){
		this.template =Constants.template;
	}
	public List<DataVO> allList(){
		System.out.println("template = "+Constants.template);
		String sql = "select no, title, userid, filename1, filename2 from data order by no desc";
		return template.query(sql, new BeanPropertyRowMapper<DataVO>(DataVO.class));
		
	}
}
