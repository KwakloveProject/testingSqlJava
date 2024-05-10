package kh.edu;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.spi.DirStateFactory.Result;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HrTest 
{
	public static void main (String [] args) throws SQLException 
	{
		Connection con = ConnectDatabase.makeConnection();
		//sql 쿼리문 (SELECT * from employees;) -> ▶ 결과물 ResultSet으로  가져옴
		//stmt.executeQuery(select 만 사용가능);
		//stmt.executeUpdate(select 빼고 다 사용가능);
		Statement stmt = con.createStatement();
		ResultSet rs= stmt.executeQuery("select * from employees order by salary desc");
		ArrayList<Employee> empList = new ArrayList<Employee>();
		//변수 선언
		int employeeId;    
		String firstName;
		String lastName;
		String email;
		String phoneNumber; 
		Date hireDate;      
		String jobId;
		double salary;
		double commissionPct;
		int managerId;
		int departmentId;
		//rs.next()를 할때마다 커서위치를 아래로 한칸씩 이동한다.
		while(rs.next()) 
		{
			employeeId = rs.getInt("EMPLOYEE_ID");
			firstName = rs.getString("FIRST_NAME");
			lastName = rs.getString("LAST_NAME");
			email = rs.getString("EMAIL");
			phoneNumber = rs.getString("PHONE_NUMBER");
			hireDate = rs.getDate("HIRE_DATE");
			jobId = rs.getString("JOB_ID");
			salary = rs.getDouble("SALARY");
			commissionPct = rs.getDouble("COMMISSION_PCT");
			managerId = rs.getInt("MANAGER_ID");
			departmentId = rs.getInt("DEPARTMENT_ID");
			Employee emp = new Employee(employeeId, firstName, lastName, email, phoneNumber, hireDate, jobId, salary, commissionPct, managerId, departmentId);
			empList.add(emp);
		}
		
		empListPrint(empList);
		System.out.println("the end");
	
	
	
	
	}

	private static void empListPrint(ArrayList<Employee> empList) 
	{
		for(Employee data : empList) 
		{
			System.out.println(data.toString());
		}
		
	}
	
	
}
