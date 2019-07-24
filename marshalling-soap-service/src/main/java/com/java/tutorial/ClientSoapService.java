package com.java.tutorial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import com.java.tutorial.IClientLocal;
import com.java.tutorial.IClientRemote;


@Stateless(name="ClientSoapService")
@WebService()
public class ClientSoapService implements IClientLocal, IClientRemote {

	@WebMethod(operationName="newClient")
	public ClientDTO createNewClient(@WebParam(name="id") Integer id, 
									 @WebParam(name="name") String name,
									 @WebParam(name="registerDate") String registerDate) throws Exception {
		ClientDTO myNewClient = new ClientDTO();
		ClientDTO clientToReturn = null;
		
		myNewClient.setId(id);
		myNewClient.setName(name);
		myNewClient.setRegisterDate(LocalDate.parse(registerDate));
		
		
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
	
	private void clientToXmlAndConsoleOutputWithJaxb(ClientDTO client, String filePath) {
		try {

			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ClientDTO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			//Serialization to file
			jaxbMarshaller.marshal(client, file);
			
			//Serialization to standard output
			jaxbMarshaller.marshal(client, System.out);

      } catch (JAXBException e) {
    	  e.printStackTrace();
      }
	}
	
	private void clientFromXmlToJavaClass(String filePath) {
		 try {

			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ClientDTO.class);

			//Deserialization
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ClientDTO singleClient = (ClientDTO) jaxbUnmarshaller.unmarshal(file);
			
			System.out.println(singleClient);

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
	}
	
	private void clientValidationWithXSD(ClientDTO singleClient, String xsdPath) {
		try {
			
			/* 
			 * Load Schema XSD Validator
			 **/
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(xsdPath));
			
		  /* 
		   * Context is created and used to create sources for single client
		   **/
		  JAXBContext jaxbContext = JAXBContext.newInstance(ClientDTO.class);
		  JAXBSource jaxbSourceClient = new JAXBSource(jaxbContext, singleClient);
		  
		  
		  /*
		   * Validator initialized
		   **/
		  Validator clientValidator = schema.newValidator();
		  
		  try {
			  clientValidator.validate(jaxbSourceClient);
			  System.out.println("Client Data Validation Ok!!! - Ready To Send!!!");
			  
		  } catch(SAXException ex) {
			  ex.printStackTrace();
		  }
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void clientMarShallingUsingXSD(ClientDTO singleClient, String xsdPath) {
		try {
			/* 
			 * Load Schema XSD Validator
			 **/
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(xsdPath));
			
			/* 
			 * Context is created and used to create sources for single client
			 **/
			JAXBContext jaxbContext = JAXBContext.newInstance(ClientDTO.class);
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
			marshaller.setSchema( schema );
			
			/** XD Schema use a validation handler for validate the objects
			 **/
			
			marshaller.setEventHandler( new MyValidationEventHandler() );
			try
			{
			    marshaller.marshal(singleClient, System.out);
			}
			catch( JAXBException ex )
			{
			    ex.printStackTrace();
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * To execute parsing dataBinding execute in console:
	 * -b: Use a databinding file
	 * -d: Existing directory where put the generated java source code
	 * -wsdl: URL of WSDL to parse
	 * xjc -b custom_client_data_binding.xml -d generateSources -wsdl <URL_WSDL>
	 * */
	
	public static void main(String args[]) {
		String filePath = "/home/patternsdesign/projects/clientInXML.xml";
		String clientXsdValidatorFile = "/home/patternsdesign/projects/client_validation.xsd";
		ClientSoapService clientSoapService = new ClientSoapService();
		ClientDTO mySingleClient = null;
		mySingleClient = new ClientDTO();
		
		mySingleClient.setId(3);
		mySingleClient.setName("Pako");
		mySingleClient.setRegisterDate(LocalDate.of(2019, 1, 12));
		
		//clientSoapService.clientToXmlAndConsoleOutputWithJaxb(mySingleClient, filePath);
		//clientSoapService.clientFromXmlToJavaClass(filePath);
		//clientSoapService.clientValidationWithXSD(mySingleClient, clientXsdValidatorFile);
		//clientSoapService.clientMarShallingUsingXSD(mySingleClient, clientXsdValidatorFile);
	}
}
