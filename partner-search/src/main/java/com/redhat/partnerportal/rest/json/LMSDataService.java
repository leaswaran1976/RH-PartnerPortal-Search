package com.redhat.partnerportal.rest.json;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

@Path("/lmsdata")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LMSDataService {
	@Inject
	EntityManager em;

	@GET
	public List<LMSData> list() {
		return LMSData.listAll(Sort.by("partnerid"));
	}

	@GET
	@Path("/{partnerid}")
	public JsonArray getCredData(@PathParam("partnerid") Long pid) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);
		Root<LMSData> root = criteria.from(LMSData.class);

		criteria.groupBy(root.get("cred_role"), root.get("cred_product"));
		criteria.multiselect(root.get("cred_role"), root.get("cred_product"), cb.count(root.get("cred_product")));
		criteria.where(cb.equal(root.get("partnerid"), pid));

		List<Tuple> lmsData = em.createQuery(criteria).getResultList();
		JsonArray jsonArray = new JsonArray();

		for (Tuple tuple : lmsData) {
			jsonArray.add(new JsonObject().put("partnerid", pid).put("role", tuple.get(0)).put("product", tuple.get(1))
					.put("count", tuple.get(2)));
		}
		return jsonArray;
	}
}