package com.fuze.takehome.jaxrs.endpoint;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fuze.takehome.model.Designation;
import com.fuze.takehome.service.DesignationService;

@Path("/departments")
public class DesignationEndpoint {
	
	@Inject
	private DesignationService service; 

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Designation create(@Valid @NotNull Designation entity) {
			return service.create(entity);
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Designation read(@NotNull @PathParam("id") Long id) {
		return service.read(id);
	}

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Designation delete(@NotNull @PathParam("id") Long id) {
		return service.delete(id);		
	}
}
