package es.candela.appFactura;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;

public class Cliente {
    String dni;
    String nombre;
    String direccion;
    String codigo_postal;
    String provincia;
    ArrayList<Compra> compras;

    public Cliente() {
        dni = "";
        nombre = "";
        direccion = "";
        codigo_postal = "";
        provincia = "";
        compras = new ArrayList<Compra>();

    }

    public Cliente(String n, String d, String dir, String codigo, String p) {
        dni = d;
        nombre = n;
        direccion = dir;
        codigo_postal = codigo;
        provincia = p;
        compras = new ArrayList<Compra>();
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public String mostrarComprasCliente() {
        String salida = "";
        if (compras.size() > 0) {
            Iterator<Compra> itrClientes = compras.iterator();
            while(itrClientes.hasNext()){
                Compra compra = itrClientes.next();
                salida +=compra.mostrarCompra()+"\n";
            }
        }
        return salida;
    }

    public void mostrarCliente() {
        System.out.println(nombre+"\t"+dni+"\t"+direccion+"\t\t"+codigo_postal+"\t\t"+provincia);
    }

    public String toString() {
        return "\nInformacion de Facturación del Cliente:\n"
                + "   Nombre: " + nombre + "\n"
                + "   DNI: " + dni + "\n"
                + "   Direccion: " + direccion + "\n"
                + "   Codigo Postal: " + codigo_postal + "\n"
                + "   Provincia: " + provincia + "\n"
                + "\n";
    }

    public void añadirCompra(String producto, int cantidad, Float precio) {
        Compra compra = new Compra(producto, cantidad, precio);
        compras.add(compra);
        System.out.println(compra.toString());
    }

    public float mostrarTotalCompra() {
        float total = 0;
        if (compras.size() > 0) {
            Iterator<Compra> itrClientes = compras.iterator();
            while(itrClientes.hasNext()){
                Compra compra = itrClientes.next();
                total += compra.getTotal();
            }
        }
        return total;

    }

    public void loadFromFile(DataInputStream is) {
        try {
            nombre = is.readUTF();
            dni = is.readUTF();
            direccion = is.readUTF();
            codigo_postal = is.readUTF();
            provincia = is.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(DataOutputStream os) {
        try {
            os.writeUTF(nombre);
            os.writeUTF(dni);
            os.writeUTF(direccion);
            os.writeUTF(codigo_postal);
            os.writeUTF(provincia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
