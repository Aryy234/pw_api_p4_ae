package uce.edu.ec.api.representation;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import uce.edu.ec.api.domain.Vehiculo;

@Getter
@Setter
public class VehiculoRepresentation {
    private Long id;
    private String marca;
    private String modelo;
    private String chasis;
    private Date fechaFabricacion;
    private Date fechaMatricula;

    // Convertir de VehiculoRepresentation a Vehiculo
    public Vehiculo toEntity() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(this.id);
        vehiculo.setMarca(this.marca);
        vehiculo.setModelo(this.modelo);
        vehiculo.setChasis(this.chasis);
        
        if (this.fechaFabricacion != null) {
            vehiculo.setFechaFabricacion(new java.sql.Date(this.fechaFabricacion.getTime()));
        }
        if (this.fechaMatricula != null) {
            vehiculo.setFechaMatricula(new java.sql.Date(this.fechaMatricula.getTime()));
        }
        
        return vehiculo;
    }

    // Convertir de Vehiculo a VehiculoRepresentation
    public static VehiculoRepresentation fromEntity(Vehiculo vehiculo) {
        VehiculoRepresentation representation = new VehiculoRepresentation();
        representation.setId(vehiculo.getId());
        representation.setMarca(vehiculo.getMarca());
        representation.setModelo(vehiculo.getModelo());
        representation.setChasis(vehiculo.getChasis());
        
        if (vehiculo.getFechaFabricacion() != null) {
            representation.setFechaFabricacion(new Date(vehiculo.getFechaFabricacion().getTime()));
        }
        if (vehiculo.getFechaMatricula() != null) {
            representation.setFechaMatricula(new Date(vehiculo.getFechaMatricula().getTime()));
        }
        
        return representation;
    }

}
