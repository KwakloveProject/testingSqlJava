package kh.edu2;

import java.util.Date;
import java.util.Objects;

import kh.edu.Employee;

public class UserTBL
{
	private String userId;    
	private String userName;
	private int birthYear;
	private String addr;
	private String mobile1; 
	private String mobile2;      
	private String jobId;
	private int height;
	private Date mDate;
	//생성자
	public UserTBL(String userId, String userName, int birthYear, String addr, String mobile1, String mobile2,
			 int height, Date mDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.birthYear = birthYear;
		this.addr = addr;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.height = height;
		this.mDate = mDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTBL other = (UserTBL) obj;
		return Objects.equals(userId, other.userId);
	}
	@Override
	public String toString() {
		return "[" + userId + userName + birthYear + addr
				 + mobile1  + mobile2 + height
				 + mDate + "]";
	}
	
}
