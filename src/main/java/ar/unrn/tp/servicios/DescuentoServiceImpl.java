package ar.unrn.tp.servicios;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.modelo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class DescuentoServiceImpl implements DescuentoService {

    private final EntityManagerFactory entityManagerFactory;

    public DescuentoServiceImpl(String myPersistenceUnit) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(myPersistenceUnit);
    }

    @Override
    public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta,
                                         double porcentaje) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            TarjetaCredito tarjetaCredito = entityManager.createQuery(
                    "select t from TarjetaCredito t where t.descripcion = :marcaTarjeta", TarjetaCredito.class)
                    .setParameter("marcaTarjeta", marcaTarjeta)
                    .getSingleResult();

            Descuento descuentoSobreTotal = new DescuentoCompra(fechaDesde, fechaHasta, porcentaje, tarjetaCredito);
            entityManager.persist(descuentoSobreTotal);

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
    public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, double porcentaje) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Marca marca = entityManager.createQuery("select m from Marca m where m.marca = :marcaProducto", Marca.class)
                    .setParameter("marcaProducto", marcaProducto)
                    .getSingleResult();

            Descuento descuentoSobreTotal = new DescuentoProducto(fechaDesde, fechaHasta, porcentaje, marca);

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
}
