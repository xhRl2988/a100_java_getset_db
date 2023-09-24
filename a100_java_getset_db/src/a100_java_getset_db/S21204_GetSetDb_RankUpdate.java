package a100_java_getset_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class PeopleUpdate {
	private int hakbun;		
	private int kor;			
	private int eng;			
	private int math;	
	private int sum;	
	private double avg;	
	private int rank;			
	public int cnt;

	public int getHakbun() {
		return hakbun;
	}
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSum() {
		return getKor() + getEng() + getMath();
	}
	public void setSum(int sum) {
		this.sum=sum;
	}
	public double getAvg() {
		return  (double)getSum() / 3;
	}
	public void setAvg(double avg) {
		this.avg=avg;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	void printScore() {
		System.out.printf(" %3d %3d %3d %3d %3d %3d %3.2f %3d \n",
				cnt, hakbun, kor, eng, math, getSum(), getAvg(), rank );
	}
}
public class S21204_GetSetDb_RankUpdate {
	public static void RankUpdate() {
		Scanner input = new Scanner(System.in);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;
		int num_count=0;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "system";
		String pw = "1234";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩 성공");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 접속");
			
			sql="select count(*) from sungil_jumsu_tbl";
			pstmt= conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			num_count=rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("등록된 자료 : "+num_count+"건");
		
		PeopleUpdate stu[]=new PeopleUpdate[num_count];
		try {
			sql="select * from sungil_jumsu_tbl";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			
			int i_cnt=0;
			while(rs.next()) {
				stu[i_cnt]=new PeopleUpdate();
				
				stu[i_cnt].cnt=i_cnt+1;
				stu[i_cnt].setHakbun(rs.getInt("hakbun"));
				stu[i_cnt].setKor(rs.getInt("kor"));
				stu[i_cnt].setEng(rs.getInt("eng"));
				stu[i_cnt].setMath(rs.getInt("math"));
				stu[i_cnt].setSum(rs.getInt("sum"));
				stu[i_cnt].setAvg(rs.getInt("avg"));
				stu[i_cnt].setRank(rs.getInt("rank"));
				i_cnt++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//등수 재 작업
		for (int i=0; i<stu.length; i++) {
			int rank=1;
			for (int j=0; j<stu.length; j++) {
				if(stu[i].getSum() < stu[j].getSum()) rank++;
			}
			stu[i].setRank(rank);
		}
		System.out.println("==================출력==================");
		System.out.printf(" NO    학번 국어 영어 수학 합계   평균 석차  \n");
		System.out.println("=======================================");
		for (int i=0; i<stu.length; i++) {
			stu[i].printScore();
		}try {
			for(int i=0; i<stu.length; i++) {
				sql = "update sungil_jumsu_tbl set sum=?,avg=?,rank=? where hakbun=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, stu[i].getSum());
				pstmt.setDouble(2, stu[i].getAvg());
				pstmt.setInt(3, stu[i].getRank());
				pstmt.setInt(4, stu[i].getHakbun());
				
				pstmt.executeUpdate();
			}
			System.out.println("============================");
			System.out.println("석차 재 설정 : "+stu.length+"건 완료");
		
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}		
}
