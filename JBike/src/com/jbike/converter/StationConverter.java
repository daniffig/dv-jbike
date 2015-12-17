package com.jbike.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.jbike.model2.Station;

@FacesConverter(value = "stationConverter")
public class StationConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		//return Station.stations.get(Integer.valueOf(arg2));
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Station) arg2).getId().toString();
	}

}
