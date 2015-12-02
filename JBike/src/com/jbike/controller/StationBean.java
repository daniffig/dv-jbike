package com.jbike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jbike.model.Station;

@ManagedBean(name = "stationBean")
@ApplicationScoped
public class StationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Station> stations;
	private Station station;

	private String name;
	
	private final static String[] states;
	
	static {
		states = new String[2];
		states[0] = "Available";
		states[1] = "Under construction";
	}

	@PostConstruct
	public void init() {
		stations = new ArrayList<Station>();
		
		stations.add(new Station(1, "Plaza Moreno", "Calle 12 964, La Plata, Buenos Aires", "-34.920314, -57.953833", 1));
		stations.add(new Station(2, "Plaza San Martín", "Avenida 7 902, La Plata, Buenos Aires", "-34.914732, -57.949658", 1));
		stations.add(new Station(3, "Plaza Italia", "Plaza Italia 129, La Plata, Buenos Aires", "-34.911381, -57.954861", 2));
		stations.add(new Station(4, "Estación de trenes", "Avenida 1 558, La Plata, Buenos Aires", "-34.904771, -57.949443", 1));
		stations.add(new Station(5, "Terminal de ómnibus", "Terminal de Ómnibus de La Plata, La Plata, Buenos Aires", "-34.905416, -57.954571", 1));
	}

	public String addStation() {
		return "stations/list";
	}

	public String editStation(Station station) {
		return null;
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
			//mex.printStackTrace();
		}

		return "stations/list";
	}

	public List<String> getStates() {
		return Arrays.asList(states);
	}
}
