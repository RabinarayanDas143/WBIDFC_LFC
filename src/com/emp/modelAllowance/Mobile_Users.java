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
@Table(name = "mobile_users")
public class Mobile_Users {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mobile_usersSeq")
	@SequenceGenerator(name = "mobile_usersSeq", sequenceName = "mobile_usersSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST")
	@Column(name = "purchase_dt")
	@Temporal(TemporalType.DATE)
	private Date purchase_dt;

	@NotNull
	@Column(name = "bill_amount")
	private Integer bill_amount;

	@NotNull
	@Column(name = "imei_no")
	private Integer imei_no;

	@Column(name = "sim_type", length = 5)
	private String sim_type;

	@Column(name = "ram_rom", length = 5)
	private String ram_rom;

	@Column(name = "operating_system", length = 10)
	private String operating_system;

	@Column(name = "cpu_processor", length = 10)
	private String cpu_processor;

	@Column(name = "network_type", length = 10)
	private String network_type;

	@Column(name = "display_size", length = 10)
	private String display_size;

}
