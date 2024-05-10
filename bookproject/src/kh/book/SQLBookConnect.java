package kh.book;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLBookConnect 
{	
	 public static Connection makeConnection() throws FileNotFoundException, IOException 
	 {
		 //오라클 데이터베이스 정보(주소, 유저, 패스워드)
		 String filePath = "D:/doitjava/bookproject/src/kh/book/db.properties"; //  /로 바꿔줌
		 Properties properties = new Properties();
		 properties.load(new FileReader(filePath));
		 String url = properties.getProperty("url");
		 String user = properties.getProperty("user");
		 String password = properties.getProperty("password");
		 
		 Connection con =null;
		 
		 //오라클에서 제공해주는 jdbc 라이브러리를 load 시켜야함
		 try 
		 {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("데이터베이스 드라이버 로드 성공");
			 con = DriverManager.getConnection(url,user,password);
			// System.out.println("데이터베이스 접속 성공");
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
