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
@Table(name = "fstmast")
public class FstMast {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fstmastSeq")
	@SequenceGenerator(name = "fstmastSeq", sequenceName = "fstmastSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "fstvlcd")
	private Integer fstvlcd = 0;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "fstvldt")
	@Temporal(TemporalType.DATE)
	private Date fstvldt;

	@Column(name = "fstvlnm", length = 30)
	private String fstvlnm;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "staplndt")
	@Temporal(TemporalType.DATE)
	private Date staplndt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "endaplndt")
	@Temporal(TemporalType.DATE)
	private Date endaplndt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "pdisbdt")
	@Temporal(TemporalType.DATE)
	private Date pdisbdt;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "entry_date")
	@Temporal(TemporalType.DATE)
	private Date entry_date = new Date();

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "updt_dt")
	@Temporal(TemporalType.DATE)
	private Date updt_dt;

	@NotNull
	@Column(name = "updt_by", length = 8)
	private String updt_by = "user";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getFstvlcd() {
		return fstvlcd;
	}

	public void setFstvlcd(Integer fstvlcd) {
		this.fstvlcd = fstvlcd;
	}

	public Date getFstvldt() {
		return fstvldt;
	}

	public void setFstvldt(Date fstvldt) {
		this.fstvldt = fstvldt;
	}

	public String getFstvlnm() {
		return fstvlnm;
	}

	public void setFstvlnm(String fstvlnm) {
		this.fstvlnm = fstvlnm;
	}

	public Date getStaplndt() {
		return staplndt;
	}

	public void setStaplndt(Date staplndt) {
		this.staplndt = staplndt;
	}

	public Date getEndaplndt() {
		return endaplndt;
	}

	public void setEndaplndt(Date endaplndt) {
		this.endaplndt = endaplndt;
	}

	public Date getPdisbdt() {
		return pdisbdt;
	}

	public void setPdisbdt(Date pdisbdt) {
		this.pdisbdt = pdisbdt;
	}

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public Date getUpdt_dt() {
		return updt_dt;
	}

	public void setUpdt_dt(Date updt_dt) {
		this.updt_dt = updt_dt;
	}

	public String getUpdt_by() {
		return updt_by;
	}

	public void setUpdt_by(String updt_by) {
		this.updt_by = updt_by;
	}

}
