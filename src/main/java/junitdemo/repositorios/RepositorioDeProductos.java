package junitdemo.repositorios;


import junitdemo.modelos.Producto;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioDeProductos extends CrudRepository<Producto, Integer> {
}
