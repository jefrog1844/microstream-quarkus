package com.mfmea.systemfx.business.hardware.boundary;

import java.util.Collection;
import java.util.Optional;

import com.mfmea.systemfx.business.hardware.controller.HardwareStorage;
import com.mfmea.systemfx.business.hardware.entity.Hardware;

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

@Path("/dfmeas/{dfmeaId}/hardware")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HardwareResource {

    @Inject
    HardwareStorage hardwareStorage;

    @GET
    public Collection<Hardware> getAll(@PathParam("dfmeaId") String dfmeaId) {
        return hardwareStorage.getHardwareByDfmeaId(dfmeaId);
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id, @PathParam("dfmeaId") String dfmeaId) {
        Optional<Hardware> byId = hardwareStorage.getById(id, dfmeaId);

        Response.ResponseBuilder builder = byId.map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND));

        return builder.build();
    }

    @POST
    public Response create(Hardware hardware, @PathParam("dfmeaId") String dfmeaId, @Context UriInfo uriInfo) {
        Hardware created = hardwareStorage.create(hardware, dfmeaId);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(created.getId());
        return Response.created(uriBuilder.build()).entity(created).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id, @PathParam("dfmeaId") String dfmeaId) {
        hardwareStorage.removeById(id, dfmeaId);
        return Response.noContent().build();
    }
}
