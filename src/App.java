import java.util.Scanner;

public class App {

    static double nota1 = -1, nota2 = -1, nota3 = -1;
    static double promedio = 0;
    static String nombreEstudiante = "N/A", estado= "N/A";

    static public void main(String[] args) {
        Scanner x = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero(x);
            switch (opcion) {
                case 1:
                    registrarEstudiante(x);
                    break;
                case 2:
                    mostrarDatosEstudiante();
                    break;
                case 3:
                    calcularPromedio();
                    break;
                case 4:
                    mostrarDatosEstudiante();
                    calcularPromedio();
                    setEstado();
                    break;
                case 5:
                    limpiarDatos();
                    System.out.println("Datos del estudiante actual limpiados.");
                    break;
                case 0:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        x.close();
    }

    static void mostrarMenu() {
        System.out.println(
                "--- Sistema de Registro de Estudiantes ---\n" +
                        "1. Registrar datos de un estudiante\n" + //
                        "2. Mostrar datos del estudiante actual\n" + //
                        "3. Calcular promedio de notas\n" + //
                        "4. Mostrar resumen completo del estudiante\n" + //
                        "5. Limpiar datos del estudiante actual\n" + //
                        "0. Salir\n" + //
                        "Ingrese su opción:");
    }

    static int leerEntero(Scanner x) {
        int valor = -1;
        while (valor < 0) {
            try {
                String entrada = x.nextLine().trim();
                valor = Integer.parseInt(entrada);
                if (valor < 0) {
                    System.out.println("Por favor ingrese un número entero no negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número entero.");
            }
        }
        return valor;
    } 

    static void registrarEstudiante(Scanner x) {
        if (nombreEstudiante!= "N/A") {
            System.out.println("Ya hay un estudiante registrado ¿Desea sobrescribirlo? (s/n)");
            String respuesta = x.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s")) {
                System.out.println("Registro cancelado.");
                return;
            }
        }
        nombreEstudiante = registroNombre(x);
        nota1 = registroNota(x, "Ingrese la primera nota (0-100):");
        nota2 = registroNota(x, "Ingrese la segunda nota (0-100):");
        nota3 = registroNota(x, "Ingrese la tercera nota (0-100):");
        System.out.println("Estudiante registrado exitosamente.");
    }

    static String registroNombre(Scanner x) {
        String nombre = "";
        while (nombre.isEmpty()) {
            System.out.println("Ingrese el nombre del estudiante");
            nombre = x.nextLine().trim();
        }
        return nombre;
    }

    static double registroNota(Scanner x, String mensaje) {
        double nota = -1;
        int i = 0;
        while (nota < 0 || nota > 100) {
            if (i != 0) {
                System.out.println("Nota inválida. Intente de nuevo.");
            }
            try {
                System.out.println(mensaje);
                String entrada = x.nextLine().trim().replace(",", ".");
                nota = Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número válido.");
            }
            i++;
        }
        return nota;
    }

    static void calcularPromedio() {
        if (!nombreEstudiante.equals("N/A")) {
            promedio = (nota1 + nota2 + nota3) / 3;
            System.out.printf("Promedio: %.2f\n", promedio);
            return;
        }
        System.out.println("No hay datos del estudiante registrado.");
        return;
    }

    static void limpiarDatos() {
        nombreEstudiante = "N/A";
        nota1 = -1;
        nota2 = -1;
        nota3 = -1;
        promedio = 0;
        estado = "N/A";
    }

    static void setEstado (){
        if (!nombreEstudiante.equals("N/A")) {
            estado= (promedio < 60) ? "Reprobado" : "Aprobado";
        }
        System.out.println("Estado del estudiante: " + estado);
    }

    static void mostrarDatosEstudiante() {
        if (!nombreEstudiante.equals("N/A")) {
            System.out.println("Nombre del estudiante: " + nombreEstudiante);
            System.out.printf("Nota 1: %.2f\n", nota1);
            System.out.printf("Nota 2: %.2f\n", nota2);
            System.out.printf("Nota 3: %.2f\n", nota3);

        } else {
            System.out.println("No hay datos del estudiante registrado.");
        }
    }
}
