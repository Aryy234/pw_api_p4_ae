package uce.edu.ec.api.aplication;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.api.domain.Vehiculo;
import uce.edu.ec.api.infraestructure.VehiculoRepository;

@ApplicationScoped
public class VehiculoService {

    @Inject
    private VehiculoRepository vehiculoRepository;

    @Transactional
    public void crearVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.persist(vehiculo);
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.listAll();
    }


}
