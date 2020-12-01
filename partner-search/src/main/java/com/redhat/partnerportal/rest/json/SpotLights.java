package com.redhat.partnerportal.rest.json;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "pspotlights")
public class SpotLights extends PanacheEntity {
	public String spotlight_title;
	public String spotlight_link;
	public String spotlight_sa;
	public String spotlight_industry;
	public Long partnerid;

	/**
	 * Empty Constructor as required by Quarkus
	 */
	public SpotLights() {
	}

	/**
	 * @param title
	 * @param link
	 * @param sa
	 */
	public SpotLights(String title, String link, String sa, Long pid, String industry) {
		this.spotlight_title = title;
		this.spotlight_link = link;
		this.spotlight_sa = sa;
		this.partnerid = pid;
		this.spotlight_industry = industry;
	}

	/**
	 * Method to find special Partners like APEX, GSI etc
	 * 
	 * @return
	 */
	public static List<SpotLights> findSpotLights(Long id) {
		return SpotLights.list("partnerid", id);
	}
}
