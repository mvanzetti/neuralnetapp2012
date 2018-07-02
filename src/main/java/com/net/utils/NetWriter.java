package com.net.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NetWriter {

	private String _filePath;
	
	public NetWriter(String filePath) {
		
		_filePath = filePath;
	}
		
	public void Write() {
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter(_filePath, true));
		    
		    System.out.println();
		    out.newLine();	    
		    
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Write(String string) {
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter(_filePath, true));
		    
		    System.out.println(string);
		    out.write(string);
		    out.newLine();	    
		    
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
