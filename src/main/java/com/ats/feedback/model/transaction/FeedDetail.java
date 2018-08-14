package com.ats.feedback.model.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_fb_detail")
public class FeedDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fb_detail_id")
	private int fbDetailId;

	private int fbId;
	private int queNo;

	private String optionName;
	private String rating;
	private String fbDate;

	private int saId;
	private int status;
	private int delStatus;

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

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	@Override
	public String toString() {
		return "FeedDetail [fbDetailId=" + fbDetailId + ", fbId=" + fbId + ", queNo=" + queNo + ", optionName="
				+ optionName + ", rating=" + rating + ", fbDate=" + fbDate + ", saId=" + saId + ", status=" + status
				+ ", delStatus=" + delStatus + "]";
	}

}
