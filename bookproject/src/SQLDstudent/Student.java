package SQLDstudent;

import java.util.Objects;

public class Student 
{
	private int code;
	private int sd_num;
	private String sd_name;
	private String sd_email;
	private int sd_birthday;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int code, int sd_num, String sd_name, String sd_email, int sd_birthday) {
		super();
		this.code = code;
		this.sd_num = sd_num;
		this.sd_name = sd_name;
		this.sd_email = sd_email;
		this.sd_birthday = sd_birthday;
	}
	

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getSd_num() {
		return sd_num;
	}
	public void setSd_num(int sd_num) {
		this.sd_num = sd_num;
	}
	public String getSd_name() {
		return sd_name;
	}
	public void setSd_name(String sd_name) {
		this.sd_name = sd_name;
	}
	public String getSd_email() {
		return sd_email;
	}
	public void setSd_email(String sd_email) {
		this.sd_email = sd_email;
	}
	public int getsd_birthday() {
		return sd_birthday;
	}
	public void setsd_birthday(int sd_birthday) {
		this.sd_birthday = sd_birthday;
	}
	
	@Override
	public String toString() {
		return "코드=" + code + ", 학번=" + sd_num + ", 이름=" + sd_name + ", 이메일=" + sd_email
				+ ", 생일=" + sd_birthday ;
	}

}
