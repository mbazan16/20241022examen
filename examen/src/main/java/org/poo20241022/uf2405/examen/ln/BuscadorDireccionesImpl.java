package org.poo20241022.uf2405.examen.ln;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.poo20241022.uf2405.examen.entities.Direccion;
import org.poo20241022.uf2405.examen.exceptions.CodeError;
import org.poo20241022.uf2405.examen.exceptions.ServicioException;
import org.poo20241022.uf2405.examen.repositories.DireccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscadorDireccionesImpl implements BuscadorDirecciones {

    private static final Logger log = LoggerFactory.getLogger(BuscadorDireccionesImpl.class);

    @Autowired
    private DireccionRepository repository;

    /**
     * Método para obtener todas las direcciones
     * 
     * @return Lista de direcciones
     * @throws ServicioException en caso de error
     */
    @Override
    public List<Direccion> listDirecciones() throws ServicioException {
        log.info("[listDirecciones]");
        return ejecutarOperacion(() -> repository.findAll());
    }

    /**
     * Método para conseguir una dirección por su ID
     * 
     * @param idDireccion ID de la dirección
     * @return Dirección encontrada
     * @throws ServicioException en caso de error
     */
    @Override
    public Direccion conseguirDireccion(Integer idDireccion) throws ServicioException {
        log.info("[conseguirDireccion]");
        log.debug("[idDireccion: {}]", idDireccion);
        return ejecutarOperacion(() -> 
            repository.findById(idDireccion)
                .orElseThrow(() -> new ServicioException(CodeError.DIRECCION_NOT_FOUND))
        );
    }

    /**
     * Método para guardar una nueva dirección
     * 
     * @param direccion Dirección a guardar
     * @return Dirección guardada
     * @throws ServicioException en caso de error
     */
    @Override
    public Direccion grabarDireccion(Direccion direccion) throws ServicioException {
        log.info("[grabarDireccion]");
        log.debug("[Direccion: {}]", direccion);
        return ejecutarOperacion(() -> repository.save(direccion));
    }

    /**
     * Método para eliminar una dirección por su ID
     * 
     * @param idDireccion ID de la dirección a eliminar
     * @throws ServicioException en caso de error
     */
    @Override
    public void eliminarDireccion(Integer idDireccion) throws ServicioException {
        log.info("[eliminarDireccion]");
        log.debug("[idDireccion: {}]", idDireccion);
        ejecutarOperacion(() -> {
            Optional<Direccion> direccionOp = repository.findById(idDireccion);
            if (!direccionOp.isPresent()) {
                throw new ServicioException(CodeError.DIRECCION_NOT_FOUND);
            }
            repository.deleteById(idDireccion);
            log.info("Direccion eliminada con éxito [idDireccion: {}]", idDireccion);
            return null;
        });
    }

    /**
     * Método para obtener direcciones por el código del país
     * 
     * @param codigoPais Código del país
     * @return Lista de direcciones
     * @throws ServicioException en caso de error
     */
    @Override
    public List<Direccion> listDireccionesByCodigoPais(String codigoPais) throws ServicioException {
        log.info("[listDireccionesByCodigoPais]");
        log.debug("[codigoPais: {}]", codigoPais);
        return ejecutarOperacion(() -> repository.findAllByCodigoPais(codigoPais));
    }

    /**
     * Método para conseguir una dirección por el ID de su departamento
     * 
     * @param idDepartamento ID del departamento
     * @return Dirección encontrada
     * @throws ServicioException en caso de error
     */
    @Override
    public Direccion conseguirDireccionByDepartamentoId(int idDepartamento) throws ServicioException {
        log.info("[conseguirDireccionByDepartamentoId]");
        log.debug("[idDepartamento: {}]", idDepartamento);
        return ejecutarOperacion(() -> 
            repository.findByIdDepartamento(idDepartamento)
                .orElseThrow(() -> new ServicioException(CodeError.DIRECCION_NOT_FOUND))
        );
    }

    /**
     * Método para obtener direcciones por nombre de la región
     * 
     * @param nombreRegion Nombre de la región
     * @return Lista de direcciones
     * @throws ServicioException en caso de error
     */
    @Override
    public List<Direccion> listDireccionesByNombreRegion(String nombreRegion) throws ServicioException {
        log.info("[listDireccionesByNombreRegion]");
        log.debug("[nombreRegion: {}]", nombreRegion);
        return ejecutarOperacion(() -> repository.findAllByPais_Region_Nombre(nombreRegion));
    }

    /**
     * Método genérico para ejecutar operaciones en el repositorio
     * 
     * @param operacion Operación a ejecutar
     * @param <T> Tipo de resultado de la operación
     * @return Resultado de la operación
     * @throws ServicioException en caso de error
     */
    private <T> T ejecutarOperacion(Callable<T> operacion) throws ServicioException {
        try {
            return operacion.call();
        } catch (Exception e) {
            log.error("Exception", e);
            throw new ServicioException(CodeError.ERROR_GENERAL, e);
        }
    }
}