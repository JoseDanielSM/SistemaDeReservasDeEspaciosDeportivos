import java.io.*;
import java.net.*;

/**
 * @author Jose Daniel Araya Hernandez
 * Esta clase se encarga de editar las reservas, compartirlas y notificar.
 */
public final class Administrator extends User {

    public Administrator(String name, String id) {
        super(name, id);
    }

    /**
     * Edita la reserva realizada y maneja posibles excepciones.
     *
     * @throws IOException 
     */
    public void manageSpace() throws IOException {
        String fileName = "reservas.txt";

        // Leer el contenido del archivo reservas.txt
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
            return;
        }

        // Reemplazar el contenido del archivo según sea necesario
        String nuevoContenido = content.toString().replace("before.", "after.");

        // Escribir el nuevo contenido en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(nuevoContenido);
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo: " + ex.getMessage());
        }

        System.out.println("Archivo modificado.");
    }

    /**
     * Muestra las reservas realizadas.
     *
     * @throws IOException 
     */
    public void seeReservationMade() throws IOException {
        String fileName = "reservas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println("No se pudo encontrar el archivo.");
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Método de sugerencia para modificar un usuario (por implementar).
     */
    public void modifyUser() {
        // Sugerencia de eliminación.
    }

    /**
     * Comparte la reserva con otras personas.
     *
     * @throws IOException
     */
    
    // Eliminé @Override
public void shareReservation() throws IOException {
    int puerto = 5000;

    try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
        System.out.println("Esperando conexión...");
        Socket socket = servidorSocket.accept();
        System.out.println("Cliente conectado.");

        File archivo = new File("reservas.txt");
        try (FileInputStream fileInputStream = new FileInputStream(archivo);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             OutputStream outputStream = socket.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Archivo enviado.");
        }
    } catch (IOException e) {
        System.out.println("Error durante la conexión: " + e.getMessage());
        e.printStackTrace();
    }
}


    /**
     * Método que maneja el espacio de calidad (ya implementado en otro código).
     *
     * @throws IOException
     */
    public void qualitySpace() throws IOException {
        // Esta parte ya la hace el código de Leo.
    }

    /**
     * Recibe notificaciones (por implementar).
     */
    public void receiveNotification() {
        // Por implementar.
}

}