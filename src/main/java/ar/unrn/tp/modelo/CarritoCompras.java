package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.List;

public class CarritoCompras {

    private List<Producto> productos;
    private List<Promocion> promociones;

    public CarritoCompras(List<Producto> productos, List<Promocion> promociones) {
        this.productos = productos;
        this.promociones = promociones;
    }

    public double montoTotal() {

        double montoTotal = this.productos.stream()
                .mapToDouble(Producto::precio)
                .sum();

        double descuentoTotal = this.promociones.stream()
                .filter(promocion -> promocion.estaActiva(LocalDate.now()))
                .mapToDouble(promocion -> promocion.calcularDescuento(this.productos))
                .sum();

        return montoTotal -= descuentoTotal;
    }

    public double montoTotal(TarjetaCredito tarjetaCredito) {

        double montoTotal = this.productos.stream()
                .mapToDouble(Producto::precio)
                .sum();

        double descuentoTotal = this.promociones.stream()
                .filter(promocion -> promocion.estaActiva(LocalDate.now()) && tarjetaCredito.validarTarjeta())
                .mapToDouble(promocion -> promocion.calcularDescuento(this.productos))
                .sum();

        return montoTotal -= descuentoTotal;
    }

    @Override
    public String toString() {
        return "CarritoCompras{" +
                "productos=" + productos +
                '}';
    }
}
