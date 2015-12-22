package com.jbike.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.jbike.controller.StationBean;
import com.jbike.model.Station;

@ManagedBean(name = "stationConverter")
@RequestScoped
public class StationConverter implements Converter {

	@ManagedProperty(value = "#{stationBean}")
	private StationBean stationBean;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null) {
			return null;
		}

		return stationBean.getStationDAO().findByPk(Long.valueOf(arg2));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Station) {
			return ((Station) arg2).getId().toString();
		}

		return "";
	}

	public StationBean getStationBean() {
		return stationBean;
	}

	public void setStationBean(StationBean stationBean) {
		this.stationBean = stationBean;
	}

}
