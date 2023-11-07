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
@Table(name = "pl_encashment_users")
public class PL_Encashment_Users {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pl_encashment_usersseq")
	@SequenceGenerator(name = "pl_encashment_usersseq", sequenceName = "pl_encashment_usersseq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "emply_cd")
	private int emply_cd;

	@NotNull
	@Column(name = "plencash_year")
	private int plencash_year;

	@NotNull
	@Column(name = "eligible_days")
	private int eligible_days;

	@NotNull
	@Column(name = "days_applied")
	private int days_applied;

	@Column(name = "festival_type", length = 10)
	private String festival_type;

	@NotNull
	@Column(name = "festival_name", length = 15)
	private String festival_name;

	@NotNull
	@Column(name = "amt_for_sanction")
	private Integer amt_for_sanction;

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

	public int getPlencash_year() {
		return plencash_year;
	}

	public void setPlencash_year(int plencash_year) {
		this.plencash_year = plencash_year;
	}

	public int getEligible_days() {
		return eligible_days;
	}

	public void setEligible_days(int eligible_days) {
		this.eligible_days = eligible_days;
	}

	public int getDays_applied() {
		return days_applied;
	}

	public void setDays_applied(int days_applied) {
		this.days_applied = days_applied;
	}

	public String getFestival_type() {
		return festival_type;
	}

	public void setFestival_type(String festival_type) {
		this.festival_type = festival_type;
	}

	public String getFestival_name() {
		return festival_name;
	}

	public void setFestival_name(String festival_name) {
		this.festival_name = festival_name;
	}

	public Integer getAmt_for_sanction() {
		return amt_for_sanction;
	}

	public void setAmt_for_sanction(Integer amt_for_sanction) {
		this.amt_for_sanction = amt_for_sanction;
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
