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
@Table(name = "medical_limit")
public class Medical_Allowance_Limit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "newspaper_limitSeq")
	@SequenceGenerator(name = "newspaper_limitSeq", sequenceName = "newspaper_limitSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "oat_limit")
	private Integer oat_limit;

	@NotNull
	@Column(name = "oas_limit")
	private Integer oas_limit;

	@NotNull
	@Column(name = "officer_limit")
	private Integer officer_limit;

	@Column(name = "entry_by")
	private int entry_by;

	@Column(name = "entry_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date entry_dt = new Date();

	@Column(name = "update_by")
	private int update_by;

	@Column(name = "update_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date update_dt;

	@Column(name = "from_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date from_dt;

	@Column(name = "to_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date to_dt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getOat_limit() {
		return oat_limit;
	}

	public void setOat_limit(Integer oat_limit) {
		this.oat_limit = oat_limit;
	}

	public Integer getOas_limit() {
		return oas_limit;
	}

	public void setOas_limit(Integer oas_limit) {
		this.oas_limit = oas_limit;
	}

	public Integer getOfficer_limit() {
		return officer_limit;
	}

	public void setOfficer_limit(Integer officer_limit) {
		this.officer_limit = officer_limit;
	}

	public int getEntry_by() {
		return entry_by;
	}

	public void setEntry_by(int entry_by) {
		this.entry_by = entry_by;
	}

	public Date getEntry_dt() {
		return entry_dt;
	}

	public void setEntry_dt(Date entry_dt) {
		this.entry_dt = entry_dt;
	}

	public int getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(int update_by) {
		this.update_by = update_by;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

	public Date getFrom_dt() {
		return from_dt;
	}

	public void setFrom_dt(Date from_dt) {
		this.from_dt = from_dt;
	}

	public Date getTo_dt() {
		return to_dt;
	}

	public void setTo_dt(Date to_dt) {
		this.to_dt = to_dt;
	}

}
