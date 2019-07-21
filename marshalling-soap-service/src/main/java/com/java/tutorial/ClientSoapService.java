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

@Stateless(name="ClientSoapService")
@WebService
public class ClientSoapService implements IClientLocal, IClientRemote {

	@WebMethod(operationName="newClient")
	public ClientDTO createNewClient(@WebParam(name="id") Integer id, 
									 @WebParam(name="name") String name) throws Exception {
		ClientDTO myNewClient = new ClientDTO();
		ClientDTO clientToReturn = null;
		
		myNewClient.setId(id);
		myNewClient.setName(name);
		
		
		ByteArrayOutputStream clientOutputStream = new ByteArrayOutputStream();
		Unmarshaller unmarshaller = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ClientDTO.class);
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			marshaller.marshal(myNewClient, new OutputStreamWriter(clientOutputStream));
			
			//Deserialization
			unmarshaller = jaxbContext.createUnmarshaller();
			clientToReturn = (ClientDTO) unmarshaller.unmarshal(new ByteArrayInputStream(clientOutputStream.toByteArray()));
			
		} catch(JAXBException ex) {
			ex.printStackTrace();
		}
		
		return clientToReturn;
	}
}
