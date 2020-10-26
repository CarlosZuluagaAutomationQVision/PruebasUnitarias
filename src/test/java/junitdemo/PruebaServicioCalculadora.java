package junitdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import junitdemo.ServicioCalculadora.DivisionEntreCeroException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PruebaServicioCalculadora {

  @Autowired
  ServicioCalculadora servicioCalculadora;

  @BeforeClass
  public static void antesTodaClass(){
    System.out.println("BeforeClass");
  }

  @Before
  public void antesCadaTest(){
    System.out.println("Before");
    servicioCalculadora.historiasEjecuciones = new LinkedList<String>();
    System.out.println(String.format("Historial antes de cada prueba: %d", servicioCalculadora.historiasEjecuciones.size()));
  }

  @Test
  public void pruebaMetodoSuma(){
    System.out.println("Prueba de metodo suma");
    //Dado que tenemos dos enteros
    int a= 7;
    int b= 4;

    //Cuando ejecutamos el metodo suma
    int resultado = servicioCalculadora.sumar(a, b);

    //Entonces verificamos el resultado
    assertEquals(11, resultado);
    System.out.println(String.format("Historial: %d", servicioCalculadora.historiasEjecuciones.size()));
    assertEquals(1, servicioCalculadora.historiasEjecuciones.size());
    assertTrue(servicioCalculadora.historiasEjecuciones.get(0).startsWith("sumar"));
  }

  @Test
  public void pruebaMetodoSumaNegativos(){
    System.out.println("Prueba de metodo suma con valores negativos");
    //Dado que tenemos dos enteros
    int a= -7;
    int b= -4;

    //Cuando ejecutamos el metodo suma
    int resultado = servicioCalculadora.sumar(a, b);

    //Entonces verificamos el resultado
    assertEquals(-11, resultado);
    System.out.println(String.format("Historial: %d", servicioCalculadora.historiasEjecuciones.size()));
    assertEquals(1, servicioCalculadora.historiasEjecuciones.size());
    assertTrue(servicioCalculadora.historiasEjecuciones.get(0).startsWith("sumar"));
  }

  @Test
  public void pruebaMetodoMultiplicar(){
    System.out.println("Prueba de metodo multiplicacion");
    //Dado que tenemos dos numeros flotantes
    float a= 3.2F;
    float b= 4.99F;

    //Cuando ejecutamos el metodo multiplicar
    float resultado = servicioCalculadora.multiplicar(a, b);

    //Entonces verificamos el resultado
    assertEquals(15.9, resultado, 0.1);
    System.out.println(String.format("Historial: %d", servicioCalculadora.historiasEjecuciones.size()));
    assertEquals(1, servicioCalculadora.historiasEjecuciones.size());
    assertTrue(servicioCalculadora.historiasEjecuciones.get(0).startsWith("Multiplicar"));
  }

  @Test
  public void pruebaDivision() throws DivisionEntreCeroException {
    System.out.println("Prueba de metodo dividir");
    //Dado que tenemos dos numeros flotantes
    double a= 3.0;
    double b= 1.0;

    //Cuando ejecutamos el metodo multiplicar
    double resultado = servicioCalculadora.dividir(a, b);

    //Entonces verificamos el resultado
    assertEquals(3, resultado, 0.0);
    System.out.println(String.format("Historial: %d", servicioCalculadora.historiasEjecuciones.size()));
    assertEquals(1, servicioCalculadora.historiasEjecuciones.size());
    assertTrue(servicioCalculadora.historiasEjecuciones.get(0).startsWith("Dividir"));
  }

  @Test (expected = DivisionEntreCeroException.class)
  public void pruebaDivisionEntreCero() throws DivisionEntreCeroException {
    System.out.println("Prueba de metodo dividir entre cero");
    //Dado que tenemos dos numeros flotantes
    double a= 3.0;
    double b= 0;

    //Cuando ejecutamos el metodo multiplicar
    double resultado = servicioCalculadora.dividir(a, b);

    //Entonces verificamos el resultado
    assertEquals(3, resultado, 0.0);
    System.out.println(String.format("Historial: %d", servicioCalculadora.historiasEjecuciones.size()));
    assertEquals(1, servicioCalculadora.historiasEjecuciones.size());
  }



  @After
  public void despuesCadaTest(){
    System.out.println("After");
    System.out.println(String.format("Historial despues de cada prueba: %d", servicioCalculadora.historiasEjecuciones.size()));
  }

  @AfterClass
  public static void despuesTodaClass(){
    System.out.println("AfterClass");
  }

}
