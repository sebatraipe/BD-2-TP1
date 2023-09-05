package ar.unrn.tp.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Venta {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime fechaHora;
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Producto> productos;
    private double montoTotal;

    public Venta(LocalDateTime fechaHora, Cliente cliente, List<Producto> productos, double montoTotal) {
        this.fechaHora = fechaHora;
        this.cliente = cliente;
        this.productos = productos;
        this.montoTotal = montoTotal;
    }

    protected Venta() {
    }

    public boolean verificarVentaPorFecha(LocalDateTime date) {
        return this.fechaHora.getDayOfMonth() == date.getDayOfMonth() &&
                this.fechaHora.getDayOfWeek() == date.getDayOfWeek() &&
                this.fechaHora.getDayOfYear() == date.getDayOfYear();
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private LocalDateTime getFechaHora() {
        return fechaHora;
    }

    private void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    private Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private List<Producto> getProductos() {
        return productos;
    }

    private void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    private double getMontoTotal() {
        return montoTotal;
    }

    private void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", cliente=" + cliente +
                ", productos=" + productos +
                ", montoTotal=" + montoTotal +
                '}';
    }
}
