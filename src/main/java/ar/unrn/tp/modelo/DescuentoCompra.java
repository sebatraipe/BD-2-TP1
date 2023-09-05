package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DescuentoCompra implements Descuento {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double descuento;
    @OneToOne
    private TarjetaCredito tarjetaCredito;

    public DescuentoCompra(LocalDate fechaInicio, LocalDate fechaFin, double descuento, TarjetaCredito tarjetaCredito) {

        if (fechaInicio.isAfter(fechaFin) || fechaFin.isEqual(fechaInicio)) {
            throw new IllegalArgumentException("Las fechas no son correctas...");
        }

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.tarjetaCredito = tarjetaCredito;
    }

    public DescuentoCompra() {
    }

    @Override
    public boolean estaActiva(LocalDate fecha) {
        return fecha.isAfter(fechaInicio) && fecha.isBefore(fechaFin);
    }

    @Override
    public double calcularDescuento(List<Producto> productos, TarjetaCredito tarjetaCredito) {

        double montoTotal = 0;
        if (this.tarjetaCredito.verificarTarjeta(tarjetaCredito)) {
            montoTotal = productos.stream()
                    .mapToDouble(Producto::precio)
                    .sum();
        }
        return montoTotal * this.descuento;
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

    private TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    private void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    @Override
    public String toString() {
        return "DescuentoCompra{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", descuento=" + descuento +
                ", tarjetaCredito=" + tarjetaCredito +
                '}';
    }

    public boolean suDescuentoEs(double descuento) {
        return this.descuento == descuento;
    }
}
