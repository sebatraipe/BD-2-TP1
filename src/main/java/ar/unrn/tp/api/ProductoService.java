package ar.unrn.tp.api;

import java.util.List;

public interface ProductoService {

    //Validar que sea una categoria existente y que codigo no se repita
    void crearProducto(String codigo, String descripcion, float precio, Long idCategoria);


    //Validar que sea un producto existente
    void modificarProducto(Long idProducto);

    //Devuelve todos los productos
    List listarProductos();
}
