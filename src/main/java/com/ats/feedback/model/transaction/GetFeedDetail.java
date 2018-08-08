package com.ats.feedback.model.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GetFeedDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fb_detail_id")
	private int fbDetailId;

	private int fbId;
	private int queNo;

	private String option;
	private String rating;
	private String fbDate;

	private int saId;
	private int status;
	private int delStatus;

	private String queText;

	public int getFbDetailId() {
		return fbDetailId;
	}

	public void setFbDetailId(int fbDetailId) {
		this.fbDetailId = fbDetailId;
	}

	public int getFbId() {
		return fbId;
	}

	public void setFbId(int fbId) {
		this.fbId = fbId;
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFbDate() {
		return fbDate;
	}

	public void setFbDate(String fbDate) {
		this.fbDate = fbDate;
	}

	public int getSaId() {
		return saId;
	}

	public void setSaId(int saId) {
		this.saId = saId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getQueText() {
		return queText;
	}

	public void setQueText(String queText) {
		this.queText = queText;
	}

	@Override
	public String toString() {
		return "GetFeedDetail [fbDetailId=" + fbDetailId + ", fbId=" + fbId + ", queNo=" + queNo + ", option=" + option
				+ ", rating=" + rating + ", fbDate=" + fbDate + ", saId=" + saId + ", status=" + status + ", delStatus="
				+ delStatus + ", queText=" + queText + "]";
	}

}
