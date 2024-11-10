import java.util.Scanner;
import java.io.Serializable;

/**
 * @author Leonardo Aguilar Berrocal - C4C114
 */
public class SportSpace implements Serializable {
    // Atributos.
    private String name;
    private int capacity;
    private boolean available;

    // Constructor.
    public SportSpace(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.available = true; // Esto es para que por defecto desmuestre que el espacio si este disponible.
    }

    // Getters y Setters.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Espacio Deportivo: " + name + ", Capacidad: " + capacity + ", Disponible: " + (available ? "Si" : "No");
    }

    // Metodo para probar la clase.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del espacio deportivo: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la capacidad del espacio deportivo: ");
        int capacity = scanner.nextInt();

        SportSpace space = new SportSpace(name, capacity);
        System.out.println(space);

        System.out.print("¿Desea marcar el espacio como no disponible? (si/no): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("si")) {
            space.setAvailable(false);
        }

        System.out.println("Estado actualizado: " + space);
    }
}
