package ar.unrn.tp.modelo;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Marca {

    @Id
    @GeneratedValue
    private Long id;
    @Unique
    private String marca;

    public Marca(String marca) {
        this.marca = marca;
    }

    public Marca() {

    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private String getMarca() {
        return marca;
    }

    private void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                '}';
    }

    public boolean verificarMarca(String marca) {
        return this.marca.equals(marca);
    }
}
