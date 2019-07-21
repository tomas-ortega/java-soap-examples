package com.java.tutorial;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.txw2.annotation.XmlCDATA;

import java.io.Serializable;


@XmlRootElement
public class ClientWithCDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "id")
	private Integer clientId;
	
	@XmlElement(name = "name")
	private String clientName;
	
	public ClientWithCDataDTO() {
		this.clientId = null;
		this.clientName = null;
	}
	
	public void setId(Integer incomingId) {
		this.clientId = incomingId;
	}
	
	@XmlTransient
	public Integer getId() {
		return this.clientId;
	}
	
	public void setName(String incomingName) {
		this.clientName = incomingName;
	}
	
	@XmlTransient
	public String getName() {
		return this.clientName;
	}
	
	@XmlJavaTypeAdapter(value = AdapterCDATA.class)
	@XmlCDATA
	public String getClientInformationInCData() {
		return "AAA";
	}
}
