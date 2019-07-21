package com.java.tutorial;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

public class MyCharacterEscapeHandler implements CharacterEscapeHandler {
	/*
	 * @param ch The array of characters.
	 * @param start The starting position.
	 * @param length The number of characters to use.
	 * @param isAttVal true if this is an attribute value literal.
	 */
	public void escape(char[] buf, int start, int len, boolean isAttValue,
		Writer out) throws IOException {
		StringWriter buffer = new StringWriter();
	
	for (int i = start; i < start + len; i++) {
		buffer.write(buf[i]);
	}
	
	String st = buffer.toString();

	out.write(st);
	}
}
