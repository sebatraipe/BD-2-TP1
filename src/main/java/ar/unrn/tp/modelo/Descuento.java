package ar.unrn.tp.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_descuento")
public interface Descuento {

    boolean estaActiva(LocalDate fecha);

    double calcularDescuento(List<Producto> productos, TarjetaCredito tarjetaCredito);

}
