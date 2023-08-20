package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Marca {

    @Id
    private Long id;
    private String marca;

    public Marca(Long id, String marca) {
        this.id = id;
        this.marca = marca;
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
}
