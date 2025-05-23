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
@Table(name = "health_checkup_limit")
public class Health_Checkup_All_Limit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "health_checkup_limitSeq")
	@SequenceGenerator(name = "health_checkup_limitSeq", sequenceName = "health_checkup_limitSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "oat_limit")
	private Integer oat_limit;

	@NotNull
	@Column(name = "oat_limit_female")
	private Integer oat_limit_female;

	@NotNull
	@Column(name = "oas_limit")
	private Integer oas_limit;

	@NotNull
	@Column(name = "oas_limit_female")
	private Integer oas_limit_female;

	@NotNull
	@Column(name = "officer_limit")
	private Integer officer_limit;

	@NotNull
	@Column(name = "officer_limit_female")
	private Integer officer_limit_female;

	@Column(name = "entry_by")
	private int entry_by;

	@Column(name = "entry_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date entry_dt = new Date();

	@Column(name = "update_by")
	private int update_by;

	@Column(name = "update_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date update_dt;

	@Column(name = "from_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date from_dt;

	@Column(name = "to_dt")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	private Date to_dt;

}
