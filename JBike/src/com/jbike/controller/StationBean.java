package com.jbike.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jbike.model2.Station;
import com.jbike.model2.StationState;
import com.jbike.persistence.FactoryDao;
import com.jbike.persistence.interfaces.StationDao;

@ManagedBean(name = "stationBean")
@ApplicationScoped
public class StationBean {

	private StationDao stationDAO;

	@PostConstruct
	public void init() {
		this.setStationDAO(FactoryDao.getStationDao());
	}
	
	public boolean saveStation(Station station) {
		if (station.isNew()) {
			return this.getStationDAO().save(station);
		}
		else {
			return this.getStationDAO().update(station);
		}
	}

	public List<Station> getStations() {
		return this.getStationDAO().findAll();
	}

	public List<SelectItem> getStateOptions() {
		List<SelectItem> options = new ArrayList<SelectItem>();

		options.add(new SelectItem("", "Select One"));

		for (StationState state : StationState.values()) {
			options.add(new SelectItem(state));
		}

		return options;
	}

	public String sendMail() {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "localhost");

		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress("signin@jbike.com.ar"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("daniffig@gmail.com"));
			message.setSubject("Your JBike password");
			message.setText("Lalalalaal");

			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			return "stations/list";
			// mex.printStackTrace();
		}

		return "stations/list";
	}

	public StationDao getStationDAO() {
		return stationDAO;
	}

	public void setStationDAO(StationDao stationDAO) {
		this.stationDAO = stationDAO;
	}
}
