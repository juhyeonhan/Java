package ex4.삼항연산자;
import java.util.Scanner;

public class operator {

	public static void main(String[] args) {
		
		
		int kor1, kor2, kor3;
		int total;
		float avg;
		
		Scanner scan = new Scanner(System.in);
				
		kor1 = 0;
		kor2 = 0;
		kor3 = 0;
		
		/*-----------------성적입력----------*/	
		
		System.out.println(" -------\"성적입력\"-------");
		System.out.println("국어1:");
		kor1 = scan.nextInt();
		
		
		//삼항연산자 : 앞의 조건이 참인지 거짓인지 구분하여 값을 출력 (true/false)
		int validate = (0<=kor1 && kor1<=100)?0:1;
		System.out.println(validate);
		
		
		
		System.out.println("국어2:");
		kor2 = scan.nextInt();
		System.out.println("국어3:");
		kor3 = scan.nextInt();
		
		
		total = kor1 + kor2 + kor3;
		avg = total / 3.0f;
			
		
		/*-----------------성적출력----------*/
		
		System.out.println(" -------\"성적출력\"-------");
		
		
		System.out.printf("\t국어1 : %3d\n", kor1);
		System.out.printf("\t국어2 : %3d\n", kor2);
		System.out.printf("\t국어3 : %3d\n", kor3);
		System.out.printf("\t총점 : %3d\n", total);
		System.out.printf("\t평균 : %6.2f\n", avg); //전체 4자리 중 둘째 소수점까지
		
		
		System.out.println(" \\--------------------\\");



	}

}
