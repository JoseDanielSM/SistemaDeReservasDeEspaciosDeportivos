import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *@author Grace Solano Delgado - C4K066
 */
public class Reservation implements Serializable {
    private SportSpace sportSpace;
    private LocalDate date;

    // Constructor
    public Reservation(SportSpace sportSpace, LocalDate date) {
        this.sportSpace = sportSpace;
        this.date = date;
    }

    // Getters y Setters
    public SportSpace getSportSpace() {
        return sportSpace;
    }

    public void setSportSpace(SportSpace sportSpace) {
        this.sportSpace = sportSpace;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reserva: Espacio Deportivo = " + sportSpace.getName() + ", Fecha = " + date;
    }

    // Metodo para guardar reservas en un archivo
    public static void saveReservations(List<Reservation> reservations, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(reservations);
        } catch (IOException e) {
            System.out.println("Error al guardar las reservas: " + e.getMessage());
        }
    }

    // Metodo para cargar reservas desde un archivo
    public static List<Reservation> loadReservations(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Reservation>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudieron cargar las reservas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Metodo para probar la clase
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Reservation> reservations = new ArrayList<>();

        System.out.print("Ingrese el nombre del espacio deportivo: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la capacidad del espacio deportivo: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        SportSpace sportSpace = new SportSpace(name, capacity);

        System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        Reservation reservation = new Reservation(sportSpace, date);
        reservations.add(reservation);

        System.out.println(reservation);

        // Guardar las reservas en un archivo
        saveReservations(reservations, "reservations.dat");

        // Cargar las reservas desde el archivo
        List<Reservation> loadedReservations = loadReservations("reservations.dat");
        System.out.println("Reservas cargadas:");
        loadedReservations.forEach(System.out::println);
    }
}
