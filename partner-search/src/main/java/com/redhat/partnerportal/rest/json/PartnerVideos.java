package com.redhat.partnerportal.rest.json;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "partner_videos")
public class PartnerVideos extends PanacheEntity {
	public Long partnerid;
	public String video_title;
	public String video_link;

	public PartnerVideos(String title, String link, Long pid) {
		this.partnerid = pid;
		this.video_link = link;
		this.video_title = title;
	}

	public static List<PartnerVideos> findPartnerVideos(Long pid) {
		return PartnerVideos.list("partnerid", pid);
	}
}
