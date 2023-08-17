package ar.unrn.tp.modelo;

public class Marca {

    private String marca;

    public Marca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "marca='" + marca + '\'' +
                '}';
    }
}
