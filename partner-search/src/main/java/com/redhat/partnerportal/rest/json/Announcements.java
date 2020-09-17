package com.redhat.partnerportal.rest.json;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "announcements")
public class Announcements extends PanacheEntity {
	public String announcement;
	public String announcement_image;
	public String start_date;
	public String end_date;
	public String submitter;

	public Announcements() {
	}

	public Announcements(String message, String image, String startDate, String endDate, String submitter) {
		this.announcement = message;
		this.announcement_image = image;
		this.start_date = startDate;
		this.end_date = endDate;
		this.submitter = submitter;
	}
}
