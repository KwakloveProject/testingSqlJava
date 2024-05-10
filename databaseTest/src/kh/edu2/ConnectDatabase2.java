package kh.edu2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase2 
{
	 public static Connection makeConnection() 
	 {
		 //오라클 데이터베이스 정보(주소, 유저, 패스워드)
		 String url ="jdbc:oracle:thin:@127.0.0.1:1521/xe";
		 String user = "sqlDB";// 실제로는 이렇게 보여주면안됨
		 String password ="sqldb";// 실제로는 이렇게 보여주면 안됨
		 
		 Connection con =null;
		 
		 //오라클에서 제공해주는 jdbc 라이브러리를 load 시켜야함
		 try 
		 {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 System.out.println("데이터베이스 드라이버 로드 성공");
			 con = DriverManager.getConnection(url,user,password);
			 System.out.println("데이터베이스 접속 성공");
		 }
		 catch (ClassNotFoundException e) 
		 {
			 e.printStackTrace();
			 System.out.println("데이터베이스 드라이버 로드 실패");
		 } 
		 catch (SQLException e) 
		 {
			 e.printStackTrace();
			 System.out.println("데이터베이스 연결 실패");
		 }
		 return con;
	 }
}
