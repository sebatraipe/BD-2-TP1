package ar.unrn.tp.modelo;

import java.time.LocalDate;

public interface Promocion {

    void estaActivo(LocalDate fecha);
    void calcularDescuento(CarritoCompras carritoCompras);
}
