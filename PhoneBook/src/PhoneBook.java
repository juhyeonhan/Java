import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Db {
	private Connection conn;

	public Db() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Db";
			String user = "root";
			String pass = "hanjuhyun10";
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Phone p) {
		String sql = "insert into phone values(?,?)";
		try {
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setString(1, p.getName());
			pmt.setString(2, p.getNo());
			pmt.executeUpdate();
			pmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void select() {
		try {
			Statement stm = conn.createStatement();
			String sql = "select * from phone";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1) + ":" + rs.getString(2));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String name) {
		String sql = "delete from phone where name=?";
		try {
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setString(1, name);
			pmt.executeUpdate();
			pmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String name, String no) {
		String sql = "update phone set no=? where name=?";
		try {
			PreparedStatement pmt = conn.prepareStatement(sql);
			pmt.setString(1, no);
			pmt.setString(2, name);
			pmt.executeUpdate();
			pmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

class Phone {
	private String name;
	private String no;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public Phone() throws IOException {
		System.out.print("이름:");
		name = in.readLine();
		System.out.print("전화번호:");
		no = in.readLine();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}

public class PhoneBook {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Db db = new Db();
		
		while (true) {
			System.out.print("1.등록 2.출력 3.삭제 4.수정 0.종료: ");
			int s = Integer.parseInt(in.readLine());
			if (s == 1) {
				db.insert(new Phone());
			}
			if (s == 2) {
				db.select();
			}
			if (s == 3) {
				System.out.print("이름:");
				String name = in.readLine();
				db.delete(name);
			}
			if (s == 4) {
				System.out.print("이름:");
				String name = in.readLine();
				System.out.print("전화번호:");
				String no = in.readLine();
				db.update(name, no);
			}
			if (s == 0) {
				break;
			}
		}
		System.out.println("프로그램을 종료합니다.");

	}

}
