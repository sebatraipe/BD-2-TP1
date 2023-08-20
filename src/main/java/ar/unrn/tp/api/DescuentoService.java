package ar.unrn.tp.api;

import java.time.LocalDate;

public interface DescuentoService {

    //Validar que la fecha no se superpongan
    void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde,
                                  LocalDate fechaHasta, double porcentaje);

    //Validar que la fecha no se superpongan
    void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, double porcentaje);
}
