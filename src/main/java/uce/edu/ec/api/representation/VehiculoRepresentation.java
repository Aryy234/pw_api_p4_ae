package uce.edu.ec.api.representation;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculoRepresentation {
    private Long id;
    private String marca;
    private String modelo;
    private String chasis;
    private Date fechaFabricacion;
    private Date fechaMatricula;

}
