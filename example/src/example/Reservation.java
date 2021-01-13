package example;

import java.util.Scanner;

public class Reservation {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("전체 객실수를 입력하세요.");
		int rooms = scan.nextInt();
		System.out.println("전체 객실수는 "+rooms+"개 입니다.");
		
		int select = 0;
		int roomnumber;
		String [] name = new String[rooms];
		
		while(select != 4) {
			System.out.println("=============================================");
			System.out.println("<< 1.입실, 성함 입력    2.퇴실   3.전체현황    4.프로그램 종료  >>");
			System.out.println("=============================================");
			
			select = scan.nextInt();
			switch(select) {
			case 1:
				System.out.println("방 번호를 입력해 주세요");
				roomnumber = scan.nextInt();
				if (name[roomnumber-1]== null) {
					System.out.println("성함을 입력해 주세요");
					name[roomnumber-1] = scan.next();
					System.out.println(roomnumber+"호실, "+name[roomnumber-1]+"님이 예약되었습니다.");
				}else {
					System.out.println(roomnumber+"호실은 이미 예약되었습니다.");
				}break;
				
			case 2:
				System.out.println("퇴실할 방 번호를 입력해 주세요");
				roomnumber = scan.nextInt();
				if (name[roomnumber-1]!=null) {
					System.out.println(name[roomnumber-1]+"님, 퇴실되었습니다.");
					name[roomnumber-1] = null;
				}else {
					System.out.println(roomnumber+" 호실은 아무도 투숙하지 않습니다.");
				}break;
				
			case 3:
				for (int i = 0; i < rooms; i++) {
					if (name[i]==null) {
						System.out.println(i+1+" 호실은 비어있습니다.");
					}else {
						System.out.println(i+1+"호실의 투숙객 : "+name[i]);
					}
				}break;
				
			case 4:
				System.out.println("프로그램을 종료합니다.");
				break;
				
			default : 
				System.out.println("입력된 숫자에 해당되는 항목이 없습니다. 1~4까지의 숫자를 입력해주세요.");
			}
		}
		System.out.println("안녕히 가세요");
		System.exit(0);

	}

}
