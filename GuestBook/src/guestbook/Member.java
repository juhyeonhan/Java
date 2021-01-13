package guestbook;

import java.sql.Timestamp;

public class Member {
 private int no;
 private String title;
 private Timestamp wdate;
 private String name;
 private String pass;
 private String content;
 
 public int getNo() {
  return no;
 }
 public void setNo(int no) {
  this.no = no;
 }
 public String getTitle() {
  return title;
 }
 public void setTitle(String title) {
  this.title = title;
 }
 public Timestamp getWdate() {
  return wdate;
 }
 public void setWdate(Timestamp wdate) {
  this.wdate = wdate;
 }
 public String getName() {
  return name;
 }
 public void setName(String name) {
  this.name = name;
 }
 public String getPass() {
  return pass;
 }
 public void setPass(String pass) {
  this.pass = pass;
 }
 public String getContent() {
  return content;
 }
 public void setContent(String content) {
  this.content = content;
 }
// ------------------------------------------------------
//create table guestbook(no int primary key auto_increment,
//title varchar(80) not null, 
//wdate datetime not null,
//name varchar(10) not null,
//pass varchar(10) not null,
//content text not null);



}
