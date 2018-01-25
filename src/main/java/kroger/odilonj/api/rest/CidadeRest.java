package kroger.odilonj.api.rest;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import kroger.odilonj.api.entity.Cidade;
import kroger.odilonj.api.service.CidadeService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CidadeRest {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private CidadeService service;
	
	@GET
	@Path("/capitais")
	public Response findCapitals() {
		try {
			return Response.ok(service.findCapitals()).build();
		}
		catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/total-por-estados")
	public Response findTotalByStates() {
		try {
			return Response.ok(service.findTotalByStates()).build();
		}
		catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/cidade/{ibgeId}")
	public Response find(@PathParam(value = "ibgeId") Integer ibgeId) {
		try {
			return Response.ok(service.find(ibgeId)).build();
		}
		catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cidade")
	public Response register(Cidade cidade) {
		service.register(cidade);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/cidade")
	public Response delete(Integer ibgeId) {
		service.delete(ibgeId);
		return Response.ok().build();
	}
	
	@GET
	@Path("/cidades-por-estado/{uf}")
	public Response findCitiesByState(@PathParam(value = "uf") String uf) {
		try {
			return Response.ok(service.findCitysByState(uf)).build();
		}
		catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	
	
}
