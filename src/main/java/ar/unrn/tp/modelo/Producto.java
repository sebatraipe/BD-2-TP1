package ar.unrn.tp.modelo;

public class Producto {

    private String codigo;
    private String descripcion;
    private double precio;
    private Categoria categoria;
    private Marca marca;

    public Producto(String codigo, String descripcion, double precio, Categoria categoria, Marca marca) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
    }
}
