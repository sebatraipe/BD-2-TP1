package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.TarjetaCredito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    private final EntityManagerFactory entityManagerFactory;

    public ClienteServiceImpl(String myPersistenceUnit) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(myPersistenceUnit);
    }

    @Override
    public void crearCliente(String nombre, String apellido, String dni, String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Cliente cliente = new Cliente(nombre, apellido, dni, email);
            entityManager.persist(cliente);

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
    public void modificarCliente(Long idCliente, String nombre, String apellido) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Cliente cliente = entityManager.find(Cliente.class, idCliente);
            if (cliente == null) {
                throw new RuntimeException("Cliente no encontrado...");
            }
            cliente.modificarDatos(nombre, apellido);

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
    public void agregarTarjeta(Long idCliente, String numero, String marca) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Cliente cliente = entityManager.find(Cliente.class, idCliente);

            if (cliente == null) {
                throw new RuntimeException("Cliente no encontrado...");
            }

            TarjetaCredito tarjetaCredito = new TarjetaCredito(numero, marca, true);
            System.out.println(tarjetaCredito);

            cliente.addTarjeta(tarjetaCredito);
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
    public List listarTarjetas(Long idCliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Cliente cliente = entityManager.find(Cliente.class, idCliente);
            if (cliente == null) {
                throw new RuntimeException("Cliente no encontrado...");
            }
            return cliente.tarjetas();
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
