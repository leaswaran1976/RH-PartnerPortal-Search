package com.redhat.partnerportal.rest.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import io.quarkus.panache.common.Sort;

@Path("/announcements")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnnouncementService {
	public static final Logger logger = Logger.getLogger(AnnouncementService.class);

	@GET
	public List<Announcements> list() {
		return Announcements.listAll(Sort.by("end_date"));
	}

	@GET
	@Path("/active")
	public List<Announcements> getActiveAnnouncements() {
		Stream<Announcements> sls = Announcements.streamAll();
		SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yyyy");
		sls = sls.filter(p -> {
			try {
				return sfd.parse(p.end_date).after(sfd.parse(sfd.format(new Date())));
			} catch (ParseException e) {
				logger.fatal(e);
				return false;
			}
		});
		return sls.collect(Collectors.toList());
	}
}
