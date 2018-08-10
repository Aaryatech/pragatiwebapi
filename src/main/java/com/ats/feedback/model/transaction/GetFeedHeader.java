package com.ats.feedback.model.transaction;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class GetFeedHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fb_id")
	private int fbId;

	private String fbDate;
	private String jobcardNo;
	private int saId;
	private int custId;
	private String custName;
	private String contactNo;
	private String vehRegNo;
	private int delStatus;
	private int status;
	private String remark;
	private int relManId;
	private int companyId;

	private String saName;
	private String userName;

	@Transient
	List<GetFeedDetail> getFeedDetailList;

	public int getFbId() {
		return fbId;
	}

	public void setFbId(int fbId) {
		this.fbId = fbId;
	}

	public String getFbDate() {
		return fbDate;
	}

	public void setFbDate(String fbDate) {
		this.fbDate = fbDate;
	}

	public String getJobcardNo() {
		return jobcardNo;
	}

	public void setJobcardNo(String jobcardNo) {
		this.jobcardNo = jobcardNo;
	}

	public int getSaId() {
		return saId;
	}

	public void setSaId(int saId) {
		this.saId = saId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getVehRegNo() {
		return vehRegNo;
	}

	public void setVehRegNo(String vehRegNo) {
		this.vehRegNo = vehRegNo;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getRelManId() {
		return relManId;
	}

	public void setRelManId(int relManId) {
		this.relManId = relManId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getSaName() {
		return saName;
	}

	public void setSaName(String saName) {
		this.saName = saName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<GetFeedDetail> getGetFeedDetailList() {
		return getFeedDetailList;
	}

	public void setGetFeedDetailList(List<GetFeedDetail> getFeedDetailList) {
		this.getFeedDetailList = getFeedDetailList;
	}

	@Override
	public String toString() {
		return "GetFeedHeader [fbId=" + fbId + ", fbDate=" + fbDate + ", jobcardNo=" + jobcardNo + ", saId=" + saId
				+ ", custId=" + custId + ", custName=" + custName + ", contactNo=" + contactNo + ", vehRegNo="
				+ vehRegNo + ", delStatus=" + delStatus + ", status=" + status + ", remark=" + remark + ", relManId="
				+ relManId + ", companyId=" + companyId + ", saName=" + saName + ", userName=" + userName
				+ ", getFeedDetailList=" + getFeedDetailList + "]";
	}

}