package com.coco.spring.boot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8015476019906434426L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String userName;
	@NotNull
	private String passWord;
	@NotNull
	private Date recDate;
	@NotNull
	private int recStatus;

	private Date upDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public int getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(int recStatus) {
		this.recStatus = recStatus;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
}
