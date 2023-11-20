package com.mfmea.systemfx.business.dfmea.boundary;

import java.util.Collection;
import java.util.Optional;

import com.mfmea.systemfx.business.dfmea.controller.DfmeaService;
import com.mfmea.systemfx.business.dfmea.entity.Dfmea;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/dfmeas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DfmeaResource {

    @Inject
    DfmeaService dfmeaService;

    @GET
    public Collection<Dfmea> getAll() {
        return dfmeaService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        Optional<Dfmea> byId = dfmeaService.getById(id);

        Response.ResponseBuilder builder = byId.map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND));

        return builder.build();
    }

    @GET
    @Path("/by/{name}")
    public Response findBy(@PathParam("name") String name) {
        Optional<Dfmea> byName = dfmeaService.findByName(name);

        Response.ResponseBuilder builder = byName.map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND));

        return builder.build();

    }

    @POST
    public Response create(Dfmea dfmea, @Context UriInfo uriInfo) {
        Dfmea savedDfmea = dfmeaService.create(dfmea);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(savedDfmea.getId());
        return Response.created(uriBuilder.build()).entity(savedDfmea).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        dfmeaService.removeById(id);
        return Response.noContent().build();
    }
}
