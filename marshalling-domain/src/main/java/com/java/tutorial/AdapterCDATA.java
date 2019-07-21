package com.java.tutorial;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterCDATA extends XmlAdapter<String, String> {

	@Override
	public String unmarshal(String argChorizo) throws Exception {
		return "<![CDATA[" + argChorizo + "]]>";
	}

	@Override
	public String marshal(String argChorizo) throws Exception {
		return "<![CDATA[" + argChorizo + "]]>";
	}
	
}
