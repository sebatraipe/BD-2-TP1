package ar.unrn.tp.servicios;

import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class VentaServiceImpl implements VentaService {

    private final EntityManagerFactory entityManagerFactory;

    public VentaServiceImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            TarjetaCredito tarjetaCredito = entityManager.find(TarjetaCredito.class, idTarjeta);

            Cliente cliente = entityManager.find(Cliente.class, idCliente);

            List<Producto> productosList = entityManager.createQuery(
                            "select p from Producto p where p.id in :productos", Producto.class)
                    .setParameter("productos", productos)
                    .getResultList();

            List<Descuento> descuentos = entityManager.createQuery(
                            "select d from Descuento d", Descuento.class)
                    .getResultList();

            CarritoCompras carritoCompras = new CarritoCompras(productosList, descuentos, cliente);

            Venta venta = carritoCompras.realizarPago(tarjetaCredito);

            entityManager.persist(venta);

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
    public double calcularMonto(List<Long> productos, Long idTarjeta, Long idCliente) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            TarjetaCredito tarjetaCredito = entityManager.find(TarjetaCredito.class, idTarjeta);

            if (tarjetaCredito == null) {
                throw new RuntimeException("La tarjeta no existe...");
            }

            List<Descuento> descuentos = entityManager.createQuery("select d from Descuento d", Descuento.class)
                    .getResultList();

            List<Producto> productosList = entityManager.createQuery(
                            "select p from Producto p where p.id in :productos", Producto.class)
                    .setParameter("productos", productos)
                    .getResultList();

            Cliente cliente = entityManager.find(Cliente.class, idCliente);

            CarritoCompras carritoCompras = new CarritoCompras(productosList, descuentos, cliente);

            entityTransaction.commit();
            entityManager.clear();

            return carritoCompras.montoTotal(tarjetaCredito);
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
    public List ventas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            List<Venta> ventas = entityManager.createQuery(
                    "select v from Venta v", Venta.class).getResultList();

            if (ventas == null)
                throw new RuntimeException("No hay ventas...");
            return ventas;

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
