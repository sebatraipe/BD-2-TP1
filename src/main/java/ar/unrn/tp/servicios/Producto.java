package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ProductoService;

import java.util.List;

public class Producto implements ProductoService {
    @Override
    public void crearProducto(String codigo, String descripcion, float precio, Long idCategoria) {

    }

    @Override
    public void modificarProducto(Long idProducto) {

    }

    @Override
    public List listarProductos() {
        return null;
    }
}
