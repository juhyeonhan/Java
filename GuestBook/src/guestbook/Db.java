package guestbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Db {
	Connection conn;

	public Db() {
		String url = "jdbc:mysql://localhost:3306/EXAMPLE?serverTimezone=UTC";
		String user = "root";
		String password = "hanjuhyun10";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			// System.out.println(conn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insert(Member m) {

		String sql = "insert into guestbook value(0,?,?,?,?,?)";
		try {
			PreparedStatement ptm = conn.prepareStatement(sql);
			ptm.setString(1, m.getTitle());
			ptm.setTimestamp(2, m.getWdate());
			ptm.setString(3, m.getName());
			ptm.setString(4, m.getPass());
			ptm.setString(5, m.getContent());
			ptm.executeUpdate();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Member> select() {
		ArrayList<Member> al = new ArrayList<Member>();
		String sql = "select * from guestbook order by no desc";
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Member m = new Member();
				m.setNo(rs.getInt("no"));
				m.setTitle(rs.getString("title"));
				m.setWdate(rs.getTimestamp("wdate"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setContent(rs.getString("content"));
				al.add(m);
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;

	}

	public Member view(int no) {
		Member m = null;
		String sql = "select * from guestbook where no=?";
		try {
			PreparedStatement ptm = conn.prepareStatement(sql);
			ptm.setInt(1, no);
			ResultSet rs = ptm.executeQuery();
			m = new Member();
			if (rs.next()) {
				m.setNo(rs.getInt("no"));
				m.setTitle(rs.getString("title"));
				m.setWdate(rs.getTimestamp("wdate"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("pass"));
				m.setContent(rs.getString("content"));
			}
			rs.close();
			ptm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m;

	}

	public boolean chk(int no, String pass) throws SQLException {
		String sql = "select pass from guestbook where no=?";
		PreparedStatement ptm = null;
		ResultSet rs = null;
		try {
			ptm = conn.prepareStatement(sql);
			ptm.setInt(1, no);
			rs = ptm.executeQuery();
			if (rs.next()) {
				String pass2 = rs.getString("pass");

				if (pass2.equals(pass)) {
					System.out.println("비밀번호가 일치 합니다.");
					return true;
				} else {
					System.out.println("비밀번호가 일치 하지 않습니다.");
					return false;
				}
			} else {
				System.out.println("해당 글이 존재 하지 않습니다.");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ptm.close();
			rs.close();
		}

		return false;

	}

	public boolean delete(int no, String pass) throws SQLException {
		if (!chk(no, pass))
			return false;

		String sql = "delete from guestbook where no=?";
		try {
			PreparedStatement ptm = conn.prepareStatement(sql);
			ptm.setInt(1, no);
			ptm.executeUpdate();
			ptm.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}
}