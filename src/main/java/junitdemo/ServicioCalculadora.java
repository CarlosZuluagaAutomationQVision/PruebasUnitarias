package junitdemo;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ServicioCalculadora {

  List<String> historiasEjecuciones = new LinkedList<>();

  public int sumar(final int a, final int b){
    int resultado = a + b;
    historiasEjecuciones.add(String.format("sumar: %d + %d = %d", a, b, resultado));
    return resultado;
  }

  public float multiplicar(final float a, final float b) {
    float resultado = a * b;
    historiasEjecuciones.add(String.format("Multiplicar: %f x %f = %f", a, b, resultado));
    return resultado;
  }

  public double dividir(final double a, final double b) throws DivisionEntreCeroException {
    if(b== 0) throw new DivisionEntreCeroException();

    double resultado = a / b;
    historiasEjecuciones.add(String.format("Dividir: %f / %f = %f", a, b, resultado));
    return resultado;
  }

  public class DivisionEntreCeroException extends Throwable {

  }
}
