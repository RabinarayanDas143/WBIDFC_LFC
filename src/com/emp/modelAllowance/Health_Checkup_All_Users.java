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
@Table(name = "Health_Checkup_Users")
public class Health_Checkup_All_Users {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eye_checkup_usersSeq")
	@SequenceGenerator(name = "eye_checkup_usersSeq", sequenceName = "eye_checkup_usersSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "emply_cd")
	private int emply_cd;

	@NotNull
	@Column(name = "health_checkup_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy", timezone = "IST")
	private Date eye_checkup_date;

	@NotNull
	@Column(name = "applied_for", length = 10)
	private String applied_for;

	@NotNull
	@Column(name = "amount_applied")
	private Integer amount_applied;

	@Column(name = "maker")
	private Integer maker;

	@Column(name = "maker_up_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy", timezone = "IST")
	private Date maker_up_dt = new Date();

	@Column(name = "maker_flag", length = 1)
	private String maker_flag;

	@Column(name = "checker")
	private Integer checker;

	@Column(name = "checker_up_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy", timezone = "IST")
	private Date checker_up_dt;

	@Column(name = "checker_flag", length = 1)
	private String checker_flag;
}
