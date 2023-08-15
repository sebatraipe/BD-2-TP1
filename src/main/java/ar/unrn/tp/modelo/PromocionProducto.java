package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.List;

public class PromocionProducto implements Promocion {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double descuento;
    private Marca marca;

    public PromocionProducto(LocalDate fechaInicio, LocalDate fechaFin, double descuento, Marca marca) {
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
    public double calcularDescuento(List<Producto> productos) {

        return productos.stream()
                .filter(p -> p.marca().equals(this.marca))
                .mapToDouble(p -> p.precio() * this.descuento)
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
