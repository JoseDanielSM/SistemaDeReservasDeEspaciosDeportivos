import java.io.*;
import java.util.Scanner;

/**
 *@author Luis Fernando Gomez Esquivel - C4f464
 */
public class User {
    private String name;
    private String id;
    
    // Constructor que no tiene nada.
    public User() {
    }

    // Constructor que tiene parametros.
    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Metodo que se usa cargar los datos del usuario.
     */
    public void loadUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre por favor: ");
        this.name = scanner.nextLine();
        System.out.print("Ingrese su cédula por favor: ");
        this.id = scanner.nextLine();
        System.out.println("Los datos fueron ingresados correctamente.");
    }

    /**
     * Metodo para crear la reserva
     */
    public void makeReservation() {
        if (this.name == null || this.id == null) {
            System.out.println("No se han cargado los datos del usuario.");
            return;
        }

        String reservationDetails = "Reserva para: " + this.name + ", Cédula: " + this.id;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservas.txt", true))) {
            writer.write(reservationDetails);
            writer.newLine();
            System.out.println("Reserva hecha: " + reservationDetails);
        } catch (IOException e) {
            System.out.println("Error al realizar la reserva: " + e.getMessage());
        }
    }

    // Metodo para probar una clase
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        while (true) {
            System.out.println("<<<< Menu de Opciones >>>>");
            System.out.println("1. Ingresar datos de usuario");
            System.out.println("2. Hacer reserva");
            System.out.println("3. Salir");
            System.out.println("------------------------------------");

            System.out.print("Ingrese la opcion: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    user.loadUserData();
                    break;
                case 2:
                    user.makeReservation();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema....");
                    return;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
    }
}
