import java.util.Scanner;

public interface Language {
    void chooseLanguage();
}

class LanguageSelector implements Language {
    private String selectedLanguage;

    @Override
    public void chooseLanguage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione un idioma: ");
        System.out.println("1. Español");
        System.out.println("2. English");
        System.out.print("Ingrese el numero del idioma: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                selectedLanguage = "Español";
                break;
            case 2:
                selectedLanguage = "English";
                break;
            default:
                selectedLanguage = "Español"; 
                System.out.println("Seleccion invalida. Se establece Español como predeterminado.");
                break;
        }
        System.out.println("Idioma seleccionado: " + selectedLanguage);
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    public static void main(String[] args) {
        LanguageSelector selector = new LanguageSelector();
        selector.chooseLanguage();
    }
}
