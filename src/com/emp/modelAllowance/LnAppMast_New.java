package com.emp.modelAllowance;

import java.io.Serializable;
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

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "lnappmast_new")
public class LnAppMast_New implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LnAppMast_NewSeq")
	@SequenceGenerator(name = "LnAppMast_NewSeq", sequenceName = "LnAppMast_NewSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "lnempcd")
	private Integer lnempcd;

	@Column(name = "lncode", length = 4)
	private String lncode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "fstvldt")
	@Temporal(TemporalType.DATE)
	private Date fstvldt;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "lnapplctn_dt")
	@Temporal(TemporalType.DATE)
	private Date lnapplctn_dt = new Date();

	@NotNull
	@Column(name = "lnloan_amt", precision = 9, scale = 2)
	private Double lnloan_amt = 0.00;

	@NotNull
	@Check(constraints = "lnstts IN ('N', 'S', 'R')")
	@Column(name = "lnstts", length = 1)
	private String lnstts = "N";

	@Column(name = "cns_name", length = 30)
	private String cns_name;

	@Column(name = "pc_name", length = 30)
	private String pc_name;

	@Column(name = "vendor_name", length = 30)
	private String vendor_name;

	@Column(name = "vendor_add", length = 100)
	private String vendor_add;

	@Column(name = "inv_no", length = 15)
	private String inv_no;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "inv_date")
	@Temporal(TemporalType.DATE)
	private Date inv_date;

	@Column(name = "inv_amt", precision = 9, scale = 2)
	private Double inv_amt;

//	@NotNull
	@Column(name = "idd", length = 2)
	private String idd;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "entry_date")
	@Temporal(TemporalType.DATE)
	private Date entry_date = new Date();

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "updt_date")
	@Temporal(TemporalType.DATE)
	private Date updt_date = new Date();

//	@NotNull
	@Column(name = "updt_by", length = 10)
	private String updt_by;

	@Column(name = "del_flg_mkr", length = 1)
	private String del_flg_mkr;

	@Column(name = "del_flg_chkr", length = 1)
	private String del_flg_chkr;

	@Column(name = "del_by_mkr", length = 1)
	private String del_by_mkr;

	@Column(name = "del_by_chkr", length = 10)
	private String del_by_chkr;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "delete_dt_mkr")
	@Temporal(TemporalType.DATE)
	private Date delete_dt_mkr;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "delete_dt_chkr")
	@Temporal(TemporalType.DATE)
	private Date delete_dt_chkr;

	@Column(name = "mkr_remarks", length = 50)
	private String mkr_remarks;

	@Column(name = "chkr_remarks", length = 100)
	private String chkr_remarks;

	@Column(name = "festival_type", length = 10)
	private String festival_type;

	@Column(name = "festival_name", length = 15)
	private String festival_name;

	@Column(name = "festAdv_year")
	private int festAdv_year;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getLnempcd() {
		return lnempcd;
	}

	public void setLnempcd(Integer lnempcd) {
		this.lnempcd = lnempcd;
	}

	public String getLncode() {
		return lncode;
	}

	public void setLncode(String lncode) {
		this.lncode = lncode;
	}

	public Date getFstvldt() {
		return fstvldt;
	}

	public void setFstvldt(Date fstvldt) {
		this.fstvldt = fstvldt;
	}

	public Date getLnapplctn_dt() {
		return lnapplctn_dt;
	}

	public void setLnapplctn_dt(Date lnapplctn_dt) {
		this.lnapplctn_dt = lnapplctn_dt;
	}

	public Double getLnloan_amt() {
		return lnloan_amt;
	}

	public void setLnloan_amt(Double lnloan_amt) {
		this.lnloan_amt = lnloan_amt;
	}

	public String getLnstts() {
		return lnstts;
	}

	public void setLnstts(String lnstts) {
		this.lnstts = lnstts;
	}

	public String getCns_name() {
		return cns_name;
	}

	public void setCns_name(String cns_name) {
		this.cns_name = cns_name;
	}

	public String getPc_name() {
		return pc_name;
	}

	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public String getVendor_add() {
		return vendor_add;
	}

	public void setVendor_add(String vendor_add) {
		this.vendor_add = vendor_add;
	}

	public String getInv_no() {
		return inv_no;
	}

	public void setInv_no(String inv_no) {
		this.inv_no = inv_no;
	}

	public Date getInv_date() {
		return inv_date;
	}

	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}

	public Double getInv_amt() {
		return inv_amt;
	}

	public void setInv_amt(Double inv_amt) {
		this.inv_amt = inv_amt;
	}

	public String getIdd() {
		return idd;
	}

	public void setIdd(String idd) {
		this.idd = idd;
	}

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public Date getUpdt_date() {
		return updt_date;
	}

	public void setUpdt_date(Date updt_date) {
		this.updt_date = updt_date;
	}

	public String getUpdt_by() {
		return updt_by;
	}

	public void setUpdt_by(String updt_by) {
		this.updt_by = updt_by;
	}

	public String getDel_flg_mkr() {
		return del_flg_mkr;
	}

	public void setDel_flg_mkr(String del_flg_mkr) {
		this.del_flg_mkr = del_flg_mkr;
	}

	public String getDel_flg_chkr() {
		return del_flg_chkr;
	}

	public void setDel_flg_chkr(String del_flg_chkr) {
		this.del_flg_chkr = del_flg_chkr;
	}

	public String getDel_by_mkr() {
		return del_by_mkr;
	}

	public void setDel_by_mkr(String del_by_mkr) {
		this.del_by_mkr = del_by_mkr;
	}

	public String getDel_by_chkr() {
		return del_by_chkr;
	}

	public void setDel_by_chkr(String del_by_chkr) {
		this.del_by_chkr = del_by_chkr;
	}

	public Date getDelete_dt_mkr() {
		return delete_dt_mkr;
	}

	public void setDelete_dt_mkr(Date delete_dt_mkr) {
		this.delete_dt_mkr = delete_dt_mkr;
	}

	public Date getDelete_dt_chkr() {
		return delete_dt_chkr;
	}

	public void setDelete_dt_chkr(Date delete_dt_chkr) {
		this.delete_dt_chkr = delete_dt_chkr;
	}

	public String getMkr_remarks() {
		return mkr_remarks;
	}

	public void setMkr_remarks(String mkr_remarks) {
		this.mkr_remarks = mkr_remarks;
	}

	public String getChkr_remarks() {
		return chkr_remarks;
	}

	public void setChkr_remarks(String chkr_remarks) {
		this.chkr_remarks = chkr_remarks;
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

	public int getFestAdv_year() {
		return festAdv_year;
	}

	public void setFestAdv_year(int festAdv_year) {
		this.festAdv_year = festAdv_year;
	}

}
