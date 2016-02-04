package com.jbike.scheduler;

import java.sql.Date;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jbike.model.BikeState;
import com.jbike.model.Movement;
import com.jbike.model.MovementState;
import com.jbike.persistence.BikeDaoHibernate;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.MovementDaoHibernate;
import com.jbike.persistence.interfaces.BikeDao;
import com.jbike.persistence.interfaces.MovementDao;

public class RemoveMovementJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		BikeDao bikeDAO = FactoryDao.getBikeDao();
		MovementDao movementDAO = FactoryDao.getMovementDao();
		
		for (Movement movement : movementDAO.findAllByState(MovementState.NEW)) {
			Calendar dueDate = Calendar.getInstance();
			
			dueDate.setTime(movement.getCreatedAt());
			dueDate.add(Calendar.HOUR, 3);
			
			Date now = new Date(System.currentTimeMillis());
			
			if (now.after(dueDate.getTime())) {
				movement.setState(MovementState.CANCELLED);
				movement.getBike().setState(BikeState.AVAILABLE);
				
				bikeDAO.save(movement.getBike());
				movementDAO.save(movement);
				
				System.out.println(String.format("Movement %s cancelled.", movement.toString()));
			}			
		}		
	}

}
