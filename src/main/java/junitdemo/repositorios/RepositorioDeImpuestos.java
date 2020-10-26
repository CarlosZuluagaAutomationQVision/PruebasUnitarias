package junitdemo.repositorios;

import java.util.Optional;
import junitdemo.enums.CategoriaDeProductos;
import junitdemo.modelos.Impuesto;
import junitdemo.enums.Localidad;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioDeImpuestos extends CrudRepository<Impuesto, Integer> {

    Optional<Impuesto> findByLocalidadAndCategoria(Localidad localidad, CategoriaDeProductos categoria);

}
