package ar.unrn.tp.modelo;

public class TarjetaServicio implements TarjetaValidacion {

    @Override
    public boolean validarTarjeta() {
        // Implementación ficticia de la validación de tarjeta
        System.out.println("Validando tarjeta de credito... Éxito!");
        return true;
    }
}
