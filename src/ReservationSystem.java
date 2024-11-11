import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *@author José Daniel Segura Menjivar - C4J929
 */
public class ReservationSystem {
    // Listas para almacenar usuarios y espacios deportivos.
    private List<SportSpace> listSpace = new ArrayList<>();
    private List<Reservation> listReservations = new ArrayList<>();

    public void startSystem() {
        loadSportSpaces();
        loadReservations();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Para mostrar el menu de acciones.
            System.out.println("\n--- Sistema de Reservas Deportivas ---");
            System.out.println("1. Registrar Espacio Deportivo");
            System.out.println("2. Modificar Espacio Deportivo");
            System.out.println("3. Eliminar Espacio Deportivo");
            System.out.println("4. Ver Espacios Disponibles");
            System.out.println("5. Realizar Reserva");
            System.out.println("6. Cancelar Reserva");
            System.out.println("7. Ver Reservas");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registerSpace(scanner);
                    break;
                case 2:
                    modifySpace(scanner);
                    break;
                case 3:
                    deleteSpace(scanner);
                    break;
                case 4:
                    seeAvailability();
                    break;
                case 5:
                    makeReservation(scanner);
                    break;
                case 6:
                    cancelReservation(scanner);
                    break;
                case 7:
                    viewReservations();
                    break;
                case 8:
                    running = false;
                    saveSportSpaces();
                    saveReservations();
                    System.out.println("Saliendo del sistema. Gracias por usar el Sistema de Reservas Deportivas!");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
                    break;
            }
        }
        scanner.close();
    }

    /**
     * Este metodo es para registrar un nuevo espacio deportivo.
     * @param scanner
     */
    public void registerSpace(Scanner scanner) {
        System.out.print("Ingrese el nombre del espacio deportivo: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la capacidad del espacio: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        SportSpace newSpace = new SportSpace(name, capacity);
        listSpace.add(newSpace);
        System.out.println("Espacio deportivo registrado con éxito!");
    }

    /**
     * Este metodo es para modificar un espacio deportivo que ya existe.
     * @param scanner
     */
    public void modifySpace(Scanner scanner) {
        System.out.print("Ingrese el nombre del espacio deportivo a modificar: ");
        String name = scanner.nextLine();

        for (SportSpace space : listSpace) {
            if (space.getName().equalsIgnoreCase(name)) {
                System.out.print("Ingrese la nueva capacidad del espacio: ");
                int capacity = scanner.nextInt();
                scanner.nextLine();
                space.setCapacity(capacity);
                System.out.println("Espacio deportivo modificado con éxito!");
                return;
            }
        }
        System.out.println("Espacio deportivo no encontrado.");
    }

    /**
     * Este metodo funciona para eliminar un espacio deportivo que se haya agregado previamente.
     * @param scanner
     */
    public void deleteSpace(Scanner scanner) {
        System.out.print("Ingrese el nombre del espacio deportivo a eliminar: ");
        String name = scanner.nextLine();

        listSpace.removeIf(space -> space.getName().equalsIgnoreCase(name));
        System.out.println("Espacio deportivo eliminado (si existía).");
    }

    /**
     * Un metodo para ver la disponibilidad de un espacio deportivo previamente creado.
     */
    public void seeAvailability() {
        if (listSpace.isEmpty()) {
            System.out.println("No hay espacios registrados.");
        } else {
            System.out.println("Espacios disponibles:");
            for (SportSpace space : listSpace) {
                System.out.println("- " + space.getName() + " (Capacidad: " + space.getCapacity() + ", Disponible: " + (space.isAvailable() ? "Si" : "No") + ")");
            }
        }
    }

    /**
     * Este metodo es para crear una reserva con todo y la fecha.
     * @param scanner
     */
    public void makeReservation(Scanner scanner) {
        System.out.print("Ingrese el nombre del espacio deportivo a reservar: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        for (SportSpace space : listSpace) {
            if (space.getName().equalsIgnoreCase(name) && space.isAvailable()) {
                Reservation newReservation = new Reservation(space, date);
                listReservations.add(newReservation);
                space.setAvailable(false);
                System.out.println("Reserva realizada con exito!");
                return;
            }
        }
        System.out.println("No se pudo realizar la reserva. Espacio no disponible o no encontrado.");
    }

    /**
     * Este metodo es para cancelar una reserva.
     * @param scanner
     */
    public void cancelReservation(Scanner scanner) {
        System.out.print("Ingrese el nombre del espacio deportivo a cancelar la reserva: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        listReservations.removeIf(reservation -> reservation.getSportSpace().getName().equalsIgnoreCase(name) && reservation.getDate().equals(date));
        System.out.println("Reserva cancelada (si existía).");
    }

    /**
     * Un metodo para ver todas las reservas que se hicieron.
     */
    public void viewReservations() {
        if (listReservations.isEmpty()) {
            System.out.println("No hay reservas realizadas.");
        } else {
            System.out.println("Reservas realizadas:");
            for (Reservation reservation : listReservations) {
                System.out.println("- Espacio: " + reservation.getSportSpace().getName() + ", Fecha: " + reservation.getDate());
            }
        }
    }

    /**
     * este metodo es para cargar los archivos de un espacio deportivo.
     */
    public void loadSportSpaces() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sportspaces.dat"))) {
            listSpace = (List<SportSpace>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontraron espacios deportivos previos.");
        }
    }

    /**
     * este sirve para guardar los archivos de un espacio creado.
     */
    public void saveSportSpaces() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sportspaces.dat"))) {
            oos.writeObject(listSpace);
        } catch (IOException e) {
            System.out.println("Error al guardar los espacios deportivos.");
        }
    }

    /**
     * y este para cargar una reserva desde un archivo.
     */
    public void loadReservations() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reservations.dat"))) {
            listReservations = (List<Reservation>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontraron reservas previas.");
        }
    }

    /**
     * este metodo sirve para guardar una reserva.
     */
    public void saveReservations() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("reservations.dat"))) {
            oos.writeObject(listReservations);
        } catch (IOException e) {
            System.out.println("Error al guardar las reservas.");
        }
    }

    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        system.startSystem();
    }
}

class Reservation implements Serializable {
    private SportSpace sportSpace;
    private LocalDate date;

    // Constructors.
    public Reservation(SportSpace sportSpace, LocalDate date) {
        this.sportSpace = sportSpace;
        this.date = date;
    }

    // Getters.
    public SportSpace getSportSpace() {
        return sportSpace;
    }

    public LocalDate getDate() {
        return date;
    }
}
