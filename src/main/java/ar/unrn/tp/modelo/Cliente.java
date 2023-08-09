package ar.unrn.tp.modelo;

import java.util.List;

public class Cliente {

    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private List<TarjetaCredito> tarjetaCreditos;

    public Cliente(String nombre, String apellido, String dni, String email, List<TarjetaCredito> tarjetaCreditos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.tarjetaCreditos = tarjetaCreditos;
    }
}
