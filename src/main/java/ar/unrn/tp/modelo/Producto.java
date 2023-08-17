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

    public double precio() {
        return this.precio;
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

    public boolean verificarMarca(Marca marca) {
        return this.marca.equals(marca);
    }

    public double descuento(double descuento) {
        return this.precio * descuento;
    }
}
