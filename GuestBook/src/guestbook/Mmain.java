package guestbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Mmain {

 public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
  BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
  Db db=new Db();
  
  while(true) {
   System. out.print("1.글쓰기 2.목록보기 3.글보기 4.글삭제 0.종료?");
   int x=Integer.parseInt(in.readLine());
   if(x==1) {
    Member m=new Member();
    System.out.print("글제목:");
    m.setTitle(in.readLine());
    java.util.Date d=new java.util.Date();
    m.setWdate(new Timestamp(d.getTime()));
    System.out.print("작성자:");
    m.setName(in.readLine());
    System.out.print("비밀번호:");
    m.setPass(in.readLine());
    System.out.print("글내용:");
    m.setContent(in.readLine());
    db.insert(m);
   }
   if(x==2) {
    ArrayList<Member> al=db.select();
    for(Member i:al) {
     System.out.println(i.getNo()+"."+i.getTitle()
     +" "+i.getWdate()+" "+i.getName());
    }
   }
   if(x==3) {
    System.out.print("글번호:");
    Member m=db.view(Integer.parseInt(in.readLine()));
    System.out.println("글제목:"+m.getTitle());
    System.out.println("글쓴 일시:"+m.getWdate());
    System.out.println("글작성자:"+m.getName());
    System.out.println("글내용:"+m.getContent());
   }
   if(x==4) {
    System.out.print("글번호:");
    int no=Integer.parseInt(in.readLine());
    System.out.print("비밀번호:");
    String pass=in.readLine();
    boolean d=db.delete(no,pass);
    if(d)System.out.println("글이 삭제 되었습니다.");
    else System.out.println("글이 삭제 되지 않았습니디.");
   }
   if(x==0) {
    break;
   }
  }
  System.out.println("프로그램을 종료합니다.");

 }

}