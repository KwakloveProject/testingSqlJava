package kh.book;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class BookMain
{
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Connection con = SQLBookConnect.makeConnection(); 
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
						case 1: // 책정보보기
							selectALLBook();
							break;
						case 2: // 책정보입력하기
							Books books =inputBook();
							insertBook(books);
							break;
						case 3: // 책정보조회하기
							searchBook();
							break;
						case 4: // 책정보수정하기
							updateBook();
							break;
						case 5: // 책정보삭제하기
							deleteBook();
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
	
	private static void deleteBook() 
	{
		selectALLBook();
		System.out.println("삭제 책 제목 입력>>");
		String title = sc.nextLine();	
		
		Connection con =null;
		PreparedStatement pstmt = null;
		try 
		{
			con =SQLBookConnect.makeConnection();
			String sql = "delete from books where title=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, title);
			int i =pstmt.executeUpdate();
			
			if(i == 1) 
			{
				System.out.println(title+"책이 삭제했습니다.");
			}
			else 
			{
				System.out.println(title+"책이 삭제실패하셨습니다.");
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

	private static void updateBook() 
	{
		Books books =searchBook();
		System.out.print("update Title("+books.getTitle()+")>>");
		String title = sc.nextLine();
		System.out.print("update publisher("+books.getPublisher()+")>>");
		String publisher = sc.nextLine();
		System.out.print("update year("+books.getYear()+")>>");
		String year = sc.nextLine();
		System.out.print("update price("+books.getPrice()+")>>");
		int price = sc.nextInt();
		sc.nextLine();
		
		Connection con =null;
		PreparedStatement pstmt = null;
		try 
		{
			con =SQLBookConnect.makeConnection();
			String sql = "update books set title=? , publisher=?,year=?,price=? where title =?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, publisher);
			pstmt.setString(3, year);
			pstmt.setInt(	4,    price);
			pstmt.setString(5, books.getTitle());
			int i =pstmt.executeUpdate();

			if(i == 1) 
			{
				System.out.println(books.getTitle()+"책이 수정했습니다.");
			}
			else 
			{
				System.out.println(books.getTitle()+"책이 수정실패하셨습니다.");
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

	private static Books searchBook() 
	{
		//수정할 책을 먼저 보여준다. 
		System.out.println("찾고자 하는 책제목입력>>");
		String title =sc.nextLine();
		
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Books books =null;
		try 
		{
			con =SQLBookConnect.makeConnection();
			String sql = "SELECT *from books where title = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, title);
			rs=pstmt.executeQuery();

			if(rs.next()) 
			{
				int book_id = rs.getInt("book_id");
				String _title = rs.getString("title");
				String publisher = rs.getString("publisher");
				String year = rs.getString("year");
				int price = rs.getInt("price");
				 books =new Books(book_id, _title, publisher, year, price);
				System.out.println(books.toString());
			}
			else 
			{
				System.out.println(title+"책이 없습니다");
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
	return books;
		
	}

	

	private static Books inputBook() 
	{
		System.out.println("input title>>");
		String title = sc.nextLine();
		System.out.println("input publisher>>");
		String publisher = sc.nextLine();
		System.out.println("input year>>");
		String year = sc.nextLine();
		System.out.println("input price>>");
		int price = sc.nextInt();
		sc.nextLine();
		Books books = new Books(0, title, publisher, year, price);
		return books;
	}

	private static void insertBook(Books books) 
	{
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try 
		{
			con =SQLBookConnect.makeConnection();
			String sql = "INSERT INTO books VALUES (books_seq.NEXTVAL,?,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, books.getTitle());
			pstmt.setString(2, books.getPublisher());
			pstmt.setString(3, books.getYear());
			pstmt.setInt(4,    books.getPrice());
			int i =pstmt.executeUpdate();
			
			if(i == 1) 
			{
				System.out.println(books.getTitle()+"책이 입력성공했습니다.");
			}
			else 
			{
				System.out.println(books.getTitle()+"책이 입력실패했습니다.");
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

	public static void selectALLBook() 
	{
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try 
		{
			con =SQLBookConnect.makeConnection();
			String sql = "SELECT * from books" ;
			pstmt =con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) 
			{
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String publisher = rs.getString("publisher");
				String year = rs.getString("year");
				int price = rs.getInt("price");
				Books books =new Books(book_id, title, publisher, year, price);
				System.out.println(books.toString());
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
		System.out.println("\tBook Market Menu");
		System.out.println("**********************************************");
		System.out.println("1. 책정보보기");
		System.out.println("2. 책정보입력하기");
		System.out.println("3. 책정보조회하기");
		System.out.println("4. 책정보수정하기");
		System.out.println("5. 책정보삭제하기");
		System.out.println("**********************************************");

	}

}
