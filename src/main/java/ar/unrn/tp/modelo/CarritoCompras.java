        package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CarritoCompras {

    private List<Producto> productos;
    private List<Descuento> promociones;
    private Cliente cliente;

    public CarritoCompras(List<Producto> productos, List<Descuento> promociones, Cliente cliente) {
        this.productos = productos;
        this.promociones = promociones;
        this.cliente = cliente;
    }

    public double montoTotal(TarjetaCredito tarjetaCredito) {

        double montoTotal = this.calcularMontoTotal();

        double descuentoTotal = this.descuentoTotal(tarjetaCredito);

        return montoTotal -= descuentoTotal;
    }

    private double calcularMontoTotal() {
        return this.productos.stream()
                .mapToDouble(Producto::precio)
                .sum();
    }

    private double descuentoTotal(TarjetaCredito tarjetaCredito) {
        return this.promociones.stream()
                .filter(descuento -> descuento.estaActiva(LocalDate.now()))
                .mapToDouble(descuento -> descuento.calcularDescuento(this.productos, tarjetaCredito))
                .sum();
    }

    public Venta realizarPago(TarjetaCredito tarjetaCredito) {

        if (!this.cliente.validarTarjeta(tarjetaCredito)) {
            System.out.println("La tarjeta falló...");
        }
        double montoTotal = this.calcularMontoTotal();
        double descuentoTotal = this.descuentoTotal(tarjetaCredito);
        montoTotal -= descuentoTotal;

        return new Venta(LocalDateTime.now(), this.cliente, this.productos, montoTotal);
    }

    @Override
    public String toString() {
        return "CarritoCompras{" +
                "productos=" + productos +
                '}';
    }
}
