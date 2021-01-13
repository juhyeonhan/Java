package ex5.control;
import java.util.Scanner;

public class contral2_dowhile {

	public static void main(String[] args) {
		
		
		int kor1, kor2, kor3;
		int total;
		float avg;
		
		Scanner scan = new Scanner(System.in);
				
		kor1 = 0;
		kor2 = 0;
		kor3 = 0;
		
		
		while(true) 
		{
			/*-----------------성적입력----------*/	
			
			System.out.println(" -------\"성적입력\"-------");
			
			do {
				System.out.println("국어1:");
				kor1 = scan.nextInt();
				
				if(kor1<0 || 100<kor1)
					System.out.println("성적범위(0~100)를 벗어났습니다.");
				
				
			}while(kor1<0 || 100<kor1);
			
			
			
			do {
				System.out.println("국어2:");
				kor2 = scan.nextInt();
				
				if(kor2<0 || 100<kor2)
					System.out.println("성적범위(0~100)를 벗어났습니다.");
				
				
			}while(kor2<0 || 100<kor2);
			
		
			
			do {
				System.out.println("국어3:");
				kor3 = scan.nextInt();
				
				if(kor3<0 || 100<kor3)
					System.out.println("성적범위(0~100)를 벗어났습니다.");
				
				
			}while(kor3<0 || 100<kor3);
			
			
			
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

}


