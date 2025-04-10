package com.emp.model;

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

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name = "hrms_historylfc", indexes = {
		@Index(columnList = "emply_cd,in_time,session_id", name = "h_hrms_historylfc_idx") })
public class Hrms_History {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hrms_historylfcSeq")
	@SequenceGenerator(name = "hrms_historylfcSeq", sequenceName = "hrms_historylfcSeq", allocationSize = 1, initialValue = 1)
	private int id;
	@Column(name = "emply_cd")
	private int emply_cd;
	@Column(name = "in_time")
	@Temporal(TemporalType.DATE)
	private Date intime;
	@Column(name = "out_time")
	@Temporal(TemporalType.DATE)
	private Date outtime;
	@Column(name = "ip_address", length = 20)
	private String ipaddress;
	@Column(name = "session_id", length = 50)
	private String sessionid;

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

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public Date getOuttime() {
		return outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Hrms_History(int emply_cd, Date intime, Date outtime, String ipaddress, String sessionid) {
		super();
		this.emply_cd = emply_cd;
		this.intime = intime;
		this.outtime = outtime;
		this.ipaddress = ipaddress;
		this.sessionid = sessionid;
	}

}
