package example;

import java.util.Scanner;

public class Practice {

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);

		System.out.print("소문자 알파벳 하나를 입력하시오>>");
		char ch = scan.next().charAt(0);
		for (char i=ch;i>='a';i--) {
			for(char j='a'; j<=i; j++) {
				System.out.print(j);
			}
			System.out.println("");
		}
	}

}
