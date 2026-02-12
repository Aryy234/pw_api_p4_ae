package uce.edu.ec.api.aplication;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.ec.api.domain.Vehiculo;
import uce.edu.ec.api.infraestructure.VehiculoRepository;

@ApplicationScoped
public class VehiculoService {

    @Inject
    private VehiculoRepository vehiculoRepository;

    //CRUD completo de Vehiculo

    //crear un vehiculo
    public void crearVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.persist(vehiculo);
    }

    //listar todos los vehiculos
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.listAll();
    }


}
