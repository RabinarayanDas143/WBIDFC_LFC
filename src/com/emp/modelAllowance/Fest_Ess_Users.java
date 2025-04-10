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
@Table(name = "fest_ess_users")
public class Fest_Ess_Users {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fest_ess_usersSeq")
	@SequenceGenerator(name = "fest_ess_usersSeq", sequenceName = "fest_ess_usersSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "user_id", length = 10)
	private String user_id;

	@Column(name = "user_name", length = 50)
	private String user_name;

	@NotNull
	@Column(name = "is_active")
	private Integer is_active;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "added_on")
	@Temporal(TemporalType.DATE)
	private Date added_on;

	@Column(name = "user_full_name", length = 100)
	private String user_full_name;

	@Column(name = "user_role", length = 15)
	private String user_role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getIs_active() {
		return is_active;
	}

	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}

	public Date getAdded_on() {
		return added_on;
	}

	public void setAdded_on(Date added_on) {
		this.added_on = added_on;
	}

	public String getUser_full_name() {
		return user_full_name;
	}

	public void setUser_full_name(String user_full_name) {
		this.user_full_name = user_full_name;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

}
