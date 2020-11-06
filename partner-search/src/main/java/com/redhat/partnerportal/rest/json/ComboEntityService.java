package com.redhat.partnerportal.rest.json;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.logging.Logger;

import io.quarkus.panache.common.Sort;

@Path("/comboComboEntity")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ComboEntityService {

	private final Logger logger = Logger.getLogger(ComboEntityService.class.getName());

	@GET
	public List<ComboEntity> list() {
		return ComboEntity.listAll(Sort.by("account_name"));
	}

	@GET
	@Path("/search/{partnerid}")
	public List<ComboEntity> findPartnerById(@PathParam("partnerid") String pid) {
		return ComboEntity.findById(pid);
	}

	@GET
	@Path("/search/{name}")
	public List<ComboEntity> findPartnerByName(@PathParam("name") String name) {
		return ComboEntity.findByName(name);
	}

	@GET
	@Path("/specialsearch/{keyword}")
	public List<ComboEntity> findApexPartnerByName(@PathParam("keyword") String keyword) {
		return ComboEntity.findSpecialComboEntity(keyword);
	}

	@GET
	@Path("/search")
	public List<ComboEntity> findComboEntity(@QueryParam("partnerlevel") String partnerLevel,
			@QueryParam("partnerpgm") String partnerProgram, @QueryParam("region") String region,
			@QueryParam("location") String location, @QueryParam("skills") String rhpe, @QueryParam("ive") String ive,
			@QueryParam("pgmgrp") String pgmGroup) {
		logger.debug("Start of performing advanced search");
		Stream<ComboEntity> comboEntities = ComboEntity.streamAll();

		if (partnerLevel != null && !partnerLevel.isBlank()) {
			logger.debug("Partner Level was not blank and null. Passed in Partner Level: " + partnerLevel);
			comboEntities = comboEntities
					.filter(p -> p.finder_partner_tier.toUpperCase().contains(partnerLevel.toUpperCase()));
		}

		if (partnerProgram != null && !partnerProgram.isBlank()) {
			comboEntities = comboEntities
					.filter(p -> p.partner_programs.toUpperCase().contains(partnerProgram.toUpperCase()));
		}

		if (region != null && !region.isBlank()) {
			comboEntities = comboEntities.filter(p -> region.equalsIgnoreCase(p.subregion));
		}

		if (location != null && !location.isBlank()) {
			comboEntities = comboEntities.filter(p -> location.equalsIgnoreCase(p.billing_state_province));
		}

		if (rhpe != null && !rhpe.isBlank()) {
			logger.debug("Skills: " + rhpe);
			comboEntities = comboEntities.filter(p -> p.partner_skills.toUpperCase().contains(rhpe.toUpperCase()));
		}

		if (ive != null && !ive.isBlank()) {
			comboEntities = comboEntities
					.filter(p -> p.industry_vertical_expertise.toUpperCase().contains(ive.toUpperCase())
							|| p.sb_industry.toUpperCase().contains(ive.toUpperCase())
							|| p.spotlight_industry.toUpperCase().contains(ive.toUpperCase()));
		}

		if (pgmGroup != null && !pgmGroup.isBlank()) {
			comboEntities = comboEntities.filter(p -> p.program_group.toUpperCase().contains(pgmGroup.toUpperCase()));
		}

		logger.debug("End of performing advanced search");
		return comboEntities.collect(Collectors.toList());
	}
}
