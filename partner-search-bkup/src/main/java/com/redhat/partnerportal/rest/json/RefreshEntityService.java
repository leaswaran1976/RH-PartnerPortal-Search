package com.redhat.partnerportal.rest.json;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/refresh")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class RefreshEntityService {
	@GET
	public List<RefreshEntity> list() {
		return RefreshEntity.listAll();
	}
}
