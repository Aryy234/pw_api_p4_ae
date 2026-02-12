package uce.edu.ec.api.representation;

import java.time.LocalDate;
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
    private LocalDate fechaFabricacion;
    private LocalDate fechaMatricula;

    // Convertir de VehiculoRepresentation a Vehiculo
    public Vehiculo toEntity() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(this.id);
        vehiculo.setMarca(this.marca);
        vehiculo.setModelo(this.modelo);
        vehiculo.setChasis(this.chasis);
        
        if (this.fechaFabricacion != null) {
            vehiculo.setFechaFabricacion(java.sql.Date.valueOf(this.fechaFabricacion));
        }
        if (this.fechaMatricula != null) {
            vehiculo.setFechaMatricula(java.sql.Date.valueOf(this.fechaMatricula));
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
            representation.setFechaFabricacion(vehiculo.getFechaFabricacion().toLocalDate());
        }
        if (vehiculo.getFechaMatricula() != null) {
            representation.setFechaMatricula(vehiculo.getFechaMatricula().toLocalDate());
        }
        
        return representation;
    }

}
