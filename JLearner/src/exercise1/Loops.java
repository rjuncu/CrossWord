package exercise1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Loops {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter x:");
			int x = Integer.parseInt(br.readLine());
			System.out.println("Enter y:");
			int y = Integer.parseInt(br.readLine());
			double average = (x+y)/(2);
			System.out.println("Heres the result: "+Math.floor(average));
		}catch(Exception e) {
			System.out.println("Ooops");
		}
	}
}