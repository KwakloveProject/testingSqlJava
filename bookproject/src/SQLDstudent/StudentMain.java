package SQLDstudent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentMain
{
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Connection con = SQLBookConnect.makeConnection(); 
		loginStudentId();
		
		boolean quit = false;
		int numberSelection=0;
		
			while (!quit) 
			{
				// 메인메뉴 표시하기
				menuIntroduction();
				System.out.print("메뉴 번호를 선택해주세요");
				numberSelection = sc.nextInt();
				sc.nextLine(); // 입력버퍼 클리어
				
				if (numberSelection < 1 || numberSelection > 6) 
				{
					System.out.println("1부터 6까지의 숫자를 입력하세요.");
				} 
				else 
				{
					switch (numberSelection) 
					{
					case 1: // 학생정보보기
						selectALLStudent();
						break;
					case 2: // 학생 정보입력하기
						Student student =inputStudent();
						insertStudent(student);
						break;
					case 3: // 책정보조회하기
						searchStudent();
						break;
					case 4: // 책정보수정하기
						updateStudent();
						break;
					case 5: // 책정보삭제하기
						deleteStudent();
						break;
					case 6: // 종료하기
						System.out.println("종료됩니다");
						quit=true;
						break;
					}
				}
				
			} // end of while
			System.out.println("The end");
		}
			
		
	
	private static void loginStudentId() 
	{
		//수정할 책을 먼저 보여준다. 
				
				
				Connection con =null;
				PreparedStatement pstmt = null;
				ResultSet rs =null;
				Student student =null;
				for(;;) 
				{
					System.out.println("아이디(이름)을 입력해주세요>>");
					String sd_name =sc.nextLine();
					try 
					{
						con =SQLStudentConnect.makeConnection();
						String sql = "SELECT *from student where sd_name = ?";
		//String sql = "SELECT *from student where id=? and password= ?";아이디 비번은 이렇게하면됨(주석문 참고)
						pstmt =con.prepareStatement(sql);
						pstmt.setString(1, sd_name);//아이디
						//pstmt.setString(2, 비밀번호);
						rs=pstmt.executeQuery();

						if(rs.next()) 
						{
							String _sd_name = rs.getString("sd_name");
							System.out.println(_sd_name+"님 환영합니다.");
							break;
						}
						else 
						{
							System.out.println(sd_name+"아이디가 존재하지않습니다.");
							
						}
					}
					
					catch (Exception e) {
						e.printStackTrace();
					}
				}
					
		
	}

	private static void deleteStudent() 
	{
		selectALLStudent();
		System.out.println("삭제 학생 이름 입력>>");
		String sd_name = sc.nextLine();	
		
		Connection con =null;
		PreparedStatement pstmt = null;
		try 
		{
			con =SQLStudentConnect.makeConnection();
			String sql = "delete from student where sd_name=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, sd_name);
			int i =pstmt.executeUpdate();
			
			if(i == 1) 
			{
				System.out.println(sd_name+"학생이 삭제했습니다.");
			}
			else 
			{
				System.out.println(sd_name+"학생이 삭제실패하셨습니다.");
			}
		
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
				try 
				{
					 if(pstmt!=null) 
					{
					pstmt.close();
					}
					 if(con!=null) 
					{
					con.close();
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		
		}
	}

	private static void updateStudent() 
	{
			
			Student student =searchStudent();
			if(student == null)
			{
				System.out.println("학생정보를 찾지못함");
				return;
			}		
			System.out.print("update sd_name("+student.getSd_name()+")>>");
			String sd_name = sc.nextLine();
			System.out.print("update sd_email("+student.getSd_email()+")>>");
			String sd_email = sc.nextLine();
			System.out.print("update sd_birthday("+student.getsd_birthday()+")>>");
			int sd_birthday = sc.nextInt();
			sc.nextLine();
			
			Connection con =null;
			PreparedStatement pstmt = null;
			try 
			{
				con =SQLStudentConnect.makeConnection();
				String sql ="update student set sd_name=?,sd_email=?,sd_birthday=? where sd_name =?";
				pstmt =con.prepareStatement(sql);
				pstmt.setString(1, sd_name);
				pstmt.setString(2, sd_email);
				pstmt.setInt(3, sd_birthday);
				pstmt.setString(4, student.getSd_name());
				int i =pstmt.executeUpdate();
				if(i == 1) 
				{
					System.out.println(student.getSd_name()+"학생이 수정했습니다.");
				}
				else 
				{
					System.out.println(student.getSd_name()+"학생이 수정실패하셨습니다.");
				}
				
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(pstmt!=null) 
					{
						pstmt.close();
					}
					if(con!=null) 
					{
						con.close();
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
		
			}
		
			
		
		
	}

	private static Student searchStudent() 
	{
		System.out.println("찾고자 하는 이름입력>>");
		String sd_name =sc.nextLine();
		
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Student student =null;
		try 
		{
			con =SQLStudentConnect.makeConnection();
			String sql = "SELECT *from student where sd_name = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, sd_name);
			rs=pstmt.executeQuery();

			if(rs.next()) 
			{
				int code=rs.getInt("code");
				int sd_num= rs.getInt("sd_num");
				String _sd_name = rs.getString("sd_name");
				String sd_email = rs.getString("sd_email");
				int sd_birthday = rs.getInt("sd_birthday");
				 student =new Student(code, sd_num, _sd_name, sd_email, sd_birthday);
				System.out.println(student.toString());
			}
			else 
			{
				System.out.println(sd_name+"학생이 없습니다");
				student =null;
			}
			
		}
		
		 catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
				try 
				{
					if(rs!=null) 
					{
					rs.close();
					}
					 if(pstmt!=null) 
					{
					pstmt.close();
					}
					 if(con!=null) 
					{
					con.close();
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		
		}
	return student;
		
	}

	

	private static Student inputStudent() 
	{
		
		System.out.println("input SD_NAME>>");
		String sd_name = sc.nextLine();
		System.out.println("input SD_EMAIL>>");
		String sd_email = sc.nextLine();
		System.out.println("input SD_BIRTHDAY>>");
		int sd_birthday = sc.nextInt();
		sc.nextLine();
		Student student = new Student(0, 0, sd_name, sd_email, sd_birthday);
		return student;
	}

	private static void insertStudent(Student student) 
	{
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try 
		{
			con =SQLStudentConnect.makeConnection();
			String sql = "INSERT INTO student VALUES (student_seq.NEXTVAL,student_seq.NEXTVAL,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, student.getSd_name());
			pstmt.setString(2, student.getSd_email());
			pstmt.setInt(3, student.getsd_birthday());
			int i =pstmt.executeUpdate();
			
			if(i == 1) 
			{
				System.out.println(student.getSd_name()+"학생이 생성되었습니다.");
			}
			else 
			{
				System.out.println(student.getSd_name()+"학생이 생성되지 않았습니다.");
			}
		
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
		finally 
		{
				try 
				{
					if(rs!=null) 
					{
					rs.close();
					}
					 if(pstmt!=null) 
					{
					pstmt.close();
					}
					 if(con!=null) 
					{
					con.close();
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		
		}
		
	}

	public static void selectALLStudent() 
	{
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try 
		{
			con =SQLStudentConnect.makeConnection();
			String sql = "SELECT * from student" ;
			pstmt =con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) 
			{
				int code=rs.getInt("code");
				int sd_num= rs.getInt("sd_num");
				String _sd_name = rs.getString("sd_name");
				String sd_email = rs.getString("sd_email");
				int sd_birthday = rs.getInt("sd_birthday");
				Student student =new Student(code, sd_num, _sd_name, sd_email, sd_birthday);
				System.out.println(student.toString());
			}
		}
		
	  catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		finally 
		{
				try 
				{
					if(rs!=null) 
					{
						rs.close();
					}
					else if(pstmt!=null) 
					{
						pstmt.close();
					}
					else if(con!=null) 
					{
						con.close();
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		
		}
	
	}

	public static void menuIntroduction() {
		System.out.println("**********************************************");
		System.out.println("\tStudentInfoMenu");
		System.out.println("**********************************************");
		System.out.println("1. 학생정보보기");
		System.out.println("2. 학생정보입력하기");
		System.out.println("3. 학생정보조회하기");
		System.out.println("4. 학생정보수정하기");
		System.out.println("5. 학생정보삭제하기");
		System.out.println("**********************************************");

	}

}
