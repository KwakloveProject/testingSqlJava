package kh.edu2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import kh.edu.ConnectDatabase;
import kh.edu.Employee;

public class HrTest 
{
	public static void main (String [] args) throws SQLException 
	{
		Connection con = ConnectDatabase2.makeConnection();
		//sql 쿼리문 (SELECT * from employees;) -> ▶ 결과물 ResultSet으로  가져옴
		//stmt.executeQuery(select 만 사용가능);
		//stmt.executeUpdate(select 빼고 다 사용가능);
		Statement stmt = con.createStatement();
		ResultSet rs= stmt.executeQuery("select * from usertbl order by birthYear desc");
		ArrayList<UserTBL> userList = new ArrayList<UserTBL>();
		//변수 선언
		String userId;    
		String userName;
		int birthYear;
		String addr;
		String mobile1; 
		String mobile2;      
		int height;
		Date mDate;
		//rs.next()를 할때마다 커서위치를 아래로 한칸씩 이동한다.
		while(rs.next()) 
		{
			userId = rs.getString("USERID");
			userName = rs.getString("USERNAME");
			birthYear = rs.getInt("BIRTHYEAR");
			addr = rs.getString("ADDR");
			mobile1 = rs.getString("MOBILE1");
			mobile2 = rs.getString("MOBILE2");
			height = rs.getInt("HEIGHT");
			mDate = rs.getDate("MDATE");
			UserTBL usertbl =new UserTBL(userId, userName, birthYear, addr, mobile1, mobile2, height, mDate) ;
			userList.add(usertbl);
		}
		
		userListPrint(userList);
		System.out.println("the end");
	
	
	
	
	}

	private static void userListPrint(ArrayList<UserTBL> userList) 
	{
		for(UserTBL data : userList) 
		{
			System.out.println(data.toString());
		}
		
	}
}
