package ar.unrn.tp.servicios;

import ar.unrn.tp.api.DescuentoService;

import java.time.LocalDate;

public class Descuento implements DescuentoService {
    @Override
    public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta, double porcentaje) {

    }

    @Override
    public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, double porcentaje) {

    }
}
