package com.jbike.model2;

public enum Gender{
	
	MALE,
	FEMALE;

	//Capitalize the name: "Male" | "Female"
	public String toString(){
		return name().charAt(0) + name().substring(1).toLowerCase();
	}
}