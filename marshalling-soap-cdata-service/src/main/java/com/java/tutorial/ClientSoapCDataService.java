package com.java.tutorial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;


import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.java.tutorial.cdata.IClientCDataLocal;
import com.java.tutorial.cdata.IClientCDataRemote;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;


@Stateless(name="ClientSoapCDataService")
@WebService
public class ClientSoapCDataService implements IClientCDataLocal, IClientCDataRemote {

	@WebMethod(operationName="newClient")
	public ClientWithCDataDTO createNewClient(@WebParam(name="id") Integer id, 
									 @WebParam(name="name") String name) throws Exception {
		ClientWithCDataDTO myNewClient = new ClientWithCDataDTO();
		ClientWithCDataDTO clientToReturn = null;
		
		myNewClient.setId(id);
		myNewClient.setName(name);
		
		
		ByteArrayOutputStream clientOutputStream = new ByteArrayOutputStream();
		Unmarshaller unmarshaller = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ClientWithCDataDTO.class);
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(CharacterEscapeHandler.class.getName(), 
										new MyCharacterEscapeHandler());
			
			marshaller.marshal(myNewClient, new OutputStreamWriter(clientOutputStream));
			
			//Deserialization
			unmarshaller = jaxbContext.createUnmarshaller();
			clientToReturn = (ClientWithCDataDTO) unmarshaller.unmarshal(new ByteArrayInputStream(clientOutputStream.toByteArray()));
			
		} catch(JAXBException ex) {
			ex.printStackTrace();
		}
		
		return clientToReturn;
	}
}
