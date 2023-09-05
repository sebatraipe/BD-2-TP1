package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DescuentoProducto implements Descuento {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double descuento;
    @OneToOne
    private Marca marca;

    public DescuentoProducto(LocalDate fechaInicio, LocalDate fechaFin, double descuento, Marca marca) {

        if (fechaInicio.isAfter(fechaFin) || fechaFin.isEqual(fechaInicio)) {
            throw new IllegalArgumentException("Las fechas no son correctas...");
        }

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.marca = marca;
    }

    protected DescuentoProducto() {
    }

    @Override
    public boolean estaActiva(LocalDate fecha) {
        return fecha.isAfter(fechaInicio) && fecha.isBefore(fechaFin);
    }

    @Override
    public double calcularDescuento(List<Producto> productos, TarjetaCredito tarjetaCredito) {

        return productos.stream()
                .filter(p -> p.verificarMarca(this.marca))
                .mapToDouble(p -> p.descuento(this.descuento))
                .sum();
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    private LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    private double getDescuento() {
        return descuento;
    }

    private void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    private Marca getMarca() {
        return marca;
    }

    private void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "DescuentoProducto{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", descuento=" + descuento +
                ", marca=" + marca +
                '}';
    }

    public boolean suDescuentoEs(double descuento) {
        return descuento == descuento;
    }
}
