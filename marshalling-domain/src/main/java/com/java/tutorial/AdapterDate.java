package com.java.tutorial;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterDate extends XmlAdapter<String, LocalDate> {

	/** From internal system to external system*/
	public LocalDate unmarshal( String date ) throws Exception {
        return LocalDate.parse( date );
    }
 
	/** From external system to internal system*/
    public String marshal( LocalDate date ) throws Exception {
        return date.toString();
    }
}
