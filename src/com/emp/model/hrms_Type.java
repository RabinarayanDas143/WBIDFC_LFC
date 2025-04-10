package com.emp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author int6346 vivek
 */

@Entity
@Table(name = "hrms_type", indexes = { @Index(columnList = "type,", name = "indx_hrms_type") })
public class hrms_Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hrms_typeSeq")
	@SequenceGenerator(name = "hrms_typeSeq", sequenceName = "hrms_typeSeq", allocationSize = 1, initialValue = 1)
	private int id;
	@Column(name = "typeid")
	private Integer typeid;
	@Column(name = "typename")
	private String typename;
	@Column(name = "type")
	private String type;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the typeid
	 */
	public Integer getTypeid() {
		return typeid;
	}

	/**
	 * @param typeid the typeid to set
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	/**
	 * @return the typename
	 */
	public String getTypename() {
		return typename;
	}

	/**
	 * @param typename the typename to set
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
