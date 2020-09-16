package com.redhat.partnerportal.rest.json;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "lmsdata")
public class LMSData extends PanacheEntity {
	public Long partnerid;
	public String cred_role;
	public String cred_product;

	/**
	 * Empty Constructor as required by Quarkus
	 */
	public LMSData() {
	}

	public LMSData(Long pid, String crole, String cproduct) {
		this.partnerid = pid;
		this.cred_role = crole;
		this.cred_product = cproduct;
	}

	/**
	 * Method to find LMS data for a specific partner
	 * 
	 * @return
	 */
	public static List<LMSData> findLMSData(Long id) {
		return LMSData.list("partnerid", id);
	}
}
