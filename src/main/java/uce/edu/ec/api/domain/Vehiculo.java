package uce.edu.ec.api.domain;

import java.sql.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehiculo")
@SequenceGenerator(name = "seq_vehiculo", sequenceName = "seq_vehiculo", allocationSize = 1)    
public class Vehiculo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehiculo")
    private Long id;

    private String marca;
    private String modelo;
    private String chasis;
    private Date fechaFabricacion;
    private Date fechaMatricula;
}
