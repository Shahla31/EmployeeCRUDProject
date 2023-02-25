package com.demo.CRUD.DTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="emp2_table")
public class EmployeeDTO implements Serializable
{
		@Id
		@GenericGenerator(name = "ref" , strategy = "increment")
		@GeneratedValue(generator = "ref")
		@Column(name = "empno")
		 private long empno;
		@Column(name = "ename")
		 private String ename;
		@Column(name = "sal")
		 private int sal;
		@Column(name = "job")
		 private String job;
		@Column(name = "deptno")
		 private int deptno ;
		 
		 public EmployeeDTO() {
			System.out.println(this.getClass().getSimpleName()+" created");
		}

		public long getEmpno() {
			return empno;
		}

		public void setEmpno(long empno) {
			this.empno = empno;
		}

		public String getEname() {
			return ename;
		}

		public void setEname(String ename) {
			this.ename = ename;
		}

		public int getSal() {
			return sal;
		}

		public void setSal(int sal) {
			this.sal = sal;
		}

		public String getJob() {
			return job;
		}

		public void setJob(String job) {
			this.job = job;
		}

		public int getDeptno() {
			return deptno;
		}

		public void setDeptno(int deptno) {
			this.deptno = deptno;
		}

		@Override
		public String toString() {
			return "EmployeeDTO [empno=" + empno + ", ename=" + ename + ", sal=" + sal + ", job=" + job + ", deptno="
					+ deptno + "]";
		}
}
