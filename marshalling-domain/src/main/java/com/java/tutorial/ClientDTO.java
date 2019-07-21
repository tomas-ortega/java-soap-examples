package com.java.tutorial;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "id")
	private Integer clientId;
	
	@XmlElement(name = "name")
	private String clientName;
	
	public ClientDTO() {
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
}
