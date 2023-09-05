package ar.unrn.tp.main;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.*;
import ar.unrn.tp.servicios.ClienteServiceImpl;
import ar.unrn.tp.servicios.DescuentoServiceImpl;
import ar.unrn.tp.servicios.ProductoServiceImpl;
import ar.unrn.tp.servicios.VentaServiceImpl;

import javax.jdo.listener.ClearCallback;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        ClienteService clienteService = new ClienteServiceImpl("jpa-objectdb");
//        clienteService.agregarTarjeta(6L, "11111111", "Visa");

//        VentaService ventaService = new VentaServiceImpl("jpa-objectdb");

//        productoService.crearProducto("0004", "Sueter", 25.000,
//                17L, 19L);
//        productoService.modificarProducto(26L, "Pantalon", 25.000, 18L);

//        System.out.println(productoService.listarProductos());

//        descuentoService.crearDescuentoSobreTotal ("12345678", LocalDate.now().minusDays(1),
//                LocalDate.now().plusDays(12), 0.05);

//        ventaService.realizarVenta(6L, List.of(23L, 24L), 2L);

    }
}
