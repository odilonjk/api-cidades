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
	@Path("/capitais/{order}")
	public Response findCapitals(@PathParam(value = "order") String order) {
		try {
			return Response.ok(service.findCapitals(order)).build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/cidade/estado/totais")
	public Response findTotalByStates() {
		try {
			return Response.ok(service.findTotalByStates()).build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/cidade/{ibgeId}")
	public Response find(@PathParam(value = "ibgeId") Integer ibgeId) {
		try {
			return Response.ok(service.find(ibgeId)).build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cidade")
	public Response register(Cidade cidade) {
		try {
			service.register(cidade);
			return Response.ok().build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/cidade/{ibgeId}")
	public Response delete(@PathParam(value = "ibgeId") Integer ibgeId) {
		try {
			service.delete(ibgeId);
			return Response.ok().build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/cidade/estado/{uf}")
	public Response findCitiesByState(@PathParam(value = "uf") String uf) {
		try {
			return Response.ok(service.findCitiesByState(uf)).build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/total/registros")
	public Response findTotalRecords() {
		try {
			return Response.ok(service.findTotalRecords()).build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/total/{column}")
	public Response findTotalRecordsByColumn(@PathParam(value = "column") String column) {
		try {
			return Response.ok(service.findTotalRecordsByColumn(column)).build();
		}
		catch(Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/estados-extremos")
	public Response findExtremeStates() {
		try {
			return Response.ok(service.findExtremeStates()).build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/cidades-mais-distantes")
	public Response findMoreDistantCities() {
		try {
			return Response.ok(service.findMoreDistant()).build();
		}
		catch (Exception e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/consulta/{column}/{filter}")
	public Response search(@PathParam(value = "column") String column, @PathParam(value = "filter") String filter) {
		try {
			return Response.ok(service.search(column, filter)).build();
		}
		catch (Exception e){
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
}
