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

@Path("/partners")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PartnerSearchService {

	private final Logger logger = Logger.getLogger(PartnerSearchService.class.getName());

	@GET
	public List<Partners> list() {
		return Partners.listAll(Sort.by("account_name"));
	}

	@GET
	@Path("/search/{partnerid}")
	public Partners findPartnerById(@PathParam("partnerid") Long pid) {
		return Partners.findById(pid);
	}
	
	@GET
	@Path("/namesearch/{name}")
	public List<Partners> findPartnerByName(@PathParam("name") String name) {
		return Partners.findByName(name);
	}

	@GET
	@Path("/specialsearch/{keyword}")
	public List<Partners> findApexPartnerByName(@PathParam("keyword") String keyword) {
		return Partners.findSpecialPartners(keyword);
	}

	@GET
	@Path("/search")
	public List<Partners> findPartners(@QueryParam("partnerlevel") String partnerLevel,
			@QueryParam("partnerpgm") String partnerProgram, @QueryParam("region") String region,
			@QueryParam("location") String location, @QueryParam("skills") String rhpe, @QueryParam("ive") String ive,
			@QueryParam("pgmgrp") String pgmGroup) {
		logger.debug("Start of performing advanced search");
		Stream<Partners> partners = Partners.streamAll();

		if (partnerLevel != null && !partnerLevel.isBlank()) {
			logger.debug("Partner Level was not blank and null. Passed in Partner Level: " + partnerLevel);
			partners = partners.filter(p -> p.finder_partner_tier.toUpperCase().contains(partnerLevel.toUpperCase()));
		}

		if (partnerProgram != null && !partnerProgram.isBlank()) {
			partners = partners.filter(p -> p.partner_programs.toUpperCase().contains(partnerProgram.toUpperCase()));
		}

		if (region != null && !region.isBlank()) {
			partners = partners.filter(p -> region.equalsIgnoreCase(p.subregion));
		}

		if (location != null && !location.isBlank()) {
			partners = partners.filter(p -> location.equalsIgnoreCase(p.billing_state_province));
		}

		if (rhpe != null && !rhpe.isBlank()) {
			logger.debug("Skills: " + rhpe);
			partners = partners.filter(p -> p.partner_skills.toUpperCase().contains(rhpe.toUpperCase()));
		}

		if (ive != null && !ive.isBlank()) {
			partners = partners.filter(p -> p.industry_vertical_expertise.toUpperCase().contains(ive.toUpperCase()));
		}

		if (pgmGroup != null && !pgmGroup.isBlank()) {
			partners = partners.filter(p -> p.program_group.toUpperCase().contains(pgmGroup.toUpperCase()));
		}

		logger.debug("End of performing advanced search");
		return partners.collect(Collectors.toList());
	}
}