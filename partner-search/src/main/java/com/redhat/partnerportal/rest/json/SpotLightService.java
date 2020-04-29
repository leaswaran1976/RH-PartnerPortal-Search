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

import org.jboss.logging.Logger;

import io.quarkus.panache.common.Sort;

@Path("/spotlights")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class SpotLightService {
	private final Logger logger = Logger.getLogger(SpotLightService.class.getName());

	@GET
	public List<SpotLights> list() {
		return SpotLights.listAll(Sort.by("partnerid"));
	}
	
	@GET
	@Path("/{partnerid}")
	public List<SpotLights> getSpotLights(@PathParam("partnerid") Long pid) {
		Stream<SpotLights> sls = SpotLights.streamAll();
		sls = sls.filter(p -> p.partnerid.equals(pid));

		List<SpotLights> slsList = sls.collect(Collectors.toList());

		for (SpotLights sl : slsList) {
			logger.debug("Link: " + sl.spotlight_title);
			logger.debug("Link: " + sl.spotlight_sa);
		}
		return SpotLights.findSpotLights(pid);
	}
}
