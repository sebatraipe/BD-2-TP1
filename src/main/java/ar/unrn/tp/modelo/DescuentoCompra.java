package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.List;

public class DescuentoCompra implements Descuento {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double descuento;
    private TarjetaCredito tarjetaCredito;

    public DescuentoCompra(LocalDate fechaInicio, LocalDate fechaFin, double descuento, TarjetaCredito tarjetaCredito) {

        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("Las fechas de validez están superpuestas...");
        }

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.tarjetaCredito = tarjetaCredito;
    }

    @Override
    public boolean estaActiva(LocalDate fecha) {
        return fecha.isAfter(fechaInicio) && fecha.isBefore(fechaFin);
    }

    @Override
    public double calcularDescuento(List<Producto> productos, TarjetaCredito tarjetaCredito) {

        double montoTotal = 0;
        if (this.tarjetaCredito.verificarTarjeta(tarjetaCredito)) {
            montoTotal = productos.stream()
                    .mapToDouble(Producto::precio)
                    .sum();
        }
        return montoTotal * this.descuento;
    }
}
