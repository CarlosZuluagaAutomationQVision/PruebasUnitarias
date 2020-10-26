package junitdemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import junitdemo.enums.CategoriaDeProductos;
import junitdemo.enums.Localidad;
import junitdemo.modelos.Impuesto;
import junitdemo.modelos.Producto;
import junitdemo.repositorios.RepositorioDeImpuestos;
import junitdemo.repositorios.RepositorioDeProductos;
import junitdemo.servicios.ServicioDeProductos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PruebaServicioCostoTotalProducto {

  @MockBean
  RepositorioDeProductos repositorioDeProductos;

  @MockBean
  RepositorioDeImpuestos repositorioDeImpuestos;

  @Autowired
  ServicioDeProductos servicioDeProductos;

  @Test
  public void pruebaCostoTotal() throws Exception {
    //Dado que
    int idDelProducto = 1;
    Localidad localidad = Localidad.COLOMBIA;

    Producto producto = new Producto();
    producto.setPrecioUnitario(BigDecimal.ONE);
    producto.setCategoria(CategoriaDeProductos.ELECTRONICOS);
    Optional<Producto> productoOptional = Optional.of(producto);
    when(repositorioDeProductos.findById(idDelProducto)).thenReturn(productoOptional);

    Impuesto impuesto = new Impuesto();
    impuesto.setImporte(BigDecimal.valueOf(0.05));
    Optional<Impuesto> impuestoOptional = Optional.of(impuesto);
    when(repositorioDeImpuestos.findByLocalidadAndCategoria(Localidad.COLOMBIA, CategoriaDeProductos.ELECTRONICOS))
        .thenReturn(impuestoOptional);

    //Cuando
    BigDecimal resultado = servicioDeProductos
        .calcularMontoAPagarPorProducto(idDelProducto, localidad);

    //Entonces
    assertEquals(new BigDecimal("1.05"), resultado);
  }



}
