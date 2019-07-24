package com.java.tutorial;

import com.java.tutorial.ClientDTO;

/**
 * Hello world!
 *
 */
public interface IClient {
   ClientDTO createNewClient(Integer id, String name, String registerDate) throws Exception;
}
