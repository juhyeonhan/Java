import java.util.InputMismatchException;
import java.util.Scanner;



public class Reservation {

	String[][] seat = new String[4][8]; //좌석을 표시 할 배열

	char rn; //좌석의 열을 표시할 변수

	int row_int, col, system; //좌석에 따른 배열의 행번호, 열번호, 기능동작

	boolean confirm; //반복문을 탈출하기 위한 변수

	String con_char, name, c_name, row, q; 

        //이름을 확인받을 변수, 이름을 받을 변수, 취소에 쓸 이름, 열이름, 예약여부표시

        String pw = "password";

        //pw = 관리자 비밀번호

	public void Interface(){//메인 인터페이스

		System.out.println("안녕하세요. 좌석 예약 시스템입니다.");

		System.out.println("번호를 선택하세요");

		System.out.println("예약(1),취소(2),좌석조회(3),종료(4)");

		Scanner scan = new Scanner(System.in);

                try{

		     system = scan.nextInt();

	             switch(system){

                     case 00:Admin();break;

		     case 1 :Reservation();break;

		     case 2 :Cancel();break;

		     case 3 :Reference();break;

		     case 4 :System.out.println("시스템을 종료합니다.");break;

		     }
                }

               catch(InputMismatchException e){

                     System.out.println("잘못입력하셨습니다.");

               }

	}

	public void Admin(){ //관리자모드

		System.out.print("관리자 비밀번호를 입력하세요 : ");

		Scanner scan = new Scanner(System.in);

		if(scan.nextLine().equals(pw)){

			System.out.println("관리자 로그인 되었습니다.");

			confirm = true;

			do{

                                try{

				     System.out.print("취소(1),좌석조회(2),종료(3) : ");

				     system = new Scanner(System.in).nextInt();

				     switch(system){

				     case 1:AdminCancel();break;

				     case 2:AdminRef();	break;

				     case 3:System.out.println("관리자모드 종료");

						confirm = false; break;

				     }
                                }

                                catch(InputMismatchException e){

                                     System.out.println("잘못입력하셨습니다.");

                                }

			}while(confirm);

		}else{

			System.out.println("비밀번호가 일치하지 않습니다.\n");

		}

	}

	public void Initialize(){//초기화

		for(int i=0;i<4;i++){

			for(int j=0;j<8;j++){

				seat[i][j]="____"; 

			}

		}

	}

	public void AdminRef(){//관리자 조회

		rn = 'A';

		for(int i=0;i<4;i++){

			System.out.print(rn+"열 ");

			col = 1;

			rn++;

			for(int j=0;j<8;j++){

				System.out.print(col + seat[i][j] + " " );

				col++;

			}

			System.out.println();

		}

	}

	public void Reference(){//조회

		rn = 'A';

		for(int i=0;i<4;i++){

			System.out.print(rn+"열 ");

			col = 1;

			rn++;

			for(int j=0;j<8;j++){

                                q=seat[i][j].equals("____")?" □ ":" ■ ";

				System.out.print(col + q);

				col++;

			}

			System.out.println();

		}

	}

        //예약

	public void Reservation(){

		Reference();

		System.out.print("이름을 입력해주세요 : ");

		//이름 입력

		do{

			confirm = true;

			Scanner scan = new Scanner(System.in);

			name = scan.nextLine();

			System.out.println("이름을 확인해주세요.");

			System.out.print("맞으면 Y, 틀리면 N : ");

			con_char = scan.next();

			confirm=(con_char.equals("Y")||con_char.equals("y"))?false:true;

		}while(confirm);

		//열 선택

		do{

			confirm = true;

			System.out.print("열을 선택하세요(A~D) : ");

                        Scanner scan = new Scanner(System.in);

			try{

			row = scan.next();

			}catch(Exception e){

				System.out.println("A~D만 입력하세요");

			}

			if(row.equals("A")||row.equals("a")){

				row_int=0;	

				confirm = false;

			}

			else if(row.equals("B")||row.equals("b")){

				row_int=1;

				confirm = false;

			}

			else if(row.equals("C")||row.equals("c")){

				row_int=2;

				confirm = false;

			}

			else if(row.equals("D")||row.equals("d")){

				row_int=3;

				confirm = false;

			}

			else{

				System.out.println("A~D만 입력하세요");

			}

		}while(confirm);

                //좌석번호 선택

		do{

			confirm = true;

			Scanner scan = new Scanner(System.in);

			try{

				System.out.print("좌석 번호를 선택해주세요. 1~8 : ");

				col = scan.nextInt();

				if(col>=1 && col<=8){

					if(seat[row_int][col-1].equals("____")){

						seat[row_int][col-1]=name;

						confirm = false;

					}else{

						System.out.println("이미 예약된 자리입니다.");

					}

				}else{

					System.out.println("좌석은 1~8번까지 입니다.");

				}

			}catch(Exception e){

				System.out.println("좌석은 1~8번까지 입니다.");

			}

		}while(confirm);

		System.out.println(seat[row_int][col-1]+"님 "+row+"열"+col+"번 으로 예약이 완료되었습니다.");

		Reference();

	}

	//취소

	public void Cancel(){

		System.out.println("이름을 입력하세요");

		Scanner scan = new Scanner(System.in);

		c_name = scan.nextLine();

		for(int i=0;i<4;i++){

			for(int j=0;j<8;j++){

				if(seat[i][j].equals(c_name)){

					seat[i][j]="____";

					System.out.println("취소가 완료되었습니다.");

					Reference();

				}

			}

		}

	}

        //관리자 전용 취소

	public void AdminCancel(){ 

		System.out.println("이름을 입력하세요");

		Scanner scan = new Scanner(System.in);

		c_name = scan.nextLine();

		for(int i=0;i<4;i++){

			for(int j=0;j<8;j++){

				if(seat[i][j].equals(c_name)){

					seat[i][j]="____";

					System.out.println("취소가 완료되었습니다.");

					AdminRef();

				}

			}

		}

	}

	public static void main(String[] args) {

		//프로그램 시작시 사용자 이름 묻기

		//1.조회 2.예약 3.취소 4.종료(기능)

		//누가예약했는지 저장하세요. 이름

		//예약한 곳에 또다시 예약할 수 없게 해야함

		//예약 방법은 1.열선택 2.자리번호 선택

		Reservation movie = new Reservation();

		movie.Initialize();

		while(true){

			movie.Interface();

			if(movie.system == 4)break;

		}

	}//main end

}//class end
