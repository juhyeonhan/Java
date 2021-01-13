package ex6.array;
import java.util.Scanner;

public class array2_다차원배열2 {

	public static void main(String[] args) {
		
		
		//int kor1, kor2, kor3;
		//int[] kors = new int[3*3]; //1학년 국어1~3, 2학년 국어1~3, 3학년 국어1~3
		int[][]kors = new int[3][3];
		int total;
		float avg;
		int menu;
		
		Scanner scan = new Scanner(System.in);
				
		//kor1 = 0;
		//kor2 = 0;
		//kor3 = 0;
		
		for(int j=0; j<3; j++)
			for(int i = 0; i<3; i++)
				//kors[3*j+i] = 0;
				kors[j][i] = 0;
			
		종료 : //라벨을 만들어줌
		while(true) {
			
			/*-----------------메인메뉴----------*/	
			
			System.out.println(" -------\"메인메뉴\"-------");
			System.out.println("\t1.성적입력");
			System.out.println("\t2.성적출력");
			System.out.println("\t3.종료");
			System.out.println("\t>");
			menu = scan.nextInt(); //메뉴 값을 입력받으면 선택된 번호 실행
			
			
			switch(menu) {
			
			case 1: //메뉴에 1이 입력되면 실행
				/*-----------------성적입력----------*/	
				
				System.out.println(" -------\"성적입력\"-------");
				
//				do {
//					System.out.println("국어1:");
//					kor1 = scan.nextInt();
//					
//					if(kor1<0 || 100<kor1)
//						System.out.println("성적범위(0~100)를 벗어났습니다.");
//					
//					
//				}while(kor1<0 || 100<kor1);
//				
//				
//				
//				do {
//					System.out.println("국어2:");
//					kor2 = scan.nextInt();
//					
//					if(kor2<0 || 100<kor2)
//						System.out.println("성적범위(0~100)를 벗어났습니다.");
//					
//					
//				}while(kor2<0 || 100<kor2);
//				
//				
//			
//				do {
//					System.out.println("국어3:");
//					kor3 = scan.nextInt();
//					
//					if(kor3<0 || 100<kor3)
//						System.out.println("성적범위(0~100)를 벗어났습니다.");
//					
//					
//				}while(kor3<0 || 100<kor3);
			
				for(int j=0; j<3; j++)
					for(int i=0; i<3; i++)
						do {
							System.out.printf("%d학년 국어%d:",j+1, i+1);
							kors[j][i] = scan.nextInt();
							
							if(kors[j][i]<0 || 100<kors[j][i])
								System.out.println("성적범위(0~100)를 벗어났습니다.");
							
							
						}while(kors[j][i]<0 || 100<kors[j][i]);
					
				
				
				
				break;
			
			
			
			case 2: //메뉴에 2가 입력되면 실행
				/*-----------------성적출력----------*/
				
				
				
				System.out.println(" -------\"성적출력\"-------");
				
				
				for(int j=0; j<3; j++ ) {
					
					//total = kor1 + kor2 + kor3;
					total = kors[j][0]+kors[j][1]+kors[j][2];
					avg = total / 3.0f;
					
					
					System.out.printf("<%d학년 국어성적>----------\n", j+1);
					
					
					//for(int n=1; n<=3; n++) 1부터 시작할땐 n을 씀
					for(int i=0; i<3; i++)
						System.out.printf("\t국어%d : %3d\n", i+1, kors[j][i]); //\t국어%d에i+1대입,%3d\n에 kor1대입 
						//System.out.printf("\t국어%d : %3d\n", 3-i, kor1); //출력번호를 3->2->1로 할때
						
						
						
						
					System.out.printf("\t총점 : %3d\n", total);
					System.out.printf("\t평균 : %6.2f\n", avg); //전체 4자리 중 둘째 소수점까지
					
					
					System.out.println(" \\--------------------\\");
					
					
					
					
				}
				
				
				break;
			
			case 3://메뉴에 3이 입력되면 실행
				break 종료; //라벨을 이용하여 switch문 빠져나감
				//System.exit(0); 프로그램 강제종료
			
			
			default :
				System.out.println("입력 오류 : 1~3까지만 입력할 수 있습니다.");
			
			
			
			
			
			}
			

		}
		
		System.out.println("good bye~");
	}

}


