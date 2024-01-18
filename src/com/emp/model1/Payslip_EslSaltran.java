/**
 * 
 */
package com.emp.model1;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 9764
 */
@Entity
@Table(name = "eslsaltran", indexes = { @Index(columnList = "emply_cd", name = "h_eslsaltran_idx") })
public class Payslip_EslSaltran {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "eslsaltranseq")
	@SequenceGenerator(name = "eslsaltranseq", sequenceName = "eslsaltranseq", allocationSize = 1, initialValue = 1)
	private int id;

	@Column(name = "emply_cd")
	private Integer emply_cd;

	@Column(name = "pay_rcvry_cd", length = 4)
	private String pay_rcvry_cd;

	@Column(name = "pay_rcvry_srl")
	private Integer pay_rcvry_srl = 1;

	@Column(name = "vld_frm_yrmth")
	private Integer vld_frm_yrmth;

	@Column(name = "vld_to_yrmth")
	private Integer vld_to_yrmth;

	@Column(name = "adj_of_yrmth")
	private Integer adj_of_yrmth;

	@Column(name = "dscrptn", length = 50)
	private String dscrptn;

	@Column(name = "amt")
	private BigDecimal amt;

	@Column(name = "ln_snctn_no")
	private Integer ln_snctn_no = 0;

	@Column(name = "rmtng_offc_cd")
	private Integer rmtng_offc_cd = 0;

	@Column(name = "maker")
	private Integer maker;

	@Column(name = "maker_up_dt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Temporal(TemporalType.DATE)
	private Date maker_up_dt;

	@Column(name = "maker_flag", length = 1)
	private String maker_flag;

	@Column(name = "checker")
	private Integer checker;

	@Column(name = "checker_up_dt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Temporal(TemporalType.DATE)
	private Date checker_up_dt;

	@Column(name = "checker_flag", length = 1)
	private String checker_flag;

	@Column(name = "flag")
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getEmply_cd() {
		return emply_cd;
	}

	public void setEmply_cd(Integer emply_cd) {
		this.emply_cd = emply_cd;
	}

	public String getPay_rcvry_cd() {
		return pay_rcvry_cd;
	}

	public void setPay_rcvry_cd(String pay_rcvry_cd) {
		this.pay_rcvry_cd = pay_rcvry_cd;
	}

	public Integer getPay_rcvry_srl() {
		return pay_rcvry_srl;
	}

	public void setPay_rcvry_srl(Integer pay_rcvry_srl) {
		this.pay_rcvry_srl = pay_rcvry_srl;
	}

	public Integer getVld_frm_yrmth() {
		return vld_frm_yrmth;
	}

	public void setVld_frm_yrmth(Integer vld_frm_yrmth) {
		this.vld_frm_yrmth = vld_frm_yrmth;
	}

	public Integer getVld_to_yrmth() {
		return vld_to_yrmth;
	}

	public void setVld_to_yrmth(Integer vld_to_yrmth) {
		this.vld_to_yrmth = vld_to_yrmth;
	}

	public Integer getAdj_of_yrmth() {
		return adj_of_yrmth;
	}

	public void setAdj_of_yrmth(Integer adj_of_yrmth) {
		this.adj_of_yrmth = adj_of_yrmth;
	}

	public String getDscrptn() {
		return dscrptn;
	}

	public void setDscrptn(String dscrptn) {
		this.dscrptn = dscrptn;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Integer getLn_snctn_no() {
		return ln_snctn_no;
	}

	public void setLn_snctn_no(Integer ln_snctn_no) {
		this.ln_snctn_no = ln_snctn_no;
	}

	public Integer getRmtng_offc_cd() {
		return rmtng_offc_cd;
	}

	public void setRmtng_offc_cd(Integer rmtng_offc_cd) {
		this.rmtng_offc_cd = rmtng_offc_cd;
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
