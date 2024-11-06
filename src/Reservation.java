import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
// webi wabo
    private SportSpace sportSpace;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String state;
    private User user;

    // Método principal de reservación.
    public static void main(String[] args) {
        Reservation reservation = new Reservation();
        reservation.showAvailableDates(10);
        reservation.setState("Confirmada");
        System.out.println("Estado de la reserva: " + reservation.getState());
    }

    // Días disponibles para reservar.
    public void showAvailableDates(int availableDays) {
        List<LocalDate> dateReservation = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < availableDays; i++) {
            dateReservation.add(currentDate.plusDays(i));
        }

        // Mostrar fechas disponibles.
        System.out.println("Las fechas de reservación disponibles son: ");
        dateReservation.forEach(date -> {
            System.out.print(date + " ");
        });
        System.out.println();
    }

    // Getters and setters.
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    // Validación de reserva por su tipo.
    public void validateReservationType(String typeOfReservation) throws IOException {
        switch (typeOfReservation) {
            case "Gimnasio" ->
                System.out.println("Reserva de gym.");
            case "Cancha" ->
                System.out.println("Reserva de cancha.");
            case "Piscina" ->
                System.out.println("Reserva de piscina.");
            case "Spa" ->
                System.out.println("Reserva de spa.");
            default ->
                System.out.println("No se encontró el tipo de reserva.");
        }
    }

    /**
     * Validación de pago para la reservación.
     */
    public boolean confirmPayment() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("¿Desea confirmar el pago? (Sí/No) ");
        String confirmation = reader.readLine();

        if (confirmation.equalsIgnoreCase("Sí") || confirmation.equalsIgnoreCase("Yes")) {
            System.out.println("Pago confirmado.");
            return true;  // Pago confirmado.
        } else {
            System.out.println("Pago no confirmado.");
            return false;  // Pago no confirmado
        }
    }

    // Clase interna para el espacio deportivo
    public static class SportSpace {

        private String name;
        private String type;
        private int capacity;

        public SportSpace(String name, String type, int capacity) {
            this.name = name;
            this.type = type;
            this.capacity = capacity;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getCapacity() {
            return capacity;
        }
    }
}
