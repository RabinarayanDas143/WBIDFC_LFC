package com.emp.bean;

import org.springframework.stereotype.Component;

@Component
public class LoginBean {
	// for login detail
	private String userid;
	private String password;

	// for session
	private String sessionid;
	private String role;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginBean [userid=" + userid + ", password=" + password + ", sessionid=" + sessionid + ", role=" + role
				+ "]";
	}

}
