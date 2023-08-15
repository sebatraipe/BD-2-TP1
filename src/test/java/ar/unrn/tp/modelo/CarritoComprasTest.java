package ar.unrn.tp.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarritoComprasTest {

    @Test
    public void testCalcularMontoSinDescuentosVigentesPeroConDescuentosCaducados() {

        Categoria categoriaCalzado = new Categoria("Calzado");

        Marca marcaNike = new Marca("Nike");

        Producto producto1 = new Producto("1234", "Zapatillas", 45.000,
                categoriaCalzado, marcaNike);
        Producto producto2 = new Producto("0000", "Zapatillas", 35.000,
                categoriaCalzado, marcaNike);

        //Creo una promocion de descuento que ya haya caducado
        Promocion promocionCaducada = new PromocionProducto(LocalDate.now().minusDays(2),
                LocalDate.now().minusDays(1), 5, marcaNike);

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2), List.of(promocionCaducada));

        double montoTotal = carritoCompras.montoTotal();

        assertEquals(80.0, montoTotal, 0.01);
    }

    @Test
    public void testCalcularMontoConDescuentoVigenteParaProductos() {

        Categoria categoriaCalzado = new Categoria("Calzado");

        Marca marcaAcme = new Marca("Acme");

        Producto producto1 = new Producto("1234", "Zapatillas", 45.000,
                categoriaCalzado, marcaAcme);
        Producto producto2 = new Producto("0000", "Zapatillas", 35.000,
                categoriaCalzado, marcaAcme);

        //Creo una promocion de descuento vigente
        Promocion promocionVigente = new PromocionProducto(LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(6), 0.05, marcaAcme);

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2), List.of(promocionVigente));

        double montoTotal = carritoCompras.montoTotal();

        assertEquals(76.0, montoTotal, 0.01);
    }


}
