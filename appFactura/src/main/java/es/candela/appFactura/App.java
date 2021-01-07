package es.candela.appFactura;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public final class App {

    ArrayList<Cliente> clientes = null;

    App() {
        clientes = new ArrayList<Cliente>();
    }

    public static void main(String[] args) {
        App app = new App();
        app.menu();
    }

    public void menu() {
        int opcion = -1;
        boolean salir = false;
        while (!salir) {
            opcion= mostrarMenu();
            switch (opcion) {
                case 0:
                    salir = true;
                    System.out.println("\nSalimos de la aplicación de Gestión de Facturas. Gracias.\n");
                    break;
                case 1:
                    System.out.println("\nLista Clientes   ------------");
                    if (clientes.size() > 0)
                        mostrarListaClienes(clientes);
                    else
                        System.out.println("\nInfo: No existen clientes. Primero debe añadir Clientes a la aplicación");
                    break;
                case 2:
                    System.out.println("\nAñadir Cliente   ------------");
                    Cliente cliente = añadirCliente();
                    clientes.add(cliente);
                    break;
                case 3:
                    System.out.println("\nAñadir Compras de un Clientes   ------------");
                    if (clientes.size() > 0)
                        añadirComprasCliente(clientes);
                    else
                        System.out.println("\nInfo: No existen clientes. Primero debe añadir Clientes a la aplicación");
                    break;
                case 4:
                    System.out.println("\nEmitir Facturas de un Cliente   ------------");
                    if (clientes.size() > 0)
                        emitirFacturaCliente(clientes);
                    else
                        System.out.println("\nInfo: No existen clientes. Primero debe añadir Clientes a la aplicación");
                    break;
                case 5:
                    System.out.println("\nBorrar Cliente   ------------");
                    if (clientes.size() > 0)
                        borrarCliente(clientes);
                    else
                        System.out.println("\nInfo: No existen clientes. Primero debe añadir Clientes a la aplicación");
                    break;
                case 6:
                    System.out.println("\nCargar Clientes desde un fichero");
                    cargarClientes(clientes);
                    break;
                case 7:
                    System.out.println("\nVolcar Clientes a fichero");
                    salvarClientes(clientes);
                    break;
                default:
                    System.out.println("\nError: opción no válida. Debe introducir un número entre le 0 y el 5. Gracias.");
            }

        }
    }

    public static int mostrarMenu() {
        Scanner sn = new Scanner(System.in);
        int opcion = -1;
        System.out.println("\n");
        System.out.println("\t####  Gestión Facturas Clientes  #########################\n");
        System.out.println("\t1. Mostrar Lista Clientes");
        System.out.println("\t2. Añadir Clientes");
        System.out.println("\t3. Añadir Compras de clientes");
        System.out.println("\t4. Emitir Factura Cliente");
        System.out.println("\t5. Borrar Cliente");
        System.out.println("\t0. Salir\n");
        try {       
            System.out.print(">>> Elija una de las opciones > ");
            opcion = sn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error : debes insertar un número.");
            sn.next();
        }
        return opcion; 
    }


    public static void mostrarListaClienes(ArrayList<Cliente> clientes) {
        Iterator<Cliente> itrClientes = clientes.iterator();
        System.out.println("Nombre\tDNI\t\tDirección\t\tCP\t\tProvincia");
        while(itrClientes.hasNext()){
            Cliente cliente = itrClientes.next();
            //System.out.println("\t"+cliente.toString());
            cliente.mostrarCliente();
        }
    }
    public static Cliente añadirCliente() {    
        Scanner scan = new Scanner( System.in );        
        // System.out.println("Añadir Cliente\n");
        System.out.print(">>> Introduzca el nobre del cliente : ");
        String nombre = scan.nextLine();

        System.out.print(">>> Introduzca el DNI del cliente : ");
        String dni = scan.nextLine();

        System.out.print(">>> Introduzca la direccion de facturación : ");
        String dir = scan.nextLine();

        System.out.print(">>> Introduzca el código postal : ");
        String cp = scan.nextLine();

        System.out.print(">>> Introduzca la provincia : ");
        String provincia = scan.nextLine();

        Cliente cliente = new Cliente(nombre, dni, dir, cp, provincia);
        System.out.println("Info: Cliente añadido: "+cliente.getNombre());
        return cliente;
    }

    public static void añadirComprasCliente(ArrayList<Cliente> clientes) {
        Scanner scan = new Scanner( System.in ); 
        boolean encontrado = false;       
        // System.out.println("Añadir compras del  Cliente");
        System.out.print(">>> Introduzca el nombre del cliente : ");
        String nombre = scan.nextLine();

        Iterator<Cliente> itrClientes = clientes.iterator();
        int i = 0;
        while(itrClientes.hasNext()){
            Cliente cliente = itrClientes.next();
            // System.out.println("Borrando a ... " +cliente.getNombre()+ " comparado con "+nombre);
            if (nombre.equals(cliente.getNombre())) {
                System.out.println("Info: El cliente " +nombre+ " ha sido encontrado");
                //System.out.println(cliente.toString());;
                encontrado = true;
                break;  
            } 
            i++;
        }
        if (encontrado) {
            System.out.print(">>> Introduzca el nombre del producto comprado por "+clientes.get(i).getNombre()+" : ");
            String producto = scan.nextLine();

            System.out.print(">>> Introduzca las cantidades compradas del "+producto+" : ");
            int unidades = scan.nextInt();

            System.out.print(">>> Introduzca el precio del "+producto+ " comprado : ");
            float precio = scan.nextFloat();
            clientes.get(i).añadirCompra(producto, unidades, precio);
        } else
            System.out.println("Info: El cliente " +nombre+ " NO ha sido encontrado");

    }

    public static void borrarCliente(ArrayList<Cliente> clientes) {
        Scanner scan = new Scanner( System.in );
        boolean encontrado = false;          
        // System.out.println("Borrar Cliente\n");
        System.out.print(">>> Introduzca el nombre del cliente a borrar : ");
        String nombre = scan.nextLine();

        Iterator<Cliente> itrClientes = clientes.iterator();
        while(itrClientes.hasNext()){
            Cliente cliente = itrClientes.next();
            if (nombre.equals(cliente.getNombre())) {
                encontrado = true;
                System.out.println("Info: El cliente " +nombre+ " ha sido borrado");
                itrClientes.remove();
            } 
        }
        if (!encontrado) System.out.println("Info: El cliente " +nombre+ " NO ha sido encontrado");
    }

    public static void emitirFacturaCliente(ArrayList<Cliente> clientes) {
        Scanner scan = new Scanner( System.in ); 
        boolean encontrado = false;  
        // System.out.println("Emitir Factura Cliente -----------------");
        System.out.print(">>> Introduzca el nombre del cliente : ");
        String nombre = scan.nextLine();

        Iterator<Cliente> itrClientes = clientes.iterator();
        while(itrClientes.hasNext()){
            Cliente cliente = itrClientes.next();
            if (nombre.equals(cliente.getNombre())) {
                encontrado = true;
                Factura.imprimirFactura(cliente);
                break;  
            } 
        }
        if (!encontrado) System.out.println("Info: El cliente " +nombre+ " NO ha sido encontrado");
    }

    public static void cargarClientes(ArrayList<Cliente> clientes) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("clientes.txt"));
            while (dis.available() > 0 ){
                Cliente cliente = new Cliente();
                cliente.loadFromFile(dis);
                clientes.add(cliente);
            }
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void salvarClientes(ArrayList<Cliente> clientes) {
        
        Iterator<Cliente> itrClientes = clientes.iterator();
        while(itrClientes.hasNext()){
            Cliente cliente = itrClientes.next();
            try {
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("clientes.txt", true));
                cliente.saveToFile(dos);
                dos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}