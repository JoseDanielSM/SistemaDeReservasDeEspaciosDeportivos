import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Sports Reservation System
 * Hecho por José Daniel Segura Menjívar
 */
public class ReservationSystem {
    // Listas para almacenar usuarios, espacios deportivos y reservas
    private List<User> listUser = new ArrayList<>();
    private List<SportSpace> listSpace = new ArrayList<>();
    private List<Reservation> listOfReservation = new ArrayList<>();

    public void startSystem() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Mostrar el menú de opciones
            System.out.println("\n--- Sports Reservation System ---");
            System.out.println("1. Registrar Espacio Deportivo");
            System.out.println("2. Agregar Reserva");
            System.out.println("3. Confirmar Reserva");
            System.out.println("4. Cancelar Reserva");
            System.out.println("5. Ver Disponibilidad");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            // Ejecutar la opción seleccionada
            switch (option) {
                case 1:
                    registerSpace();
                    break;
                case 2:
                    addReservation();
                    break;
                case 3:
                    confirmReservation();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    seeAvailability();
                    break;
                case 6:
                    running = false;
                    System.out.println("Saliendo del sistema. Gracias por usar el Sports Reservation System!");
                    break;
                default:
                    System.out.println("Opcion invalida. Intentelo de nuevo.");
            }
        }
        scanner.close();
    }

    /**
     * Metodo para registrar un espacio deportivo
     */
    public void registerSpace() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del espacio: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el tipo de espacio (cancha, gimnasio): ");
        String type = scanner.nextLine();
        System.out.print("Ingrese la capacidad del espacio: ");
        int capacity = scanner.nextInt();

        SportSpace newSpace = new SportSpace(name, type, capacity);
        listSpace.add(newSpace);
        System.out.println("Espacio deportivo registrado exitosamente.");
    }

    /**
     * Método para agregar una reserva
     */
    public void addReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del espacio que desea reservar: ");
        String spaceName = scanner.nextLine();
        System.out.print("Ingrese la fecha de la reserva (DD-MM-AAAA): ");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput);

        // Buscar el espacio solicitado
        SportSpace space = null;
        for (SportSpace s : listSpace) {
            if (s.getName().equalsIgnoreCase(spaceName)) {
                space = s;
                break;
            }
        }

        if (space != null) {
            Reservation reservation = new Reservation(space, date);
            listOfReservation.add(reservation);
            System.out.println("Reserva agregada exitosamente.");
        } else {
            System.out.println("El espacio solicitado no está disponible.");
        }
    }

    /**
     * Método para confirmar una reserva
     */
    public void confirmReservation() {
        System.out.println("Confirmando reserva...");
        // Aquí se implementaría la lógica para confirmar la reserva
    }

    /**
     * Método para cancelar una reserva
     */
    public void cancelReservation() {
        System.out.println("Cancelando reserva...");
        // Me falta meter la logica de aqui
    }

    /**
     * Método para ver la disponibilidad de los espacios
     */
    public void seeAvailability() {
        System.out.println("Espacios disponibles:");
        for (SportSpace space : listSpace) {
            System.out.println("- " + space.getName() + " (Capacidad: " + space.getCapacity() + ")");
        }
    }

    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        system.startSystem();
    }
}

class User implements Serializable {
    private String name;
    private String id;

    // Constructor básico
    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Métodos simples para simular acciones de un usuario
    public void makeReservation() {
        System.out.println("Haciendo una reserva...");
        //Me falta meter la logica de aqui
    }

    public void cancelReservation() {
        System.out.println("Cancelando una reserva...");
        //Me falta meter la logica de aqui
    }
}

class SportSpace implements Serializable {
    private String name;
    private String type;
    private int capacity;
    private String availability;

    // Constructores
    public SportSpace(String name, String type, int capacity) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.availability = "Disponible";
    }

    // Getters 
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}

class Reservation implements Serializable {
    private SportSpace sportSpace;
    private LocalDate date;

    // Constructores
    public Reservation(SportSpace sportSpace, LocalDate date) {
        this.sportSpace = sportSpace;
        this.date = date;
    }
}
