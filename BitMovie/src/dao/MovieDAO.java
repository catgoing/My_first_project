package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import frame.SignIn;
import vo.MovieVO;
import vo.ReservVO;

public class MovieDAO {
	
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@114.204.160.132:3690:xe";
	final String USER = "MEMTEST";
	final String PASSWORD = "memtest";
	public String VNAME, RTIME, RSEAT;
	public Date RDATE;
	public int RNUM;
	public double VGRADE;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public MovieVO movie = new MovieVO();
	public ReservVO reserv = new ReservVO();
	
	
	public MovieDAO() {
	
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
	
			e.printStackTrace();
		}
		
	}
	
	// 여기서부터 편집
	
	public void setMovie(int movieNo) {
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String sql = ""
					+ "SELECT VNAME, VTYPE, VINTRO, VDATE, VGRADE, VTIME "
					+ "FROM MOVIE "
					+ "WHERE VNO = ? "
					;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, movieNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				movie.setName(rs.getString("VNAME"));
				movie.setType(rs.getString("VTYPE"));
				movie.setInfo(rs.getString("VINTRO"));
				movie.setRelease(rs.getDate("VDATE"));
				movie.setGrade(rs.getDouble("VGRADE"));
				movie.setTime(rs.getString("VTIME"));
				
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
	}
	
	
	// SeatFrame에서 moviename, movietime, seats를 받아와,
	// DB의 RESERV테이블에 id, 영화제목, 상영일자, 좌석번호, 상영시간, 예매번호 등록
	public void reservMovie(String moviename, String moviedate, String movietime,  String seats, int price) {
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			String id = "";
			id = MemberDAO.id; // 관리자계정으로 회원 영화 예약할 경우 입력받은 아이디
			
			if("admin".equals(SignIn.ids)) {
				id = MemberDAO.id;
			} else	id = SignIn.ids;
			
			if(id != null) {
				
			
			String sql = ""
					+ "INSERT INTO RESERV(RID, RNAME, RDATE, RSEAT, RTIME, RNUM, RPRICE) "
					+ "VALUES (?, ?, ?, ?, ?, RESERVNO.NEXTVAL, ?) "
					;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, moviename);
			pstmt.setString(3, moviedate);
			pstmt.setString(4, seats);
			pstmt.setString(5, movietime);
			pstmt.setInt(6, price);
			
			pstmt.executeUpdate();
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		
	}
	
	
	// SeatFrame2에서 moviename, movietime, seats를 받아와,
	// DB의 RESERV테이블에서 id, 영화제목, 상영시간이 일치하는 정보 좌석 번호만 수정
	public void updateMovie(String moviename, String moviedate, String movietime, String seats, int rnum, String user_id) {
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
				
			String sql = ""
					+ "UPDATE 	RESERV "
					+ "   SET 	RNAME = ?"
					+ " 		,RDATE = ? "
					+ " 		,RTIME = ? "
					+ " 		,RSEAT = ? "
					+ "WHERE 	RNUM = ? "
					+ "AND 		RID = ? "
					
					;
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, moviename);
			pstmt.setString(2, moviedate);
			pstmt.setString(3, movietime);
			pstmt.setString(4, seats);
			pstmt.setInt(5, rnum);
			pstmt.setString(6, user_id);
			
			
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
	}
	
	
	
	// 예매한 영화 조회
	public String[][] reservedID(String id) {
		
		ArrayList<String[]> seatlist = new ArrayList<>();
		String[][] reserv_ID = null;
		
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			
			String sql = ""
					+ "SELECT LPAD(RNUM, 6, '0') AS RNUM, RNAME, RDATE, RTIME, RSEAT "
					+ "FROM RESERV "
					+ "WHERE RID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String[] str = new String[5];
				int n = 0;
				
				reserv.setRname(rs.getString("RNAME"));
				reserv.setRdate(rs.getDate("RDATE"));
				reserv.setRtime(rs.getString("RTIME"));
				reserv.setRnum(rs.getString("RNUM"));
				reserv.setRseat(rs.getString("RSEAT"));
				
				str[n++] = reserv.getRnum();
				str[n++] = reserv.getRname();
				str[n++] = reserv.getRdate().toString();
				str[n++] = reserv.getRtime();
				str[n++] = reserv.getRseat();

				seatlist.add(str);
				
				
			} 
			
			int listsize = seatlist.size();
			reserv_ID = new String[listsize][];
			
			for (int i = 0; i < listsize; i++) {
				reserv_ID[i] = seatlist.get(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		} 
		
		if(null == rs) {
			reserv_ID = new String[1][];
			
			String[] strr = {"", "", "예매내역이 없습니다", "", ""};
			
			reserv_ID[0] = strr;
			return  reserv_ID;
		}
			
			
		else return reserv_ID; 
		
	}
	
	
	public boolean reservedRnum(int rnum, String user_id) {
		
		boolean b = false;
		
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			
			String sql = ""
					+ "SELECT LPAD(RNUM, 6, '0') AS RNUM, RNAME, RDATE, RTIME, RSEAT, VNO "
					+ "FROM RESERV R, MOVIE M "
					+ "WHERE RNUM = ? "
					+ "AND RID = ? "
					+ "AND RNAME = VNAME";
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, rnum);
			pstmt.setString(2, user_id);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				reserv.setRname(rs.getString("RNAME"));
				reserv.setRnum(rs.getString("RNUM"));
				reserv.setRdate(rs.getDate("RDATE"));
				reserv.setRtime(rs.getString("RTIME"));
				reserv.setRseat(rs.getString("RSEAT"));
				movie.setNo(rs.getString("VNO"));
				
				b = true;

			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		} return b;
		
	}
	
	
	// 내가 예매한 영화 취소
	public void cancelMovie(int rnum) {
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
		
			String sql = ""
					+ "DELETE RESERV "
					+ "WHERE RNUM = ? "
					;
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, rnum);
			
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
	}
	
	
	
	// SeatFrame, SeatFrame2에서 moviename, movietime를 받고, 
	// 같은 영화, 같은 시간대 예매된 좌석을 DB의 RESETV 테이블에서  하나의 ROW로 얻어내어,
	// 좌석번호를 String RSEAT으로 얻어내서 리턴
	public String reservedSeat(String moviename, String moviedate, String movietime) {
		
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			
			String sql = ""
					+ "SELECT LISTAGG(RSEAT,',') WITHIN GROUP (ORDER BY RNAME) RSEAT "
					+ "FROM RESERV  "
					+ "WHERE RNAME = ? "
					+ "AND RTIME = ? "
					+ "AND RDATE = ? "
					;
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, moviename);
			pstmt.setString(2, movietime);
			pstmt.setString(3, moviedate);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				RSEAT = rs.getString("RSEAT");
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		} return RSEAT;
		
	}
	
	
	// SeatFrame2에서 moviename, movietime 받고, 
	// 내가 예매한 좌석을 DB에서 String RSEAT으로 얻어내서 리턴
	public String reservedSeat2(String moviename, String moviedate, String movietime, String id) {
		
		
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			System.out.println("reservedSeat2: " + id);
			
			String sql = ""
					+ "SELECT LISTAGG(RSEAT,',') WITHIN GROUP (ORDER BY RNAME) RSEAT "
					+ "FROM RESERV  "
					+ "WHERE RNAME = ? "
					+ "AND RTIME = ?"
					+ "AND RDATE = ?"
					+ "AND RID = ?"
					;
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, moviename);
			pstmt.setString(2, movietime);
			pstmt.setString(3, moviedate);
			pstmt.setString(4, id);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				RSEAT = rs.getString("RSEAT");
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		} return RSEAT;
		
	}
	
	
	public String movieStatisAud(String moviename) {
		
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			
			String sql = ""
					+ "SELECT LISTAGG(RSEAT,',') WITHIN GROUP (ORDER BY RNAME) RSEAT "
					+ "FROM RESERV  "
					+ "WHERE RNAME = ? "
					;
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, moviename);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				RSEAT = rs.getString("RSEAT");
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		} return RSEAT;
		
	}
	
	public String movieStatisSales(String moviename) {
		String sales = "";
		
		try {
			conn = DriverManager.getConnection(URL,	USER, PASSWORD);
			
			
			String sql = ""
					+ "SELECT NVL(TO_CHAR(SUM(RPRICE), '999,999,999,999'), 0) AS SALES "
					+ "FROM RESERV  "
					+ "WHERE RNAME = ? "
					;
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, moviename);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sales = rs.getString("SALES");
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		} return sales;
		
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
