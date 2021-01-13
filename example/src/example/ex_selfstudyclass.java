package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Fruits{
	String name;
	int price;
	int count;
	int result;
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				
	
	public Fruits() throws IOException {
		System.out.println("과일=");
		 name = in.readLine();
		System.out.println("수량=");
		count = Integer.parseInt(in.readLine());
	}
	
	public void disp() {
		if (name.equals("사과")) {
			price = 1000;
			}
		if (name.equals("바나나")) {
			price = 2000;
		}
		if (name.equals("배")) {
			price = 3000;
		}
		result = price * count;
		System.out.println("가격:"+price);
		System.out.println("금액:"+result);
				
	}
	
} 

public class ex_selfstudyclass {

	public static void main(String[] args) throws IOException {
		Fruits[]f = new Fruits[50];
		for (int i=0; i<f.length; i++) {
			f[i]=new Fruits();
			f[i].disp();
		}
	}

}
