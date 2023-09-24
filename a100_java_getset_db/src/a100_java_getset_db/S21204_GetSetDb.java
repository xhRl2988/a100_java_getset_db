package a100_java_getset_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class S21204_GetSetDb {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
			
		int new_num=0;
		do {
			
		System.out.println("============================================");
		System.out.println("********** 수행평가 조회 및 등록(수정) **********");
		System.out.println("============================================");
		System.out.println("   1.등록된 전체 학생의 점수를 조회합니다(등록순)");		
		System.out.println("   2.등록된 전체 학생의 점수를 조회합니다(석차순)");		
		System.out.println("   3.등록된 학생을 선택하여 조회합니다.");				
		System.out.println("   4.학생의 점수를 입력하니다");						
		System.out.println("   5.등록된 점수의 석차를 재 설정합니다.");				
		System.out.println("   6.등록된 학생을 선택하여 삭제합니다.");				
		System.out.println("   0.작업을 종료합니다");							
		System.out.println("--------------------------------------------");
		System.out.print("작업 번호를 선택하세요?");
		new_num=sc.nextInt();
		
		if(new_num==1) {
			S21204_GetSetDb_Inquiry Iq = new S21204_GetSetDb_Inquiry();
	       Iq.Inquiry();
			}else if(new_num==2) {
				S21204_GetSetDb_InquiryRank IR = new S21204_GetSetDb_InquiryRank();
				IR.InquiryRank();
			}else if(new_num==3) {
				S21204_GetSetDb_InquiryChoice IC = new S21204_GetSetDb_InquiryChoice();
				IC.InquiryChoice();
			}else if(new_num==4) {
				S21204_GetSetDb_Insert In = new S21204_GetSetDb_Insert();
				In.Insert();
			}else if(new_num==5) {
				S21204_GetSetDb_RankUpdate RU = new S21204_GetSetDb_RankUpdate();
				RU.RankUpdate();
			}else if(new_num==6) {
				S21204_GetSetDb_DeleteChoice RC = new S21204_GetSetDb_DeleteChoice();
				RC.DeleteChoice();
			}else if(new_num==0) {
				System.out.println("작업을 정지합니다.");
				break;
			}else {
				System.out.println("숫자를 잘못 입력하셨습니다. 다시 입력해주세요");
			}
		
		}while(true);
		
		
		//다른방법
//		int i_num = input.nextInt();
//		if(i_num==1) {
//			S21204_GetSetDb_Inquiry.Inquiry();
//		}else if(i_num==2) {
//			S21204_GetSetDb_InquiryRank.InquiryRank();
//		}else if(i_num==3) {
//			S21204_GetSetDb_InquiryChoice.InquiryChoice();
//		}else if(i_num==4) {
//			S21204_GetSetDb_Insert.Insert();
//		}else if(i_num==5) {
//			S21204_GetSetDb_RankUpdate.RankUpdate();
//		}else if(i_num==6) {
//			S21204_GetSetDb_DeleteChoice.DeleteChoice();
//		}else if(i_num==0) {
//			System.out.println("작업을 정지합니다.");
//			break;
//		}
	
	}
}
