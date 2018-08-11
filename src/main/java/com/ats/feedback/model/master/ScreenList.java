package com.ats.feedback.model.master;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ScreenList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "screen_id")
	private int screenId;
	private String screenNo;

	private String queNo;
	private String title;
	private int companyId;

	@Transient
	List<Question> queList;

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public String getScreenNo() {
		return screenNo;
	}

	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}

	public String getQueNo() {
		return queNo;
	}

	public void setQueNo(String queNo) {
		this.queNo = queNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public List<Question> getQueList() {
		return queList;
	}

	public void setQueList(List<Question> queList) {
		this.queList = queList;
	}

	@Override
	public String toString() {
		return "ScreenList [screenId=" + screenId + ", screenNo=" + screenNo + ", queNo=" + queNo + ", title=" + title
				+ ", companyId=" + companyId + ", queList=" + queList + "]";
	}

}
