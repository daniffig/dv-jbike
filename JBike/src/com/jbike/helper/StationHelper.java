package com.jbike.helper;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

import com.jbike.model2.Station;

public class StationHelper {

	public static Marker toMarker(Station station) {
		return new Marker(new LatLng(station.getLatitude(), station.getLongitude()), station.toString(), station,
				station.getState().getIcon(), "http://maps.google.com/mapfiles/ms/micons/msmarker.shadow.png");
	}
}
