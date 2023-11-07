package com.emp.modelAllowance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/*@Entity*/
public class Festival_Advance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fest_ess_usersSeq")
	@SequenceGenerator(name = "fest_ess_usersSeq", sequenceName = "fest_ess_usersSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "emply_cd", length = 10)
	private String emply_cd;

	@Column(name = "emply_name", length = 50)
	private String emply_name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "added_on")
	@Temporal(TemporalType.DATE)
	private Date entry_dt;

	@Column(name = "user_full_name", length = 100)
	private String user_full_name;

	@Column(name = "emply_role", length = 15)
	private String emply_role;

	@Column(name = "fest_type", length = 10)
	private String fest_type;

	@NotNull
	@Column(name = "fest_name", length = 15)
	private String fest_name;

	@NotNull
	@Column(name = "amount_applied")
	private Integer amount_applied;

	@Column(name = "maker_up_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date maker_up_dt = new Date();

	@Column(name = "maker_flag", length = 1)
	private String maker_flag;

	@Column(name = "checker")
	private Integer checker;

	@Column(name = "checker_up_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date checker_up_dt;

	@Column(name = "checker_flag", length = 1)
	private String checker_flag;
}
