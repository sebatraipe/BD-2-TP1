package ar.unrn.tp.modelo;

import javax.jdo.annotations.Unique;
import javax.persistence.*;
import java.util.function.Predicate;

@Entity
public class TarjetaCredito {

    @Id
    @GeneratedValue
    private Long id;
    @Unique
    private String numero;
    private String descripcion;
    private boolean activa;

    public TarjetaCredito(String numero, String descripcion, boolean activa) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.activa = activa;
    }

    protected TarjetaCredito() {
    }

    public boolean validarTarjeta() {
        return this.activa;
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

    private boolean getActiva() {
        return activa;
    }

    private void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean verificarNumero(String numero) {
       return this.numero.equals(numero);
    }

    @Override
    public String toString() {
        return "TarjetaCredito{" +
                "numero='" + numero + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public boolean verificar(String numeroTarjeta, String descripcionTarjeta) {
        return this.numero.equals(numeroTarjeta) && this.descripcion.equals(descripcionTarjeta);
    }
}
