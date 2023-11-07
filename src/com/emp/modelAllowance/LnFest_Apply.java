package com.emp.modelAllowance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LnFest_Apply")
public class LnFest_Apply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LnFest_ApplySeq")
	@SequenceGenerator(name = "LnFest_ApplySeq", sequenceName = "LnFest_ApplySeq", allocationSize = 1, initialValue = 1)
	private int id;

	@NotNull
	@Column(name = "lnEmpCd")
	private Integer lnEmpCd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getLnEmpCd() {
		return lnEmpCd;
	}

	public void setLnEmpCd(Integer lnEmpCd) {
		this.lnEmpCd = lnEmpCd;
	}

}
