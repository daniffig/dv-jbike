package com.jbike.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jbike.mailer.GMailMailHandler;
import com.jbike.model.Movement;
import com.jbike.model.MovementState;
import com.jbike.model.Penalization;
import com.jbike.model.User;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.interfaces.MovementDao;
import com.jbike.persistence.interfaces.PenalizationDao;
import com.jbike.persistence.interfaces.UserDao;

public class AddPenalizationJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		UserDao userDAO                 = FactoryDao.getUserDao();
		MovementDao movementDAO         = FactoryDao.getMovementDao();
		PenalizationDao penalizationDAO = FactoryDao.getPenalizationDao();

		for (User user : userDAO.findAll()) {
			List<Movement> movements = movementDAO.findAllByUserAndState(user, MovementState.CONFIRMED);

			if (movements.size() > 0) {
				Movement lastMovement = movements.get(movements.size() - 1);
				Calendar calendar = Calendar.getInstance();

				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, 1);

				Penalization penalization = new Penalization(user, calendar.getTime(), "Bike not returned.");

				if (penalizationDAO.save(penalization)) {
					try {
						GMailMailHandler.send(user.getEmail(), "You've been penalized", String.format(
								"You've been penalized for not returning %s on time", lastMovement.getBike().toString()));
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
