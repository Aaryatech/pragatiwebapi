package com.ats.feedback.model.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Count {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int status1;
	private int status2;
	private int status3;
	private int status4;

	public int getStatus1() {
		return status1;
	}

	public void setStatus1(int status1) {
		this.status1 = status1;
	}

	public int getStatus2() {
		return status2;
	}

	public void setStatus2(int status2) {
		this.status2 = status2;
	}

	public int getStatus3() {
		return status3;
	}

	public void setStatus3(int status3) {
		this.status3 = status3;
	}

	public int getStatus4() {
		return status4;
	}

	public void setStatus4(int status4) {
		this.status4 = status4;
	}

	@Override
	public String toString() {
		return "Count [status1=" + status1 + ", status2=" + status2 + ", status3=" + status3 + ", status4=" + status4
				+ "]";
	}

	

}
