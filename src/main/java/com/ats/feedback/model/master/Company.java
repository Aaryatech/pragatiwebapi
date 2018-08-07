package com.ats.feedback.model.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	private int companyId;

	private String companyName;
	private String companyAdd;
	private String contactNo;
	private String compOfficeAdd;
	private String contactNoOffice;
	private String companyEmail;
	private int noOfScreen;
	private int delStatus;

	private int isBlock;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAdd() {
		return companyAdd;
	}

	public void setCompanyAdd(String companyAdd) {
		this.companyAdd = companyAdd;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCompOfficeAdd() {
		return compOfficeAdd;
	}

	public void setCompOfficeAdd(String compOfficeAdd) {
		this.compOfficeAdd = compOfficeAdd;
	}

	public String getContactNoOffice() {
		return contactNoOffice;
	}

	public void setContactNoOffice(String contactNoOffice) {
		this.contactNoOffice = contactNoOffice;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public int getNoOfScreen() {
		return noOfScreen;
	}

	public void setNoOfScreen(int noOfScreen) {
		this.noOfScreen = noOfScreen;
	}

	public int getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(int isBlock) {
		this.isBlock = isBlock;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyAdd=" + companyAdd
				+ ", contactNo=" + contactNo + ", compOfficeAdd=" + compOfficeAdd + ", contactNoOffice="
				+ contactNoOffice + ", companyEmail=" + companyEmail + ", noOfScreen=" + noOfScreen + ", delStatus="
				+ delStatus + ", isBlock=" + isBlock + "]";
	}

}
