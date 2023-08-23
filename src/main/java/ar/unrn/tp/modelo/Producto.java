package ar.unrn.tp.modelo;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    @Unique
    private String codigo;
    private String descripcion;
    private double precio;
    private Categoria categoria;
    private Marca marca;

    public Producto(String codigo, String descripcion, double precio, Categoria categoria, Marca marca) {

        if (codigo == null || descripcion == null || precio <= 0 || categoria == null || marca == null) {
            throw new IllegalArgumentException("Todos los valores del producto deben ser válidos...");
        }

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
    }

    public double precio() {
        return this.precio;
    }

    public boolean verificarMarca(Marca marca) {
        return this.marca.equals(marca);
    }

    public double descuento(double descuento) {
        return this.precio * descuento;
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    private String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private double getPrecio() {
        return precio;
    }

    private void setPrecio(double precio) {
        this.precio = precio;
    }

    private Categoria getCategoria() {
        return categoria;
    }

    private void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    private Marca getMarca() {
        return marca;
    }

    private void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", marca=" + marca +
                '}';
    }

    public void modificarDatos(String descripcion, double precio, Categoria categoria) {
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
        this.setCategoria(categoria);
    }
}
