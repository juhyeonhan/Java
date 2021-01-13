import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Sp{
 private String spname;
 private int price;
 BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
 public Sp() throws IOException {
  System.out.print("상품명:");
  spname=in.readLine();
  System.out.print("가격:");
  price=Integer.parseInt(in.readLine());
 }
 public String getSpname() {
  return spname;
 }
 public void setSpname(String spname) {
  this.spname = spname;
 }
 public int getPrice() {
  return price;
 }
 public void setPrice(int price) {
  this.price = price;
 }
}

class Db1 {
	private Connection conn;

	public Db1() {
		String url = "jdbc:mysql://localhost:3306/EXAMPLE?serverTimezone=UTC";
		String user = "root";
		String pass = "hanjuhyun10";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void insert(Sp p) {
		String sql = "insert into sp values(?,?)";
		try {
			PreparedStatement ptm = conn.prepareStatement(sql);
			ptm.setString(1, p.getSpname());
			ptm.setInt(2, p.getPrice());
			ptm.executeUpdate();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void select() {
		String sql = "select * from sp";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1) + ":" + rs.getInt(2));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int select_f(String spname) {
		String sql = "select price from sp where spname=?";
		int price = 0;
		try {
			PreparedStatement ptm = conn.prepareStatement(sql);
			ptm.setString(1, spname);
			ResultSet rs = ptm.executeQuery();
			if (rs.next()) {
				price = rs.getInt(1);
			}
			rs.close();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}

	public void update(String spname, String newname) {
		String sql = "update sp set spname=? where spname=?";
		try {
			PreparedStatement ptm = conn.prepareStatement(sql);
			ptm.setString(1, newname);
			ptm.setString(2, spname);
			ptm.executeUpdate();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(String spname, int price) {
		String sql = "update sp set price=? where spname=?";
		try {
			PreparedStatement ptm = conn.prepareStatement(sql);
			ptm.setInt(1, price);
			ptm.setString(2, spname);

			ptm.executeUpdate();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(String spname) {
		String sql = "delete from sp where spname=?";
		try {
			PreparedStatement ptm = conn.prepareStatement(sql);
			ptm.setString(1, spname);

			ptm.executeUpdate();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class Ex07 {

 public static void main(String[] args) throws NumberFormatException, IOException {
  BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
  Db1 db=new Db1();
  while(true) {
   System.out.print("1.상품등록 2.조회 3.수정 4.삭제 0.종료");
   int s=Integer.parseInt(in.readLine());
   if(s==1) {
    db.insert(new Sp());
   }
   if(s==2) {
    db.select();
   }
   if(s==3) {
    System.out.print("상품명:");
    String spname=in.readLine();
    System.out.print("1.상품명 수정 2.가격수정");
    int ss=Integer.parseInt(in.readLine());
    if(ss==1) {
     System.out.print("새로운상품명:");
     String newname=in.readLine();
     db.update(spname,newname);
    }
    if(ss==2) {
     System.out.print("가격:");
     int price=Integer.parseInt(in.readLine());
     db.update(spname,price);
    }
     
   }
   if(s==4) {
    System.out.print("상품명:");
    String spname=in.readLine();
    db.delete(spname);
   }
   if(s==0) {
    break;
   }
  }
  System.out.println("프로그램을 종료합니다.");

 }

}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Pan {
	private String name;
	private String spname;
	private int count;
	private int price;
	private int total;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public Pan() throws IOException {
		System.out.print("이름:");
		name = in.readLine();
		System.out.print("상품명:");
		spname = in.readLine();
		System.out.print("수량:");
		count = Integer.parseInt(in.readLine());
		Db1 db = new Db1();
		price = db.select_f(spname);
		total = count * price;
	}

	public void disp() {
		// 홍길동님 사과(7)개 가격은 ????입니다.
		System.out.println(name + "님 " + spname + "(" + count + ")개 가격은 " + total + "입니다.");
	}
}

public class ProductManagement {

	public static void main(String[] args) throws IOException {
  //BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
  new Pan().disp();

 }

}