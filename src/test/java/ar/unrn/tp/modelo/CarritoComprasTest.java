package ar.unrn.tp.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.lang.IllegalArgumentException;

import static org.junit.jupiter.api.Assertions.*;

class CarritoComprasTest {

    @Test
    public void testCalcularMontoSinDescuentosVigentesPeroConDescuentosCaducados() {


        TarjetaCredito tarjetaCreditoComarca = new TarjetaCredito("12345678",
                "Comarca", true);
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito( "12345678",
                "Naranja Z", true);

        Categoria categoriaCalzado = new Categoria("Calzado");

        Marca marcaNike = new Marca("Nike");
        Marca marcaAcme = new Marca("Acme");

        Producto producto1 = new Producto("1234", "Zapatillas", 45.000,
                categoriaCalzado, marcaNike);
        Producto producto2 = new Producto("0000", "Zapatillas", 35.000,
                categoriaCalzado, marcaNike);

        Producto producto3 = new Producto("0000", "Zapatillas", 10.000,
                categoriaCalzado, marcaAcme);

        //Creo una promocion de descuento que ya haya caducado
        Descuento descuentoCaducadaProducto = new DescuentoProducto(LocalDate.now().minusDays(2),
                LocalDate.now().minusDays(1), 0.05, marcaNike);

        Descuento descuentoCaducadaMarca = new DescuentoCompra(LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1), 0.08, tarjetaCreditoComarca);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", List.of(tarjetaCreditoSebastian));

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(descuentoCaducadaProducto, descuentoCaducadaMarca), clienteSebastian);

        double montoTotal = carritoCompras.montoTotal(tarjetaCreditoSebastian);

        assertEquals(90.0, montoTotal, 0.01);
    }

    @Test
    public void testCalcularMontoConDescuentoVigenteParaProductos() {

        TarjetaCredito tarjetaCreditoComarca = new TarjetaCredito("12345678",
                "Comarca", true);
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito("12345678",
                "Naranja Z", true);

        Categoria categoriaCalzado = new Categoria("Calzado");

        Marca marcaNike = new Marca("Nike");
        Marca marcaAcme = new Marca("Acme");

        Producto producto1 = new Producto("1234", "Zapatillas", 45.000,
                categoriaCalzado, marcaNike);
        Producto producto2 = new Producto("0000", "Zapatillas", 35.000,
                categoriaCalzado, marcaNike);

        Producto producto3 = new Producto("0000", "Zapatillas", 10.000,
                categoriaCalzado, marcaAcme);

        //Creo una promocion de descuento que ya haya caducado
        Descuento descuentoVigenteProducto = new DescuentoProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.05, marcaAcme);

        Descuento descuentoCompra = new DescuentoCompra(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.08, tarjetaCreditoComarca);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", List.of(tarjetaCreditoSebastian));

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(descuentoVigenteProducto, descuentoCompra), clienteSebastian);

        double montoTotal = carritoCompras.montoTotal(tarjetaCreditoSebastian);

        assertEquals(89.5, montoTotal, 0.01);
    }

    @Test
    public void testCalcularMontoConDescuentoVigenteDelTipoMedioDePago() {

        TarjetaCredito tarjetaCreditoComarca = new TarjetaCredito("12345678",
                "Comarca", true);
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito("23147893",
                "Comarca", true);

        Categoria categoriaCalzado = new Categoria("Calzado");

        Marca marcaNike = new Marca("Nike");
        Marca marcaAcme = new Marca("Acme");

        Producto producto1 = new Producto("1234", "Zapatillas", 45.000,
                categoriaCalzado, marcaNike);
        Producto producto2 = new Producto("0000", "Zapatillas", 35.000,
                categoriaCalzado, marcaNike);

        Producto producto3 = new Producto("9999", "Zapatillas", 10.000,
                categoriaCalzado, marcaNike);

        //Creo una promocion de descuento que ya haya caducado
        Descuento descuentoVigenteProducto = new DescuentoProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.05, marcaAcme);

        Descuento descuentoCompra = new DescuentoCompra(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.08, tarjetaCreditoComarca);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", List.of(tarjetaCreditoSebastian));

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(descuentoVigenteProducto, descuentoCompra), clienteSebastian);

        double montoTotal = carritoCompras.montoTotal(tarjetaCreditoComarca);

        assertEquals(82.8, montoTotal, 0.01);
    }

    @Test
    public void testCalcularMontoConDescuentoVigenteDelTipoProductoYCompra() {

        TarjetaCredito tarjetaCreditoMemeCard = new TarjetaCredito("12345678",
                "MemeCard", true);
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito("23147893",
                "MemeCard", true);

        Categoria categoriaCalzado = new Categoria("Calzado");

        Marca marcaComarca = new Marca("Comarca");
        Marca marcaAcme = new Marca("Acme");

        Producto producto1 = new Producto("1234", "Zapatillas", 35.000,
                categoriaCalzado, marcaComarca);
        Producto producto2 = new Producto("0000", "Zapatillas", 45.000,
                categoriaCalzado, marcaComarca);
        Producto producto3 = new Producto("9999", "Zapatillas", 10.000,
                categoriaCalzado, marcaAcme);

        //Creo una promocion de descuento
        Descuento descuentoVigenteProducto = new DescuentoProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(12), 0.05, marcaComarca);

        Descuento descuentoCompra = new DescuentoCompra(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(12), 0.08, tarjetaCreditoMemeCard);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", List.of(tarjetaCreditoSebastian));

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(descuentoVigenteProducto, descuentoCompra), clienteSebastian);

        double montoTotal = carritoCompras.montoTotal(tarjetaCreditoMemeCard);

        assertEquals(78.8, montoTotal, 0.01);
    }

    @Test
    public void testRealizarPagoYVerificarQueSeGeneraLaVenta() {

        TarjetaCredito tarjetaCreditoMemeCard = new TarjetaCredito("12345678",
                "MemeCard", true);
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito("23147893",
                "MemeCard", true);

        Categoria categoriaCalzado = new Categoria("Calzado");

        Marca marcaComarca = new Marca("Comarca");
        Marca marcaAcme = new Marca("Acme");

        Producto producto1 = new Producto("1234", "Zapatillas", 45.000,
                categoriaCalzado, marcaComarca);
        Producto producto2 = new Producto("0000", "Zapatillas", 35.000,
                categoriaCalzado, marcaComarca);
        Producto producto3 = new Producto("9999", "Zapatillas", 10.000,
                categoriaCalzado, marcaAcme);

        //Creo una promocion de descuento
        Descuento descuentoVigenteProducto = new DescuentoProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.05, marcaComarca);

        Descuento descuentoCompra = new DescuentoCompra(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.08, tarjetaCreditoMemeCard);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", List.of(tarjetaCreditoSebastian));

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(descuentoVigenteProducto, descuentoCompra), clienteSebastian);

        Venta venta = carritoCompras.realizarPago(tarjetaCreditoSebastian);

        assertNotNull(venta);
        assertEquals(true, venta.verificarVentaPorFecha(LocalDateTime.now()));

    }

    @Test
    public void testCrearProductoSinCategoriaDescripcionPrecio() {
        assertThrows(IllegalArgumentException.class, () -> {
            Producto producto = new Producto("1234", "descripcion", 0.0,
                    null, null);
        });
    }

    @Test
    void testCrearDescuentoConFechasSuperpuestas() {

        assertThrows(IllegalArgumentException.class, () -> {
            Descuento descuentoProducto = new DescuentoProducto(LocalDate.now().plusDays(5),
                    LocalDate.now().plusDays(3), 0.05, new Marca("Marca"));

            TarjetaCredito tarjetaCreditoMemeCard = new TarjetaCredito("12345678",
                    "MemeCard", true);

            Descuento descuentoCompra = new DescuentoCompra(LocalDate.now().plusDays(5),
                    LocalDate.now().plusDays(3), 0.08, tarjetaCreditoMemeCard);
        });
    }
}
