package a100_java_getset_db;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class People {
	private int hakbun;		
	private int kor;			
	private int eng;			
	private int math;	
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
	public double getAvg() {
		return  (double)getSum() / 3;
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
public class S21204_GetSetDb_Insert {
	public static void Insert() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("몇명의 성적처리를 하나요(숫자입력)? ");
		int num = input.nextInt();
		
		People stu[] = new People[num];
		
		for(int i=0; i<stu.length; i++) {
			
			stu[i] = new People();	// 매우 중요!!! 배열은 인덱스 모두에 객체생성 후 참조배열 연계
			System.out.print("학번을 입력하세요: ");
			stu[i].setHakbun(input.nextInt());
			System.out.print("국어 점수를 입력하세요: ");
			stu[i].setKor(input.nextInt());
			System.out.print("영어 점수를 입력하세요: ");
			stu[i].setEng(input.nextInt());
			System.out.print("수학 점수를 입력하세요: ");
			stu[i].setMath(input.nextInt());
		}
		// 석차 부여, 같은 총점일 경우 같은 석차
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
			stu[i].cnt = i+1;
			stu[i].printScore();
		}
		// DB연결 후 입력된 자료 등록
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql;

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "system";
			String pw = "1234";
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("클래스 로딩 성공");
				conn = DriverManager.getConnection(url, id, pw);
				System.out.println("DB 접속");
				
				//setCharacterEncoding("UTF-8);
				for(int i=0; i<stu.length; i++) {
					sql = "insert into sungil_jumsu_tbl values (?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					
					//pstmt.setInt(1,Integer.parseInt(getParameter("custno")));
					pstmt.setInt(1, stu[i].getHakbun());
					pstmt.setInt(2, stu[i].getKor());
					pstmt.setInt(3, stu[i].getEng());
					pstmt.setInt(4, stu[i].getMath());
					pstmt.setInt(5, stu[i].getSum());
					pstmt.setDouble(6, stu[i].getAvg());
					pstmt.setInt(7, stu[i].getRank());
					
					pstmt.executeUpdate();
				}
				System.out.println("==================================================");
				System.out.println("DB 입력작업 성공:"+stu.length+"건 입력");
				
				pstmt.close();
				conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
