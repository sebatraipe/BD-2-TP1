package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.List;

public class DescuentoProducto implements Descuento {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double descuento;
    private Marca marca;

    public DescuentoProducto(LocalDate fechaInicio, LocalDate fechaFin, double descuento, Marca marca) {

        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("Las fechas de validez están superpuestas...");
        }

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.marca = marca;
    }

    @Override
    public boolean estaActiva(LocalDate fecha) {
        return fecha.isAfter(fechaInicio) && fecha.isBefore(fechaFin);
    }

    @Override
    public double calcularDescuento(List<Producto> productos, TarjetaCredito tarjetaCredito) {

        return productos.stream()
                .filter(p -> p.verificarMarca(this.marca))
                .mapToDouble(p -> p.descuento(this.descuento))
                .sum();
    }

    @Override
    public String toString() {
        return "PromocionProducto{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", descuento=" + descuento +
                ", marca=" + marca +
                '}';
    }
}
