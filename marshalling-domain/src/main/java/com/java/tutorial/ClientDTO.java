package com.java.tutorial;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "ClientDTO")
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "id", required = true)
	private Integer clientId;
	
	@XmlElement(name = "name", required = true)
	private String clientName;
	
	@XmlElement(name = "registerDate", required = false)
	//@XmlJavaTypeAdapter(AdapterDate.class)
	private LocalDate registerDate;
	
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
	
	@XmlTransient
	public LocalDate getRegisterDate() {
		return this.registerDate;
	}
	
	public void setRegisterDate(LocalDate currentRegisterDate) {
		this.registerDate = currentRegisterDate;
	}
}
