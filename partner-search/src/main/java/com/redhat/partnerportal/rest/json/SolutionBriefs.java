package com.redhat.partnerportal.rest.json;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "solution_briefs")
public class SolutionBriefs extends PanacheEntity {
	public String sb_title;
	public String sb_link;
	public String sb_industry;
	public String sb_biz_challenge;
	public Long partnerid;

	public SolutionBriefs() {
	}

	public SolutionBriefs(String sbt, String sbl, String sbi, String sbbc, Long pid) {
		this.sb_title = sbt;
		this.sb_link = sbl;
		this.sb_industry = sbi;
		this.sb_biz_challenge = sbbc;
		this.partnerid = pid;
	}

	public static List<SolutionBriefs> findSolutionBriefs(Long pid) {
		return SolutionBriefs.list("partnerid", pid);
	}
}
