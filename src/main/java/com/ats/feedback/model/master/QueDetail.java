package com.ats.feedback.model.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_question_detail")
public class QueDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "que_detail_id")
	private int queDetailId;
	@Column(name = "que_no")
	private int queNo;
	@Column(name = "option1")
	private String option;
	@Column(name = "marking")
	private String marking;
	@Column(name = "is_accpt_notaccept")
	private int isAccptNotaccept;
	@Column(name = "del_status")
	private int delStatus;
	@Column(name = "company_id")
	private int companyId;

	public int getQueDetailId() {
		return queDetailId;
	}

	public void setQueDetailId(int queDetailId) {
		this.queDetailId = queDetailId;
	}

	public int getQueNo() {
		return queNo;
	}

	public void setQueNo(int queNo) {
		this.queNo = queNo;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getMarking() {
		return marking;
	}

	public void setMarking(String marking) {
		this.marking = marking;
	}

	public int getIsAccptNotaccept() {
		return isAccptNotaccept;
	}

	public void setIsAccptNotaccept(int isAccptNotaccept) {
		this.isAccptNotaccept = isAccptNotaccept;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "QueDetail [queDetailId=" + queDetailId + ", queNo=" + queNo + ", option=" + option + ", marking="
				+ marking + ", isAccptNotaccept=" + isAccptNotaccept + ", delStatus=" + delStatus + ", companyId="
				+ companyId + "]";
	}

}
