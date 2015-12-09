package com.jbike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jbike.model.Station;
import com.jbike.navigation.NavigationBean;

@ManagedBean(name = "stationBean")
@ApplicationScoped
public class StationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Station> stations;
	
	// Esto hay que pasarlo al StationView.
	private Station station;

	private String name;

	@ManagedProperty("#{navigationBean}")
	private NavigationBean navigationBean;

	@PostConstruct
	public void init() {
		stations = new ArrayList<Station>(Station.stations.values());
	}

	public String addStation() {
		return "stations/list";
	}

	public void editStation(Station station) {
		this.setStation(station);

		navigationBean.redirect("/stations/new");
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}
}
