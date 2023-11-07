package com.emp.model;

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
 * @author int6346 vivek
 */

@Entity
@Table(name = "hrms_dependent_detail", indexes = { @Index(columnList = "emply_cd", name = "indx_hrms_dependent_detail") })
public class Hrms_Dependent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hrms_dependent_detailSeq")
	@SequenceGenerator(name = "hrms_dependent_detailSeq", sequenceName = "hrms_dependent_detailSeq", allocationSize = 1, initialValue = 1)
	@Column(name = "id")
	private int id;

	@Column(name = "emply_cd")
	private int emplyCd;
	
	@Column(name = "Dser_no")
	private int desrNo;

	@Column(name = "depnd_title", length = 10)
	private String title;

	@Column(name = "depnd_name", length = 100)
	private String dependentname;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "dob")
	private Date dob;

	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "relation", length = 100)
	private String relation;

	@Column(name = "occupation", length = 100)
	private String occupation;

	@Column(name = "annualincome")
	private BigDecimal annualincome;

	@Column(name = "entryby")
	private int entryby;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "entrydt")
	private Date entrydt;
	@Column(name = "updateby")
	private int updateby;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "updatedt")
	private Date updatedt;

	@Column(name = "checkerby")
	private Integer checkerby;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "checkerdt")
	private Date checkerdt;
	
	@Column(name = "flag")
	private String flag = "Y";
	
	@Column(name = "checker_flag")
	private String checkerFlag;

	@Column(name = "remarks")
	private String remarks;

	public String getCheckerFlag() {
		return checkerFlag;
	}

	public void setCheckerFlag(String checkerFlag) {
		this.checkerFlag = checkerFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the updateby
	 */
	public int getUpdateby() {
		return updateby;
	}

	/**
	 * @param updateby the updateby to set
	 */
	public void setUpdateby(int updateby) {
		this.updateby = updateby;
	}

	/**
	 * @return the updatedt
	 */
	public Date getUpdatedt() {
		return updatedt;
	}

	/**
	 * @param updatedt the updatedt to set
	 */
	public void setUpdatedt(Date updatedt) {
		this.updatedt = updatedt;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the emplyCd
	 */
	public int getEmplyCd() {
		return emplyCd;
	}

	/**
	 * @param emplyCd the emplyCd to set
	 */
	public void setEmplyCd(int emplyCd) {
		this.emplyCd = emplyCd;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the dependentname
	 */
	public String getDependentname() {
		return dependentname;
	}

	/**
	 * @param dependentname the dependentname to set
	 */
	public void setDependentname(String dependentname) {
		this.dependentname = dependentname;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the annualincome
	 */
	public BigDecimal getAnnualincome() {
		return annualincome;
	}

	/**
	 * @param annualincome the annualincome to set
	 */
	public void setAnnualincome(BigDecimal annualincome) {
		this.annualincome = annualincome;
	}

	/**
	 * @return the entryby
	 */
	public int getEntryby() {
		return entryby;
	}

	/**
	 * @param entryby the entryby to set
	 */
	public void setEntryby(int entryby) {
		this.entryby = entryby;
	}

	/**
	 * @return the entrydt
	 */
	public Date getEntrydt() {
		return entrydt;
	}

	/**
	 * @param entrydt the entrydt to set
	 */
	public void setEntrydt(Date entrydt) {
		this.entrydt = entrydt;
	}

	public Integer getCheckerby() {
		return checkerby;
	}

	public void setCheckerby(Integer checkerby) {
		this.checkerby = checkerby;
	}

	public Date getCheckerdt() {
		return checkerdt;
	}

	public void setCheckerdt(Date checkerdt) {
		this.checkerdt = checkerdt;
	}

	public int getDesrNo() {
		return desrNo;
	}

	public void setDesrNo(int desrNo) {
		this.desrNo = desrNo;
	}
	
	
}
