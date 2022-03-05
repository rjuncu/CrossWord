package project;

import java.io.*;
import java.util.Properties;

public class GetPropertyValues {
	
	private String fileName;
	private String solution;
	
	public GetPropertyValues() throws Exception{
		Properties prop = new Properties();
		try(FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"\\config.properties")) {
			prop.load(input);
			fileName = prop.getProperty("filename");
			solution = prop.getProperty("solution");
		}catch(IOException e) {
			System.out.println("I/O Exception");
			e.printStackTrace();
		}
		
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String getSolution() {
		return solution;
	}
}
