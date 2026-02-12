package uce.edu.ec.api.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.ec.api.domain.Vehiculo;

@ApplicationScoped
public class VehiculoRepository implements PanacheRepository<Vehiculo> {
    

}
