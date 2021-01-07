package es.candela.appFactura;

import java.time.*;
import java.time.format.*;

public class Factura {

    public static void imprimirFactura(Cliente cliente) {
        System.out.println("\nLicorería");
        System.out.println(generarNumFactura());
        System.out.println(generarFechaFactura());
        System.out.println(cliente.toString());
        System.out.println("Producto\tUnidad\tPrecio\tTotal");
        System.out.println(cliente.mostrarComprasCliente());
        float total = cliente.mostrarTotalCompra();
        double baseI =  total * 0.79;
        System.out.println("Base Imponible : " +baseI);
        System.out.println("IVA 21%        : " +(total-baseI));
        System.out.println("Total Fatura   : " +total);

    }

    public static String generarFechaFactura() {
        LocalDate ahora = LocalDate.now();
        String numFact = "Fecha Factura  :" +ahora;
        return numFact;
    }

    public static String generarNumFactura() {
        LocalDate ahora = LocalDate.now();
        String numFact = "Numero Factura :" +generarCodFactura()+"/"+ahora.getYear();
        return numFact;
    }


    public static String generarCodFactura(){
        String palabra = ""; 
        int caracteres = 5; 
        for (int i=0; i<caracteres; i++){ 
            int codigoAscii = (int)Math.floor(Math.random()*(122 - 97)+97); 
            palabra = palabra + (char)codigoAscii; 
            palabra = palabra.toUpperCase();
        } 
        return palabra; 
        } 
    
}
