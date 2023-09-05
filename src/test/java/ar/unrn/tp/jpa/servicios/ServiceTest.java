package ar.unrn.tp.jpa.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.modelo.*;
import ar.unrn.tp.servicios.ClienteServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

public class ServiceTest {

    private EntityManagerFactory emf;

    @BeforeEach
    public void setup() {
        emf = Persistence.createEntityManagerFactory("objectdb:myDbTestFile.tmp;drop");
    }

    //Test para los Clientes
    @Test
    public void puedoPersistirUnClienteConTarjetasDeCredito() {
        inTransactionExecute(
                (em) -> {
                    Cliente clienteSeba = new Cliente("Sebastian", "Traipe", "40706973",
                            "shtraipe@unrn.edu.ar");

                    clienteSeba.addTarjeta(new TarjetaCredito("12345678", "Visa", true));

                    em.persist(clienteSeba);
                }
        );

        inTransactionExecute(
                (em) -> {
                    Cliente clienteSeba = em.find(Cliente.class, 1L);
                    assertTrue(clienteSeba.seLlama("Sebastian"));
                    assertTrue(clienteSeba.suDniEs("40706973"));
                    assertTrue(clienteSeba.suTarjetaEs("12345678"));
                }
        );
    }

    @Test
    public void puedoModificarUnCliente() {
        inTransactionExecute(
                (em) -> {
                    Cliente clienteSeba = new Cliente("Sebastian", "Traipe", "40706973",
                            "shtraipe@unrn.edu.ar");
                    em.persist(clienteSeba);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Cliente cliente = em.find(Cliente.class, 1L);
                    cliente.modificarDatos("Seba", "Tevez");
                    assertTrue(cliente.seLlama("Seba"));
                    assertTrue(cliente.suApellidoEs("Tevez"));
                });
    }

    @Test
    public void puedoAgregarUnaTarjetaAlCliente() {
        inTransactionExecute(
                (em) -> {
                    Cliente clienteSeba = new Cliente("Sebastian", "Traipe", "40706973",
                            "shtraipe@unrn.edu.ar");
                    em.persist(clienteSeba);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Cliente clienteSeba = em.find(Cliente.class, 1L);
                    clienteSeba.addTarjeta(new TarjetaCredito("12345678", "Visa", true));
                    assertTrue(clienteSeba.tieneTarjeta("12345678", "Visa"));
                }
        );
    }

    @Test
    public void puedoListarLosClientes() {
        inTransactionExecute(
                (em) -> {
                    Cliente clienteSeba = new Cliente("Sebastian", "Traipe", "40706973",
                            "shtraipe@unrn.edu.ar");

                    Cliente clienteMessi = new Cliente("Lionel", "Messi", "35196783",
                            "lmessi@unrn.edu.ar");

                    em.persist(clienteSeba);
                    em.persist(clienteMessi);
                }
        );
        inTransactionExecute(
                (em) -> {
                    List<Cliente> clienteList = em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                            .getResultList();

                    assertEquals(2, clienteList.size());
                    assertTrue(clienteList.stream()
                            .anyMatch(cliente -> cliente.seLlama("Sebastian")));
                    assertTrue(clienteList.stream()
                            .anyMatch(cliente -> cliente.seLlama("Lionel")));
                }
        );
    }

    //Test para los Productos
    @Test
    public void puedoPersistirUnProductoConCategoriaYMarca() {
        inTransactionExecute(
                (em) -> {
                    Categoria categoria = new Categoria("Calzado");
                    Marca marca = new Marca("Nike");
                    em.persist(categoria);
                    em.persist(marca);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Categoria categoria = em.find(Categoria.class, 1L);
                    Marca marca = em.createQuery("select m from Marca m where m.marca = 'Nike'", Marca.class)
                            .getSingleResult();
                    Producto producto = new Producto("1234", "Zapatillas", 45.000,
                            categoria, marca);

                    em.persist(producto);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Producto producto = em.createQuery("select p from Producto p where p.codigo = '1234'", Producto.class)
                            .getSingleResult();

                    assertTrue(producto.suCodigoEs("1234"));
                    assertTrue(producto.suDescripcionEs("Zapatillas"));
                    assertTrue(producto.suCategoriaEs("Calzado"));
                    assertTrue(producto.suMarcaEs("Nike"));
                }
        );
    }

    @Test
    public void puedoModicarUnProductoPersistido() {
        inTransactionExecute(
                (em) -> {
                    Categoria categoriaCalzado = new Categoria("Calzado");
                    Categoria categoriaIndumentaria = new Categoria("Indumentaria");
                    Marca marca = new Marca("Nike");
                    em.persist(categoriaCalzado);
                    em.persist(categoriaIndumentaria);
                    em.persist(marca);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Categoria categoria = em.find(Categoria.class, 1L);
                    Marca marca = em.createQuery("select m from Marca m where m.marca = 'Nike'", Marca.class)
                            .getSingleResult();
                    Producto producto = new Producto("1234", "Zapatillas", 45.000,
                            categoria, marca);

                    em.persist(producto);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Categoria categoria = em.find(Categoria.class, 2L);
                    Producto producto = em.createQuery("select p from Producto p where p.codigo = '1234'", Producto.class)
                            .getSingleResult();

                    producto.modificarDatos("Remera", 30.00, categoria);

                    assertTrue(producto.suCodigoEs("1234"));
                    assertTrue(producto.suDescripcionEs("Remera"));
                    assertTrue(producto.suCategoriaEs("Indumentaria"));
                    assertTrue(producto.suMarcaEs("Nike"));
                }
        );
    }

    @Test
    public void puedoListarLosPoductosPersistidos() {
        inTransactionExecute(
                (em) -> {
                    em.persist(new Categoria("Calzado"));
                    em.persist(new Categoria("Indumentaria"));
                    em.persist(new Marca("Nike"));
                    em.persist(new Marca("Acme"));
                }
        );
        inTransactionExecute(
                (em) -> {
                    Categoria categoriaCalzado = em.find(Categoria.class, 1L);
                    Categoria categoriaIndumentaria = em.find(Categoria.class, 2L);
                    Marca marcaNike = em.createQuery("select m from Marca m where m.marca = 'Nike'", Marca.class)
                            .getSingleResult();
                    Marca marcaAcme = em.createQuery("select m from Marca m where m.marca = 'Acme'", Marca.class)
                            .getSingleResult();

                    Producto productoZapatilla = new Producto("1234", "Zapatillas", 45.000,
                            categoriaCalzado, marcaNike);
                    Producto productoRemera = new Producto("4321", "Remera", 25.000,
                            categoriaIndumentaria, marcaAcme);

                    em.persist(productoZapatilla);
                    em.persist(productoRemera);
                }
        );
        inTransactionExecute(
                (em) -> {
                    List<Producto> productos = em.createQuery(
                            "select p from Producto p", Producto.class).getResultList();

                    assertTrue(productos.stream().anyMatch(producto -> producto.suCodigoEs("1234")));
                    assertTrue(productos.stream().anyMatch(producto -> producto.suDescripcionEs("Zapatillas")));
                    assertTrue(productos.stream().anyMatch(producto -> producto.suCategoriaEs("Calzado")));
                    assertTrue(productos.stream().anyMatch(producto -> producto.suMarcaEs("Nike")));

                    assertTrue(productos.stream().anyMatch(producto -> producto.suCodigoEs("4321")));
                    assertTrue(productos.stream().anyMatch(producto -> producto.suDescripcionEs("Remera")));
                    assertTrue(productos.stream().anyMatch(producto -> producto.suCategoriaEs("Indumentaria")));
                    assertTrue(productos.stream().anyMatch(producto -> producto.suMarcaEs("Acme")));

                }
        );
    }

    //Test para los descuentos
    @Test
    public void puedoPersistirUnDescuentoSobreElTotal() {
        inTransactionExecute(
                (em -> {
                    TarjetaCredito tarjetaCredito = new TarjetaCredito("12345678", "Visa", true);
                    em.persist(tarjetaCredito);
                })
        );
        inTransactionExecute(
                (em) -> {
                    TarjetaCredito tarjetaCredito = em.find(TarjetaCredito.class, 1L);
                    DescuentoCompra descuento = new DescuentoCompra(LocalDate.now().minusDays(5),
                            LocalDate.now().plusDays(5), 0.08, tarjetaCredito);
                    em.persist(descuento);
                }
        );
        inTransactionExecute(
                (em) -> {
                    DescuentoCompra descuento = em.find(DescuentoCompra.class, 2L);

                    assertTrue(descuento.estaActiva(LocalDate.now()));
                    assertTrue(descuento.suDescuentoEs(0.08));
                }
        );
    }

    @Test
    public void puedoPersistirUnDescuentoSobreElProducto() {
        inTransactionExecute(
                (em) -> {
                    Marca marca = new Marca("Nike");
                    em.persist(marca);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Marca marca = em.find(Marca.class, 1L);
                    DescuentoProducto descuentoProducto = new DescuentoProducto(LocalDate.now().minusDays(5),
                            LocalDate.now().plusDays(5), 0.05, marca);

                    em.persist(descuentoProducto);
                }
        );
        inTransactionExecute(
                (em) -> {
                    DescuentoProducto descuentoProducto = em.find(DescuentoProducto.class, 2L);

                    assertTrue(descuentoProducto.estaActiva(LocalDate.now()));
                    assertTrue(descuentoProducto.suDescuentoEs(0.08));
                }
        );
    }

    @Test
    public void puedoRealizarUnaVentaYPersistirlo() {
        inTransactionExecute(
                (em) -> {
                    Marca marca = new Marca("Nike");
                    em.persist(marca);
                    Categoria categoria = new Categoria("Calzado");
                    em.persist(categoria);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Marca marca = em.find(Marca.class, 1L);
                    Categoria categoria = em.find(Categoria.class, 2L);

                    Producto producto1 = new Producto("1234", "Zapatilla", 45.00,
                            categoria, marca);
                    em.persist(producto1);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Marca marca = em.find(Marca.class, 1L);
                    Descuento descuentoProducto = new DescuentoProducto(LocalDate.now().minusDays(3),
                            LocalDate.now().plusDays(5), 0.05, marca);
                    em.persist(descuentoProducto);
                    TarjetaCredito tarjetaCredito = new TarjetaCredito("12345678", "Visa", true);
                    em.persist(tarjetaCredito);
                    Descuento descuentoCompra = new DescuentoCompra(LocalDate.now().minusDays(3),
                            LocalDate.now().plusDays(5), 0.08, tarjetaCredito);
                    em.persist(descuentoCompra);
                }
        );
        inTransactionExecute(
                (em) -> {
                    List<Descuento> descuentos = em.createQuery("select d from Descuento d", Descuento.class)
                            .getResultList();
                    Producto producto = em.find(Producto.class, 3L);
                    TarjetaCredito tarjetaCredito = em.find(TarjetaCredito.class, 5L);
                    Cliente cliente = new Cliente("Sebastian", "Traipe", "40706973",
                            "shtraipe@unrn.edu.ar");
                    em.persist(cliente);
                    CarritoCompras carritoCompras = new CarritoCompras(List.of(producto), descuentos, cliente);
                    Venta venta = carritoCompras.realizarPago(tarjetaCredito);
                    em.persist(venta);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Venta venta = em.find(Venta.class, 8L);
                    assertTrue(venta.verificarVentaPorFecha(LocalDateTime.now()));
                }
        );
    }

    @Test
    public void puedoListarLasVentasPersistidas() {
        inTransactionExecute(
                (em) -> {
                    Cliente cliente = new Cliente("Sebastian", "Traipe", "40706973",
                            "shtraipe@unrn.edu.ar");
                    em.persist(cliente);
                    Categoria categoria1 = new Categoria("Calzado");
                    em.persist(categoria1);
                    Categoria categoria2 = new Categoria("Indumentaria");
                    em.persist(categoria2);
                    Marca marca1 = new Marca("Nike");
                    em.persist(marca1);
                    Marca marca2 = new Marca("Adidas");
                    em.persist(marca2);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Categoria categoria = em.find(Categoria.class, 2L);
                    Marca marca = em.find(Marca.class, 4L);
                    Producto producto1 = new Producto("1234", "Zapatillas", 45.00,
                            categoria, marca);
                    Producto producto2 = new Producto("4321", "Zapatillas", 25.00,
                            categoria, marca);
                    em.persist(producto1);
                    em.persist(producto2);
                    TarjetaCredito tarjetaCredito = new TarjetaCredito("12345678", "Visa", true);
                    em.persist(tarjetaCredito);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Marca marca = em.find(Marca.class, 3L);
                    TarjetaCredito tarjetaCredito = em.find(TarjetaCredito.class, 8L);
                    Descuento descuentoProducto = new DescuentoProducto(LocalDate.now().minusDays(3),
                            LocalDate.now().plusDays(5), 0.05, marca);
                    em.persist(descuentoProducto);
                    Descuento descuentoCompra = new DescuentoCompra(LocalDate.now().minusDays(3),
                            LocalDate.now().plusDays(5), 0.08, tarjetaCredito);
                    em.persist(descuentoCompra);
                }
        );
        inTransactionExecute(
                (em) -> {
                    Cliente cliente = em.find(Cliente.class, 1L);
                    List<Descuento> descuentos = em.createQuery("select d from Descuento d", Descuento.class)
                            .getResultList();
                    List<Producto> productos = em.createQuery("select p from Producto p", Producto.class)
                            .getResultList();
                    CarritoCompras carritoCompras = new CarritoCompras(productos, descuentos, cliente);
                    TarjetaCredito tarjetaCredito = em.find(TarjetaCredito.class, 8L);
                    Venta venta = new Venta(LocalDateTime.now(), cliente, productos, carritoCompras.montoTotal(
                            new TarjetaCredito("00000000", "Visa", true)));
                    em.persist(venta);
                }
        );
        inTransactionExecute(
                (em) -> {
                    List<Venta> ventas = em.createQuery("select v from Venta v", Venta.class)
                            .getResultList();
                    assertTrue(ventas.stream().anyMatch(v -> v.verificarVentaPorFecha(LocalDateTime.now())));
                }
        );
    }

    public void inTransactionExecute(Consumer<EntityManager> bloqueDeCodigo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            bloqueDeCodigo.accept(em);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        emf.close();
    }
}
