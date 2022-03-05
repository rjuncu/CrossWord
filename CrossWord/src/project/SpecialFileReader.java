package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SpecialFileReader {
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> clues = new ArrayList<String>();
	private int dim;
	
	public SpecialFileReader(String fileName) {
		try(BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\project\\"+fileName)))
		{
			String line = br.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			int xdim = Integer.parseInt(tokenizer.nextToken(" "));
			int ydim = Integer.parseInt(tokenizer.nextToken(" "));
			this.dim = xdim; 
			
			for(int i = 0; i<xdim; i++) {
				line = br.readLine();
				if (line == null) {
					break;
				}else {
					this.list.add(line);
				}
			}
			while(line!=null) {
				line = br.readLine();
				this.clues.add(line);
			}

		}catch(FileNotFoundException fnf) {
			System.out.println("File not found "+fileName);
		}catch(IOException e) {
			System.out.println("I/O Exception");
		}
	}
	
	public ArrayList<String> getList(){
		return this.list;
	}
	
	public ArrayList<String> getClues(){
		return this.clues;
	}
	
}
