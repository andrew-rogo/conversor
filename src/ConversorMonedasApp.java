import java.util.Scanner;

public class ConversorMonedasApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorConversionMonedas gestorConversion = new GestorConversionMonedas();

        while (true) {
            System.out.println("Seleccione la opción:");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Salir");

            int opcion = scanner.nextInt();
            if (opcion == 1) {
                System.out.println("Tipos de monedas más comunes, sino está en el listado puede usuarla. :");
                System.out.println("USD - Dólar estadounidense");
                System.out.println("EUR - Euro");
                System.out.println("GBP - Libra esterlina");
                System.out.println("JPY - Yen japonés");
                System.out.println("AUD - Dólar australiano");
                System.out.println("CAD - Dólar canadiense");
                System.out.println("CHF - Franco suizo");
                System.out.println("CNY - Yuan chino");
                System.out.println("COP - Peso colombiano");
                System.out.println("MXN - Peso mexicano");
                System.out.println("ARS - Peso argentino");
                System.out.println("CLP - Peso chileno");
                System.out.println("UYU - Peso uruguayo");
                System.out.println("PEN - Sol peruano");
                System.out.println("BRL - Real brasileño");
                System.out.print("Ingrese la moneda inicial (ej. USD): ");
                String monedaOrigen = scanner.next().toUpperCase();
                System.out.print("Ingrese la moneda final (ej. EUR): ");
                String monedaDestino = scanner.next().toUpperCase();
                System.out.print("Ingrese la cantidad a convertir: ");
                double cantidad = scanner.nextDouble();

                double cantidadConvertida = gestorConversion.convertirMoneda(monedaOrigen, monedaDestino, cantidad);
                System.out.printf("La cantidad convertida es: %.2f %s\n", cantidadConvertida, monedaDestino);
            } else if (opcion == 2) {
                gestorConversion.guardarConversionesEnJson();
                gestorConversion.mostrarResumenConversiones();
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}
