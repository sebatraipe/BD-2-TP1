package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.modelo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    private final EntityManagerFactory entityManagerFactory;

    public ProductoServiceImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void crearProducto(String codigo, String descripcion, double precio, Long idCategoria, Long idMarca) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Categoria categoria = entityManager.find(Categoria.class, idCategoria);
            if (categoria == null)
                throw new RuntimeException("Categoria no encontrada...");

            Marca marca = entityManager.find(Marca.class, idMarca);
            if (marca == null)
                throw new RuntimeException("Marca no encontrada...");
            Producto producto = new Producto(codigo, descripcion, precio, categoria, marca);
            entityManager.persist(producto);

            entityTransaction.commit();
            entityManager.clear();
        } catch (Exception e) {
            entityTransaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
            if (this.entityManagerFactory != null)
                this.entityManagerFactory.close();
        }
    }

    @Override
    public void modificarProducto(Long idProducto, String descripcion, double precio, Long idCategoria) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Producto producto = entityManager.find(Producto.class, idProducto);
            if (producto == null)
                throw new RuntimeException("Producto no encontrado...");

            Categoria categoria = entityManager.find(Categoria.class, idCategoria);
            if (categoria == null)
                throw new RuntimeException("Categoria no encontrada...");

            producto.modificarDatos(descripcion, precio, categoria);

            entityTransaction.commit();
            entityManager.clear();

        } catch (Exception e) {
            entityTransaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
            if (this.entityManagerFactory != null)
                this.entityManagerFactory.close();
        }
    }

    @Override
    public List listarProductos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            List<Producto> productos = entityManager.createQuery(
                    "select p from Producto p", Producto.class).getResultList();

            if (productos == null) {
                //Ver Optional
                throw new RuntimeException("No hay productos...");
            }
            return productos;

        } catch (Exception e) {
            entityTransaction.rollback();
            throw new RuntimeException(e);
        } finally {
            entityTransaction.commit();
            entityManager.clear();
            if (entityManager != null && entityManager.isOpen())
                entityManager.close();
            if (this.entityManagerFactory != null)
                this.entityManagerFactory.close();
        }
    }
}
