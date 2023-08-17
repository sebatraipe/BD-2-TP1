package ar.unrn.tp.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarritoComprasTest {

    @Test
    public void testCalcularMontoSinDescuentosVigentesPeroConDescuentosCaducados() {

        TarjetaValidacion tarjetaValidacion = new TarjetaServicio();

        TarjetaCredito tarjetaCreditoComarca = new TarjetaCredito(tarjetaValidacion, "12345678",
                "Comarca");
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito(tarjetaValidacion, "12345678",
                "Naranja Z");

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
        Promocion promocionCaducadaProducto = new PromocionProducto(LocalDate.now().minusDays(2),
                LocalDate.now().minusDays(1), 0.05, marcaNike);

        Promocion promocionCaducadaMarca = new PromocionCompra(LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1), 0.08, tarjetaCreditoComarca);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", tarjetaCreditoSebastian);

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(promocionCaducadaProducto, promocionCaducadaMarca), clienteSebastian);

        double montoTotal = carritoCompras.montoTotal(tarjetaCreditoSebastian);

        assertEquals(90.0, montoTotal, 0.01);
    }

    @Test
    public void testCalcularMontoConDescuentoVigenteParaProductos() {

        TarjetaValidacion tarjetaValidacion = new TarjetaServicio();

        TarjetaCredito tarjetaCreditoComarca = new TarjetaCredito(tarjetaValidacion, "12345678",
                "Comarca");
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito(tarjetaValidacion, "12345678",
                "Naranja Z");

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
        Promocion promocionVigenteProducto = new PromocionProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.05, marcaAcme);

        Promocion promocionCompra = new PromocionCompra(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.08, tarjetaCreditoComarca);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", tarjetaCreditoSebastian);

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(promocionVigenteProducto, promocionCompra), clienteSebastian);

        double montoTotal = carritoCompras.montoTotal(tarjetaCreditoSebastian);

        assertEquals(89.5, montoTotal, 0.01);
    }

    @Test
    public void testCalcularMontoConDescuentoVigenteDelTipoMedioDePago() {

        TarjetaValidacion tarjetaValidacion = new TarjetaServicio();

        TarjetaCredito tarjetaCreditoComarca = new TarjetaCredito(tarjetaValidacion, "12345678",
                "Comarca");
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito(tarjetaValidacion, "23147893",
                "Comarca");

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
        Promocion promocionVigenteProducto = new PromocionProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.05, marcaAcme);

        Promocion promocionCompra = new PromocionCompra(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.08, tarjetaCreditoComarca);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", tarjetaCreditoSebastian);

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(promocionVigenteProducto, promocionCompra), clienteSebastian);

        double montoTotal = carritoCompras.montoTotal(tarjetaCreditoComarca);

        assertEquals(82.8, montoTotal, 0.01);
    }

    @Test
    public void testCalcularMontoConDescuentoVigenteDelTipoProductoYCompra() {

        TarjetaValidacion tarjetaValidacion = new TarjetaServicio();

        TarjetaCredito tarjetaCreditoMemeCard = new TarjetaCredito(tarjetaValidacion, "12345678",
                "MemeCard");
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito(tarjetaValidacion, "23147893",
                "MemeCard");

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
        Promocion promocionVigenteProducto = new PromocionProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.05, marcaComarca);

        Promocion promocionCompra = new PromocionCompra(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.08, tarjetaCreditoMemeCard);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", tarjetaCreditoSebastian);

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(promocionVigenteProducto, promocionCompra), clienteSebastian);

        double montoTotal = carritoCompras.montoTotal(tarjetaCreditoMemeCard);

        assertEquals(78.8, montoTotal, 0.01);
    }

    @Test
    public void testRealizarPagoYVerificarQueSeGeneraLaVenta() {

        TarjetaValidacion tarjetaValidacion = new TarjetaServicio();

        TarjetaCredito tarjetaCreditoMemeCard = new TarjetaCredito(tarjetaValidacion, "12345678",
                "MemeCard");
        TarjetaCredito tarjetaCreditoSebastian = new TarjetaCredito(tarjetaValidacion, "23147893",
                "MemeCard");

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
        Promocion promocionVigenteProducto = new PromocionProducto(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.05, marcaComarca);

        Promocion promocionCompra = new PromocionCompra(LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5), 0.08, tarjetaCreditoMemeCard);

        Cliente clienteSebastian = new Cliente("Sebastian", "Traipe", "12345678",
                "shtraipe@unrn.ed.ar", tarjetaCreditoSebastian);

        CarritoCompras carritoCompras = new CarritoCompras(List.of(producto1, producto2, producto3),
                List.of(promocionVigenteProducto, promocionCompra), clienteSebastian);

        Venta venta = carritoCompras.realizarPago(tarjetaCreditoSebastian);

        assertNotNull(venta);
        assertEquals(true, venta.verificarVentaPorFecha(LocalDateTime.now()));

    }
}