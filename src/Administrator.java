import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

/**
 *@author José Daniel Araya Hernández
 */
public final class Administrator extends User {

    public Administrator(String name, String id) {
        super(name, id);
    }
    
    /**
     * Muestra las reservas realizadas.
     */
    public void seeReservationMade() {
        List<Reservation> reservations = Reservation.loadReservations("reservations.dat");
        if (reservations.isEmpty()) {
            System.out.println("No hay reservas realizadas.");
        } else {
            System.out.println("--- Reservas Realizadas ---");
            reservations.forEach(System.out::println);
        }
    }

    /**
     * Agrega una nueva reserva
     * @param scanner
     */
    public void addReservation(Scanner scanner) {
        System.out.print("Ingrese el nombre del espacio deportivo: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la capacidad del espacio deportivo: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer

        SportSpace sportSpace = new SportSpace(name, capacity);

        System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        Reservation newReservation = new Reservation(sportSpace, date);
        List<Reservation> reservations = Reservation.loadReservations("reservations.dat");
        reservations.add(newReservation);
        Reservation.saveReservations(reservations, "reservations.dat");

        System.out.println("Reserva agregada con éxito.");
    }

    /**
     * Cancela una reserva existente
     * @param scanner
     */
    public void cancelReservation(Scanner scanner) {
        System.out.print("Ingrese el nombre del espacio deportivo a cancelar la reserva: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        List<Reservation> reservations = Reservation.loadReservations("reservations.dat");
        reservations.removeIf(reservation -> reservation.getSportSpace().getName().equalsIgnoreCase(name) && reservation.getDate().equals(date));
        Reservation.saveReservations(reservations, "reservations.dat");

        System.out.println("Reserva cancelada (si existía).");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Administrator admin = new Administrator("Admin", "001");

        while (true) {
            System.out.println("-------- Menú de Opciones ----------");
            System.out.println("1. Ver Reservas Realizadas");
            System.out.println("2. Agregar Reserva");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Salir");
            System.out.println("------------------------------------");

            System.out.print("Ingrese la opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    admin.seeReservationMade();
                    break;
                case 2:
                    admin.addReservation(scanner);
                    break;
                case 3:
                    admin.cancelReservation(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema....");
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}

class User {
    private String name;
    private String id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
