package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.List;

public class CarritoCompras {

    private List<Producto> productos;
    private List<Promocion> promociones;
    private double total;

    public CarritoCompras(List<Producto> productos, List<Promocion> promociones) {
        this.productos = productos;
        this.promociones = promociones;
    }

    public double montoTotal() {

        double montoTotal = this.productos.stream()
                .mapToDouble(Producto::precio)
                .sum();

        double descuento = this.promociones.stream()
                .filter(promocion -> promocion.estaActiva(LocalDate.now()))
                .mapToDouble(promocion -> promocion.calcularDescuento(this.productos))
                .sum();

        return montoTotal -= descuento;
    }

    @Override
    public String toString() {
        return "CarritoCompras{" +
                "productos=" + productos +
                '}';
    }
}
