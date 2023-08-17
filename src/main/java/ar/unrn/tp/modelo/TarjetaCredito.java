package ar.unrn.tp.modelo;

import java.util.function.Predicate;

public class TarjetaCredito {

    private TarjetaValidacion tarjetaValidacion;
    private String numero;
    private String descripcion;

    public TarjetaCredito(TarjetaValidacion tarjetaValidacion, String numero, String descripcion) {
        this.tarjetaValidacion = tarjetaValidacion;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public boolean validarTarjeta() {
        return this.tarjetaValidacion.validarTarjeta();
    }

    public boolean verificarTarjeta(TarjetaCredito tarjetaCredito) {
        return this.descripcion.equals(tarjetaCredito.descripcion);
    }
}
