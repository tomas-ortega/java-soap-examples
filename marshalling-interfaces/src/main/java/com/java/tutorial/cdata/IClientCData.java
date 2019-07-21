package com.java.tutorial.cdata;

import com.java.tutorial.ClientWithCDataDTO;

public interface IClientCData {
   ClientWithCDataDTO createNewClient(Integer id, String name) throws Exception;
}
