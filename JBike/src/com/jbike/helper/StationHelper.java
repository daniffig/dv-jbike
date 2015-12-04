package com.jbike.helper;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

import com.jbike.model.Station;

public class StationHelper {

	public static Marker toMarker(Station station) {
		return new Marker(StationHelper.toLatLng(station.getCoordinates()), station.toString(), station,
				station.getState().getIcon(), "http://maps.google.com/mapfiles/ms/micons/msmarker.shadow.png");
	}

	public static LatLng toLatLng(String coordinates) {
		String[] latLng = coordinates.split(",");

		return new LatLng(Double.parseDouble(latLng[0]), Double.parseDouble(latLng[1]));
	}
}
