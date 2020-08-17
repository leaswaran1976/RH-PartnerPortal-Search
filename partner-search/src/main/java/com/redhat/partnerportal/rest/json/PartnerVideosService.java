package com.redhat.partnerportal.rest.json;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.quarkus.panache.common.Sort;

@Path("/partnervideos")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PartnerVideosService {
	@GET
	public List<PartnerVideos> list() {
		return PartnerVideos.listAll(Sort.by("partnerid"));
	}

	@GET
	@Path("/{partnerid}")
	public List<PartnerVideos> getPartnerVideos(@PathParam("partnerid") Long pid) {
		return PartnerVideos.findPartnerVideos(pid);
	}
}
