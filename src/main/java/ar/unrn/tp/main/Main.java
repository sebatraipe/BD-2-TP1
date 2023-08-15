package ar.unrn.tp.main;

import ar.unrn.tp.modelo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TarjetaValidacion tarjetaValidacion = new TarjetaServicio();

        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito(tarjetaValidacion, "12345678",
                "Naranja Z");

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe",
                "12345678", "shtraipe@unrn.edu.ar", tarjetaCreditoSebastian);

        clienteSebastian.validarTarjeta();


        Categoria categoriaCalzado = new Categoria("Calzado");

        Marca marcaNike = new Marca("Nike");
//        Marca marcaFila = new Marca("Fila");

        Producto producto1 = new Producto("1234", "Zapatillas", 45.000,
                categoriaCalzado, marcaNike);
        Producto producto2 = new Producto("1234", "Zapatillas", 25.000,
                categoriaCalzado, marcaNike);

        Promocion promocionVigente = new PromocionProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(7), 0.05, marcaNike);

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2), List.of(promocionVigente));

        System.out.println("Total del carrito: " + carritoCompras.montoTotal());
    }
}
