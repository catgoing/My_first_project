package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import vo.Member_VO;

public class Member_DAO {

	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@114.204.160.132:3693:xe";
	final String USER = "MEMTEST";
	final String PASSWORD = "memtest";
	static public String id;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public Member_VO member = new Member_VO();
	
	public Member_DAO() {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}


	public Member_VO select(String id) {

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);


			String sql = ""
					+ "SELECT ID, NAME, PASSWORD, PHONE "
					+ "  FROM MEMBER"
					+ " WHERE ID = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);  

			rs = pstmt.executeQuery();

			if(rs.next()) {  
				member = new Member_VO();  
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setPhone(rs.getString("PHONE"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		//		      System.out.println(member);
		return member;
	}

	public StringBuilder select2() {
		StringBuilder sb = new StringBuilder();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);


			String sql = ""
					+ "SELECT ID, NAME, PASSWORD, PHONE "
					+ "  FROM MEMBER";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {  
				member = new Member_VO();  
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setPhone(rs.getString("PHONE"));
				sb.append(member.toString());
				//			            
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return sb;
	}


	public String select3(String id) {
		StringBuilder sb = new StringBuilder();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);


			String sql = ""
					+ "SELECT ID, NAME, PASSWORD, PHONE "
					+ "  FROM MEMBER"
					+ " WHERE ID = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);  

			rs = pstmt.executeQuery();

			if(rs.next()) {  
				member = new Member_VO();  
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setPhone(rs.getString("PHONE"));
				sb.append(member.toString());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return sb.toString();
	}


	public int insert(Member_VO member) {  
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = ""
					+ "INSERT INTO MEMBER "
					+ "       (ID, NAME, PASSWORD, PHONE) "
					+ "VALUES (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getPhone());

			result =  pstmt.executeUpdate(); 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return result;
	}


	public boolean login(String id, String password) {
		boolean result = false;
		Member_VO vo = new Member_VO();

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = ""
					+ "SELECT PASSWORD "
					+ "    FROM MEMBER "
					+ " WHERE ID = ?";

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getString(1).equals(password)) {
					vo = select(id);
					result = true;
				} else {
					JOptionPane.showMessageDialog(null, "일치하는 회원정보가 없습니다");;
					result = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}

	// 중복 아이디 확인 코드
	public boolean loginid(String id) {
		boolean result = false;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = ""
					+ "SELECT ID "
					+ "    FROM MEMBER "
					+ "WHERE ID = ?" ;

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();

			if(rs.next() == false) result = false;
			
			else result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return result;
	}



	public int update(Member_VO member) {
		int result = 0;

		try {

			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE MEMBER ");
			sb.append("   SET NAME = ? ");
			sb.append("     , PASSWORD = ? ");
			sb.append("     , PHONE = ? ");
			sb.append(" WHERE ID = ? ");


			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}

		return result;
	}


	public int delete(String id) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "DELETE FROM MEMBER WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}

		return result;
	}

	private void close(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
