package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.List;

public interface Descuento {

    boolean estaActiva(LocalDate fecha);
    double calcularDescuento(List<Producto> productos, TarjetaCredito tarjetaCredito);
}
