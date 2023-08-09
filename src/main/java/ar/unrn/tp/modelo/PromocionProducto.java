package ar.unrn.tp.modelo;

import java.time.LocalDate;

public class PromocionProducto implements Promocion {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double descuento;
    private Marca marca;

    @Override
    public void estaActivo(LocalDate fecha) {

    }

    @Override
    public void calcularDescuento(CarritoCompras carritoCompras) {

    }
}
