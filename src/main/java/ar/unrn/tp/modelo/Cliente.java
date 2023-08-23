package ar.unrn.tp.modelo;

import javax.jdo.annotations.Index;
import javax.jdo.annotations.Unique;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String apellido;
    @Unique
    private String dni;
    private String email;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private List<TarjetaCredito> tarjetasCredito;

    public Cliente(String nombre, String apellido, String dni, String email, List<TarjetaCredito> tarjetaCredito) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.tarjetasCredito = tarjetaCredito;
    }

    public Cliente(String nombre, String apellido, String dni, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }

    protected Cliente() {
    }

    public void agregarTarjeta(TarjetaCredito tarjetaCredito) {
        this.tarjetasCredito.add(tarjetaCredito);
    }

    private String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String getApellido() {
        return apellido;
    }

    private void setApellido(String apellido) {
        this.apellido = apellido;
    }

    private String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        this.dni = dni;
    }

    private String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private List<TarjetaCredito> getTarjetasCredito() {
        return tarjetasCredito;
    }

    private void setTarjetasCredito(List<TarjetaCredito> tarjetasCredito) {
        this.tarjetasCredito = tarjetasCredito;
    }

    public boolean validarTarjeta(TarjetaCredito tarjetaCredito) {
        return tarjetaCredito.validarTarjeta();
    }

    public void modificarDatos(String nombre, String apellido) {
        this.setNombre(nombre);
        this.setApellido(apellido);
    }

    public List tarjetas() {
        return this.getTarjetasCredito();
    }
}
