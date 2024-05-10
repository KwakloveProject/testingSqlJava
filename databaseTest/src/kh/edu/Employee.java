package kh.edu;

import java.util.Date;
import java.util.Objects;

public class Employee 
{
	private int employeeId;    
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber; 
	private Date hireDate;      
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
	private int departmentId;
	//생성자
	public Employee(int employeeId, String firstName, String lastName, String email, String phoneNumber, Date hireDate,
			String jobId, double salary, double commissionPct, int managerId, int departmentId) 
	{
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.jobId = jobId;
		this.salary = salary;
		this.commissionPct = commissionPct;
		this.managerId = managerId;
		this.departmentId = departmentId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return employeeId == other.employeeId;
	}
	@Override
	public String toString() {
		return " [" + employeeId +"\t" + firstName +"\t"+ lastName+"\t"
				+ email+"\t" + phoneNumber +"\t" + hireDate+"\t" + jobId+"\t"
				+ salary +"\t" + commissionPct+"\t" + managerId +"\t"
				+ departmentId + "]";
	}
	
	
	







}
