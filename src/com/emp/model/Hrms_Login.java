package com.emp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@DynamicUpdate
@Entity
@Table(name = "hrms_login", indexes = { @Index(columnList = "emply_cd,date_start,date_end", name = "h_login_idx") })
public class Hrms_Login implements Serializable {

	public Hrms_Login() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hrms_loginseq")
	@SequenceGenerator(name = "hrms_loginseq", sequenceName = "hrms_loginseq", allocationSize = 1, initialValue = 1)
	private int id;
	@Column(name = "emply_cd")
	private Integer emply_cd;
	@Column(name = "name", length = 50)
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "date_start")
	@Temporal(TemporalType.DATE)
	private Date dateStart;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "date_end")
	@Temporal(TemporalType.DATE)
	private Date dateEnd;
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date createDate = new Date();
	@Column(name = "create_by")
	private int createBy;
	@Column(name = "update_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date updateDate;
	@Column(name = "update_by")
	private String updateBy;
	@Column(name = "stat", length = 2)
	private String status = "A";
	@Column(name = "rolelist", length = 50)
	private String rolelist;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRolelist() {
		return rolelist;
	}

	public void setRolelist(String rolelist) {
		this.rolelist = rolelist;
	}

}
