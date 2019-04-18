package com.techelevator.model.workout;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.model.user.TinyORM;
import com.techelevator.model.user.WorkoutPlan;

@Component
public class JdbcWorkoutDao implements WorkoutDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcWorkoutDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public WorkoutPlan getWorkOutPlan(long workoutId) {
		String sql = "SELECT workoutplan_id, client_id, trainer_id, days_of_week, title, body, completed FROM workout_plan WHERE workoutplan_id = ?";
        return new TinyORM<WorkoutPlan>(WorkoutPlan.class).readOne(jdbcTemplate.queryForRowSet(sql, workoutId));
	}

	@Override
	public WorkoutPlan getWorkOutPlanForId(long userId) {
		String sql = "SELECT workoutplan_id, client_id, trainer_id, days_of_week, title, body, completed FROM workout_plan WHERE client_id = ? OR trainer_id=?";
        return new TinyORM<WorkoutPlan>(WorkoutPlan.class).readOne(jdbcTemplate.queryForRowSet(sql, userId, userId));
	}
	
	@Override
	public List<WorkoutPlan> getWorkOutPlansForIds(long trainerId, long clientId) {
		String sql = "SELECT workoutplan_id, client_id, trainer_id, days_of_week, title, body, completed FROM workout_plan WHERE (client_id = ? OR trainer_id=?) AND (client_id = ? OR trainer_id=?)";
        return new TinyORM<WorkoutPlan>(WorkoutPlan.class).readAll(jdbcTemplate.queryForRowSet(sql, trainerId, trainerId, clientId, clientId));
	}
	
	@Override
	public void createWorkoutPlan(WorkoutPlan workoutPlan) {
		jdbcTemplate.update("INSERT INTO workout_plan (trainer_id, client_id, title, body, days_of_week) "
				+ "VALUES (?,?,?,?,?)", workoutPlan.getTrainerId(), workoutPlan.getClientId(), workoutPlan.getTitle(), workoutPlan.getMessage(), workoutPlan.getDaysOfWeek());
	}
	
	
}
