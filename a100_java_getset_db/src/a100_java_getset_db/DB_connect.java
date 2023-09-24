package a100_java_getset_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB_connect {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "system";
		String pw = "1234";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩 성공");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 접속");
	
			String sql = "select * from emp_master";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getString("emp_id"));
				System.out.print(rs.getString("emp_name"));
				System.out.print(rs.getString("emp_job"));
				System.out.print(rs.getString("emp_grade"));
				System.out.print(rs.getString("emp_pay"));
				System.out.println();
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

