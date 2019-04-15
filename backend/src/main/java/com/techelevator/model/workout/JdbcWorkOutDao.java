package com.techelevator.model.workout;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcWorkOutDao implements WorkOutDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcWorkOutDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	
}
