package com.techelevator.model.workout;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.user.TinyORM;

public class JdbcWorkoutDao implements WorkoutDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcWorkoutDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public WorkoutPlans getWorkOutPlan(long workoutId) {
		String sql = "SELECT workout_plan_id, client_id, trainer_id, days_of_week, title, body FROM workout_plan WHERE workout_plan_id = ?";
        return new TinyORM<WorkoutPlans>(WorkoutPlans.class).readOne(jdbcTemplate.queryForRowSet(sql, workoutId));
	}

	@Override
	public WorkoutPlans getWorkOutPlanForId(long userId) {
		String sql = "SELECT workout_plan_id, client_id, trainer_id, days_of_week, title, body FROM workout_plan WHERE client_id = ? OR trainer_id=?";
        return new TinyORM<WorkoutPlans>(WorkoutPlans.class).readOne(jdbcTemplate.queryForRowSet(sql, userId, userId));
	}
	
	@Override
	public List<WorkoutPlans> getWorkOutPlansForIds(long trainerId, long clientId) {
		String sql = "SELECT workout_plan_id, client_id, trainer_id, days_of_week, title, body FROM workout_plan WHERE (client_id = ? OR trainer_id=?) AND (client_id = ? OR trainer_id=?)";
        return new TinyORM<WorkoutPlans>(WorkoutPlans.class).readAll(jdbcTemplate.queryForRowSet(sql, trainerId, trainerId, clientId, clientId));
	}
	
	
}
