package com.ats.feedback.model.master;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "que_no")
	private int queNo;

	private String queText;
	private int queType;
	private String queAccept;
	private String queNonAccept;
	private String queRatingApply;
	private String inRange;
	private int delStatus;
	private int isBlock;
	private int companyId;

	@Transient
	List<QueDetail> queDetailList;

	public int getQueNo() {
		return queNo;
	}

	public void setQueNo(int queNo) {
		this.queNo = queNo;
	}

	public String getQueText() {
		return queText;
	}

	public void setQueText(String queText) {
		this.queText = queText;
	}

	public int getQueType() {
		return queType;
	}

	public void setQueType(int queType) {
		this.queType = queType;
	}

	public String getQueAccept() {
		return queAccept;
	}

	public void setQueAccept(String queAccept) {
		this.queAccept = queAccept;
	}

	public String getQueNonAccept() {
		return queNonAccept;
	}

	public void setQueNonAccept(String queNonAccept) {
		this.queNonAccept = queNonAccept;
	}

	public String getQueRatingApply() {
		return queRatingApply;
	}

	public void setQueRatingApply(String queRatingApply) {
		this.queRatingApply = queRatingApply;
	}

	public String getInRange() {
		return inRange;
	}

	public void setInRange(String inRange) {
		this.inRange = inRange;
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

	public List<QueDetail> getQueDetailList() {
		return queDetailList;
	}

	public void setQueDetailList(List<QueDetail> queDetailList) {
		this.queDetailList = queDetailList;
	}

	@Override
	public String toString() {
		return "Question [queNo=" + queNo + ", queText=" + queText + ", queType=" + queType + ", queAccept=" + queAccept
				+ ", queNonAccept=" + queNonAccept + ", queRatingApply=" + queRatingApply + ", inRange=" + inRange
				+ ", delStatus=" + delStatus + ", isBlock=" + isBlock + ", companyId=" + companyId + ", queDetailList="
				+ queDetailList + "]";
	}

}
