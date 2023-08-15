package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.List;

public class PromocionCompra implements Promocion {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double descuento;

    public PromocionCompra(LocalDate fechaInicio, LocalDate fechaFin, double descuento) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
    }

    @Override
    public boolean estaActiva(LocalDate fecha) {
        return fecha.isAfter(fechaInicio) && fecha.isBefore(fechaFin);
    }

    @Override
    public double calcularDescuento(List<Producto> productos) {
        return 0;
    }
}
