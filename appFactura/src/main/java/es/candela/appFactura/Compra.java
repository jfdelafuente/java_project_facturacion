package es.candela.appFactura;

import java.time.*;

public class Compra {
    LocalDate fecha;
    String producto;
    int cantidad;
    float precio;

    Compra(int año, int mes, int dia) {
        fecha = LocalDate.of(año, mes, dia);
    }

    Compra(String producto, int cantidad, float precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public float getPrecio() {
        return precio;
    }
    public String getProducto() {
        return producto;
    }

    public float getTotal() {
        return cantidad*precio;
    }

    public String mostrarCompra() {
        String salida = producto+"\t\t"+cantidad+"\t"+precio+"\t"+precio*cantidad;
        return salida;
    }
    
    public String toString() {
        return "\tInformacion de Compra:\n"
                + "Producto: " + producto + "\n"
                + "Cantidad: " + cantidad + "\n"
                + "Precio: " + precio + "\n"
                + "Total: " + cantidad * precio + "\n";
    }
}
