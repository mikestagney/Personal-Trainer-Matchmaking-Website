package com.techelevator.model.workout;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.user.TinyORM;

public class JdbcWorkOutDao implements WorkOutDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcWorkOutDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public WorkOut getWorkOutPlan(long workoutId) {
		String sql = "SELECT workout_plan_id, client_id, trainer_id, days_of_week, title, body FROM workout_plan JOIN workout_plan_linker workout_plan_id=workout_plan_id WHERE workout_plan_id = ?";
        return new TinyORM<WorkOut>(WorkOut.class).readOne(jdbcTemplate.queryForRowSet(sql, workoutId));
	}

	@Override
	public WorkOut getWorkOutPlanForId(long userId) {
		String sql = "SELECT workout_plan_id, client_id, trainer_id, days_of_week, title, body FROM workout_plan JOIN workout_plan_linker workout_plan_id=workout_plan_id WHERE client_id = ? OR trainer_id=?";
        return new TinyORM<WorkOut>(WorkOut.class).readOne(jdbcTemplate.queryForRowSet(sql, userId, userId));
	}
	
	
}
