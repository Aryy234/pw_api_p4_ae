package uce.edu.ec.api.interfaces;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.ec.api.aplication.VehiculoService;
import uce.edu.ec.api.domain.Vehiculo;
import uce.edu.ec.api.representation.VehiculoRepresentation;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Path("/vehiculos")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiculoResource {

    @Inject
    private VehiculoService vehiculoService;

    @Path("/crear")
    @POST
    public Response crearVehiculo(VehiculoRepresentation vehiculoRepresentation) {
        vehiculoService.crearVehiculo(vehiculoRepresentation.toEntity());
        return Response.status(Response.Status.CREATED)
                .entity("Vehículo creado exitosamente")
                .build();
    }

    @Path("/listar")
    @GET
    public Response listarVehiculos() {
        List<VehiculoRepresentation> representaciones = vehiculoService.listarVehiculos()
                .stream()
                .map(VehiculoRepresentation::fromEntity)
                .collect(Collectors.toList());
        
        return Response.ok(representaciones).build();
    }

}
