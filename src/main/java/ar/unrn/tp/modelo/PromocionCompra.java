package ar.unrn.tp.modelo;

import java.time.LocalDate;

public class PromocionCompra implements Promocion {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double descuento;

    // Preguntar!!
    private TarjetaCredito tarjetaCredito;

    public PromocionCompra(LocalDate fechaInicio, LocalDate fechaFin, double descuento, TarjetaCredito tarjetaCredito) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.tarjetaCredito = tarjetaCredito;
    }

    @Override
    public void estaActivo(LocalDate fecha) {

    }

    @Override
    public void calcularDescuento(CarritoCompras carritoCompras) {

    }
}
