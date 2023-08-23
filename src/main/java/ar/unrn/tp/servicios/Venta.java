package ar.unrn.tp.servicios;

import ar.unrn.tp.api.VentaService;

import java.util.List;

public class Venta implements VentaService {
    @Override
    public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {

    }

    @Override
    public double calcularMonto(List<Long> productos, Long idTarjeta) {
        return 0;
    }

    @Override
    public List ventas() {
        return null;
    }
}
