package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Venta {

    private LocalDateTime fechaHora;
    private Cliente cliente;
    private List<Producto> productos;
    private double montoTotal;

    public Venta(LocalDateTime fechaHora, Cliente cliente, List<Producto> productos, double montoTotal) {
        this.fechaHora = fechaHora;
        this.cliente = cliente;
        this.productos = productos;
        this.montoTotal = montoTotal;
    }

//    public void realizarPago() {
//        cliente.validarTarjeta();
//        System.out.println("El pago se realizó con éxito...");
//    }

    public boolean verificarVentaPorFecha(LocalDateTime date) {
        return this.fechaHora.getDayOfMonth() == date.getDayOfMonth() &&
                this.fechaHora.getDayOfWeek() == date.getDayOfWeek() &&
                this.fechaHora.getDayOfYear() == date.getDayOfYear();
    }
}
