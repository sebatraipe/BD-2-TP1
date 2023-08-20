package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ClienteService;

import java.util.List;

public class Cliente implements ClienteService {
    @Override
    public void crearCliente(String nombre, String apellido, String dni, String email) {

    }

    @Override
    public void modificarCliente(Long idCliente, String nombre, String apellido) {

    }

    @Override
    public void agregarTarjeta(Long idCliente, String numero, String marca) {

    }

    @Override
    public List listarTarjetas(Long idCliente) {
        return null;
    }
}
