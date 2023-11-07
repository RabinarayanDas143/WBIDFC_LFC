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
@Table(name = "newspaper_users")
public class Newspaper_Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "newspaper_usersSeq")
	@SequenceGenerator(name = "newspaper_usersSeq", sequenceName = "newspaper_usersSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "emply_cd")
	private int emply_cd;

	@NotNull
	@Column(name = "applied_year_month")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm", timezone = "IST")
	private Date applied_year_month;

	@NotNull
	@Column(name = "amount_applied")
	private Integer amount_applied;

	@Column(name = "maker")
	private Integer maker;

	@Column(name = "maker_up_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date maker_up_dt = new Date();

	@Column(name = "maker_flag", length = 1)
	private String maker_flag;

	@Column(name = "checker")
	private Integer checker;

	@Column(name = "checker_up_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
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

	public Date getApplied_year_month() {
		return applied_year_month;
	}

	public void setApplied_year_month(Date applied_year_month) {
		this.applied_year_month = applied_year_month;
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
