package ex5.control;
import java.util.Scanner;

public class contral4_if {

	public static void main(String[] args) {
		
		
		int kor1, kor2, kor3;
		int total;
		float avg;
		int menu;
		
		Scanner scan = new Scanner(System.in);
				
		kor1 = 0;
		kor2 = 0;
		kor3 = 0;
		
		
		while(true) {
			
			/*-----------------메인메뉴----------*/	
			
			System.out.println(" -------\"메인메뉴\"-------");
			System.out.println("\t1.성적입력");
			System.out.println("\t2.성적출력");
			System.out.println("\t3.종료");
			System.out.println("\t>");
			menu = scan.nextInt(); //메뉴 값을 입력받으면 선택된 번호 실행
			
			
			if(menu==1) {
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
			}
			
			

			
			if(menu==2) {
				
				total = kor1 + kor2 + kor3;
				avg = total / 3.0f;
				
				/*-----------------성적출력----------*/
				
				System.out.println(" -------\"성적출력\"-------");
				
				
				
				
				//for(int n=1; n<=3; n++)
				for(int i=0; i<3; i++)
					System.out.printf("\t국어%d : %3d\n", i+1, kor1); //\t국어%d에i+1대입,%3d\n에 kor1대입 
					//System.out.printf("\t국어%d : %3d\n", 3-i, kor1); //출력번호를 3->2->1로 할때
					
					
					
					
				System.out.printf("\t총점 : %3d\n", total);
				System.out.printf("\t평균 : %6.2f\n", avg); //전체 4자리 중 둘째 소수점까지
				
				
				System.out.println(" \\--------------------\\");
				
			}
			
			if(menu==3) {
				break;
				//System.exit(0); 프로그램 강제종료
			}
		}
		System.out.println("good bye~");
	}

}

