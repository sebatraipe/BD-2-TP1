package ar.unrn.tp.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Venta {

    private LocalDateTime fechaHora;
    private Cliente cliente;
    private List<Producto> productos;

    public Venta(LocalDateTime fechaHora, Cliente cliente, List<Producto> productos) {
        this.fechaHora = fechaHora;
        this.cliente = cliente;
        this.productos = productos;
    }

    public void realizarPago() {
        cliente.validarTarjeta();
        System.out.println("El pago se realizó con éxito...");
    }
}
