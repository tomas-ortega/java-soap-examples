package com.java.tutorial;

/**
 * Hello world!
 *
 */
public interface IClient {
   ClientDTO createNewClient(Integer id, String name) throws Exception;
}
