package ar.unrn.tp.main;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.modelo.*;
import ar.unrn.tp.servicios.ClienteServiceImpl;

import javax.jdo.listener.ClearCallback;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClienteService clienteService = new ClienteServiceImpl("jpa-objectdb");
        clienteService.crearCliente("Lionel", "Messi", "20754763",
                "shtraipe@unrn.edu.ar");
    }
}
