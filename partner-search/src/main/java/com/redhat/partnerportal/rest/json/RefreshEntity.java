package com.redhat.partnerportal.rest.json;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "data_imported")
public class RefreshEntity extends PanacheEntity {

	public String refresh_date;

	/**
	 * Empty Constructor as required by Quarkus
	 */
	public RefreshEntity() {

	}

	/**
	 * Constructor to create RefreshEntity
	 */
	public RefreshEntity(String refresh_date) {
		this.refresh_date = refresh_date;
	}
}
