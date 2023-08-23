package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.function.Predicate;

@Entity
public class TarjetaCredito {

    @Id
    private Long id;
    private TarjetaValidacion tarjetaValidacion;
    private String numero;
    private String descripcion;

    public TarjetaCredito(TarjetaValidacion tarjetaValidacion, String numero, String descripcion) {
        this.tarjetaValidacion = tarjetaValidacion;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public TarjetaCredito(String numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }

    protected TarjetaCredito() {
    }

    public boolean validarTarjeta() {
        return this.tarjetaValidacion.validarTarjeta();
    }

    public boolean verificarTarjeta(TarjetaCredito tarjetaCredito) {
        return this.descripcion.equals(tarjetaCredito.descripcion);
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private TarjetaValidacion getTarjetaValidacion() {
        return tarjetaValidacion;
    }

    private void setTarjetaValidacion(TarjetaValidacion tarjetaValidacion) {
        this.tarjetaValidacion = tarjetaValidacion;
    }

    private String getNumero() {
        return numero;
    }

    private void setNumero(String numero) {
        this.numero = numero;
    }

    private String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
