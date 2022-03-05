package exercise1;

import java.util.*;

public class Arrays {

	public static void main(String[] args) {
		Random rand = new Random();
		int[] arr = new int[20];
		for(int i = 0; i<20; i++) {
			arr[i]=rand.nextInt(11);
			//System.out.println("hers arr "+arr[i]);
		}
		
		int[] negArr = new int[arr.length];
		for(int i = 0; i<arr.length; i++) {
			negArr[i] = -arr[i];
			System.out.println("heres negArr "+ negArr[i]);
		}
		
	}
}
