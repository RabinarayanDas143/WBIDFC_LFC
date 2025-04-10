package com.emp.modelAllowance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "eye_checkup_users")
public class Eye_Checkup_All_Users {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eye_checkup_usersSeq")
	@SequenceGenerator(name = "eye_checkup_usersSeq", sequenceName = "eye_checkup_usersSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "emply_cd")
	private int emply_cd;

	@NotNull
	@Column(name = "eye_checkup_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy", timezone = "IST")
	private Date eye_checkup_date;

	@NotNull
	@Column(name = "amount_applied")
	private Integer amount_applied;

	@Column(name = "maker")
	private Integer maker;

	@Column(name = "maker_up_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy", timezone = "IST")
	private Date maker_up_dt = new Date();

	@Column(name = "maker_flag", length = 1)
	private String maker_flag;

	@Column(name = "checker")
	private Integer checker;

	@Column(name = "checker_up_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy", timezone = "IST")
	private Date checker_up_dt;

	@Column(name = "checker_flag", length = 1)
	private String checker_flag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmply_cd() {
		return emply_cd;
	}

	public void setEmply_cd(int emply_cd) {
		this.emply_cd = emply_cd;
	}

	public Date getEye_checkup_date() {
		return eye_checkup_date;
	}

	public void setEye_checkup_date(Date eye_checkup_date) {
		this.eye_checkup_date = eye_checkup_date;
	}

	public Integer getAmount_applied() {
		return amount_applied;
	}

	public void setAmount_applied(Integer amount_applied) {
		this.amount_applied = amount_applied;
	}

	public Integer getMaker() {
		return maker;
	}

	public void setMaker(Integer maker) {
		this.maker = maker;
	}

	public Date getMaker_up_dt() {
		return maker_up_dt;
	}

	public void setMaker_up_dt(Date maker_up_dt) {
		this.maker_up_dt = maker_up_dt;
	}

	public String getMaker_flag() {
		return maker_flag;
	}

	public void setMaker_flag(String maker_flag) {
		this.maker_flag = maker_flag;
	}

	public Integer getChecker() {
		return checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public Date getChecker_up_dt() {
		return checker_up_dt;
	}

	public void setChecker_up_dt(Date checker_up_dt) {
		this.checker_up_dt = checker_up_dt;
	}

	public String getChecker_flag() {
		return checker_flag;
	}

	public void setChecker_flag(String checker_flag) {
		this.checker_flag = checker_flag;
	}

}
