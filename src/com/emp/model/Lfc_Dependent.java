package com.emp.model;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Lfc_Dependent")
public class Lfc_Dependent {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Lfc_Dependent")
	@SequenceGenerator(name = "Lfc_DependentSeq", sequenceName = "Lfc_DependentSeq", allocationSize = 1, initialValue = 1)
	private int id;
	
	@Column (name="EMPLY_CD")
	private int emplycd;
	
	@Column (name="Dependent_Name")
	private String dependentname;
	
	@Column (name="DesrNo")
	private int desrno;
	
	@Column (name="Relation")
	private String relation;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column (name="Dob")
	private Date dob = new Date();
	
	@Column (name="Gender")
	private String gender;
	
	@Column (name="annualIncome")
	private String annualincome;
	
	@Column (name="occupation")
	private String occupation;
	
	@Column (name="Status")
	private String Status;
	
	@Column (name="tranId")
	private int tranId;
	
	// Extra
	@Transient
	private String dateOfBairth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmplycd() {
		return emplycd;
	}

	public void setEmplycd(int emplycd) {
		this.emplycd = emplycd;
	}

	public String getDependentname() {
		return dependentname;
	}

	public void setDependentname(String dependentname) {
		this.dependentname = dependentname;
	}

	public int getDesrno() {
		return desrno;
	}

	public void setDesrno(int desrno) {
		this.desrno = desrno;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAnnualincome() {
		return annualincome;
	}

	public void setAnnualincome(String annualincome) {
		this.annualincome = annualincome;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDateOfBairth() {
		return dateOfBairth;
	}

	public void setDateOfBairth(String dateOfBairth) {
		this.dateOfBairth = dateOfBairth;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getTranId() {
		return tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}
	 
}
