package com.ats.feedback.model.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_sa")
public class ServiceAdviser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sa_id")
	private int saId;

	private String saName;
	private String saMobile;
	private String joiningDate;
	private String userExp;
	private int delStatus;
	private int isBlock;
	private int companyId;
	private String expertIn;

	public int getSaId() {
		return saId;
	}

	public void setSaId(int saId) {
		this.saId = saId;
	}

	public String getSaName() {
		return saName;
	}

	public void setSaName(String saName) {
		this.saName = saName;
	}

	public String getSaMobile() {
		return saMobile;
	}

	public void setSaMobile(String saMobile) {
		this.saMobile = saMobile;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getUserExp() {
		return userExp;
	}

	public void setUserExp(String userExp) {
		this.userExp = userExp;
	}

	
	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(int isBlock) {
		this.isBlock = isBlock;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getExpertIn() {
		return expertIn;
	}

	public void setExpertIn(String expertIn) {
		this.expertIn = expertIn;
	}

	@Override
	public String toString() {
		return "ServiceAdviser [saId=" + saId + ", saName=" + saName + ", saMobile=" + saMobile + ", joiningDate="
				+ joiningDate + ", userExp=" + userExp + ", delStatus=" + delStatus + ", isBlock=" + isBlock
				+ ", companyId=" + companyId + ", expertIn=" + expertIn + "]";
	}

}
