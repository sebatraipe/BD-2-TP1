package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private TarjetaCredito tarjetaCredito;

    public Cliente(String nombre, String apellido, String dni, String email, TarjetaCredito tarjetaCredito) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.tarjetaCredito = tarjetaCredito;
    }
    public boolean validarTarjeta() {
        return this.tarjetaCredito.validarTarjeta();
    }

    public TarjetaCredito tarjeta() {
        return this.tarjetaCredito;
    }
}
