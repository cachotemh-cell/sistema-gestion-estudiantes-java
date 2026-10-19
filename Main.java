import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Sistema de Gestión de Estudiantes.
 * Proyecto académico para practicar Programación Orientada a Objetos en Java.
 */
public class Main {

    /**
     * Representa a un estudiante y sus datos académicos.
     */
    static class Estudiante {
        // Constante de clase: calificación mínima para aprobar.
        public static final double NOTA_APROBATORIA = 70.0;

        private String nombre;
        private String matricula;
        private double calificacion;

        /**
         * Construye un estudiante y valida los datos recibidos.
         */
        public Estudiante(String nombre, String matricula, double calificacion) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            if (matricula == null || matricula.trim().isEmpty()) {
                throw new IllegalArgumentException("La matrícula no puede estar vacía.");
            }
            if (calificacion < 0 || calificacion > 100) {
                throw new IllegalArgumentException(
                    "La calificación debe estar entre 0 y 100."
                );
            }

            this.nombre = nombre.trim();
            this.matricula = matricula.trim();
            this.calificacion = calificacion;
        }

        /** Indica si la calificación alcanza la nota mínima aprobatoria. */
        public boolean aprobo() {
            return calificacion >= NOTA_APROBATORIA;
        }

        /** Devuelve los datos del estudiante en formato legible. */
        public String mostrarInformacion() {
            String estado = aprobo() ? "Aprobado" : "No aprobado";
            return String.format(
                "Matrícula: %s | Nombre: %s | Calificación: %.2f | Estado: %s",
                matricula, nombre, calificacion, estado
            );
        }

        /** Devuelve la matrícula para las búsquedas y validaciones. */
        public String getMatricula() {
            return matricula;
        }

        /** Devuelve la calificación para calcular el promedio. */
        public double getCalificacion() {
            return calificacion;
        }
    }

    /**
     * Administra los objetos Estudiante registrados en el sistema.
     */
    static class GestionEstudiantes {
        // Lista dinámica que almacena las instancias de Estudiante.
        private List<Estudiante> estudiantes;

        /** Inicializa la colección vacía. */
        public GestionEstudiantes() {
            estudiantes = new ArrayList<>();
        }

        /**
         * Registra un estudiante y evita que se repita una matrícula.
         */
        public void registrar(Estudiante estudiante) {
            if (estudiante == null) {
                throw new IllegalArgumentException("El estudiante no puede ser nulo.");
            }

            for (Estudiante registrado : estudiantes) {
                if (registrado.getMatricula().equalsIgnoreCase(estudiante.getMatricula())) {
                    throw new IllegalArgumentException(
                        "Ya existe un estudiante con esa matrícula."
                    );
                }
            }
            estudiantes.add(estudiante);
        }

        /** Devuelve la lista de estudiantes registrados. */
        public List<Estudiante> listar() {
            return new ArrayList<>(estudiantes);
        }

        /**
         * Busca por matrícula. Devuelve null si no encuentra coincidencias.
         */
        public Estudiante buscarPorMatricula(String matricula) {
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getMatricula().equalsIgnoreCase(matricula.trim())) {
                    return estudiante;
                }
            }
            return null;
        }

        /**
         * Calcula el promedio del grupo; devuelve -1 si no hay estudiantes.
         */
        public double calcularPromedio() {
            if (estudiantes.isEmpty()) {
                return -1;
            }

            double suma = 0;
            for (Estudiante estudiante : estudiantes) {
                suma += estudiante.getCalificacion();
            }
            return suma / estudiantes.size();
        }
    }

    /** Lee una calificación válida y maneja entradas no numéricas. */
    private static double leerCalificacion(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Calificación (0 a 100): ");
                double calificacion = Double.parseDouble(scanner.nextLine().trim());
                if (calificacion >= 0 && calificacion <= 100) {
                    return calificacion;
                }
                System.out.println("Error: escribe una calificación entre 0 y 100.");
            } catch (NumberFormatException error) {
                System.out.println("Error: escribe un número válido.");
            }
        }
    }

    /** Muestra las opciones del menú principal. */
    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE GESTIÓN DE ESTUDIANTES =====");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Mostrar estudiantes");
        System.out.println("3. Buscar por matrícula");
        System.out.println("4. Calcular promedio del grupo");
        System.out.println("5. Salir");
    }

    /**
     * Método principal: instancia el gestor y coordina la interacción con el usuario.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionEstudiantes sistema = new GestionEstudiantes();

        // El ciclo mantiene el menú activo hasta que se elige salir.
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    try {
                        System.out.print("Nombre completo: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Matrícula: ");
                        String matricula = scanner.nextLine();
                        double calificacion = leerCalificacion(scanner);

                        // Instanciación de un objeto y prueba del método registrar.
                        Estudiante estudiante = new Estudiante(nombre, matricula, calificacion);
                        sistema.registrar(estudiante);
                        System.out.println("Estudiante registrado correctamente.");
                    } catch (IllegalArgumentException error) {
                        System.out.println("No fue posible registrar: " + error.getMessage());
                    }
                    break;

                case "2":
                    List<Estudiante> lista = sistema.listar();
                    if (lista.isEmpty()) {
                        System.out.println("Todavía no hay estudiantes registrados.");
                    } else {
                        System.out.println("\n--- ESTUDIANTES REGISTRADOS ---");
                        for (Estudiante estudiante : lista) {
                            System.out.println(estudiante.mostrarInformacion());
                        }
                    }
                    break;

                case "3":
                    System.out.print("Matrícula que deseas buscar: ");
                    String matriculaBuscada = scanner.nextLine();
                    Estudiante encontrado = sistema.buscarPorMatricula(matriculaBuscada);
                    if (encontrado != null) {
                        System.out.println(encontrado.mostrarInformacion());
                    } else {
                        System.out.println("No se encontró una matrícula con ese dato.");
                    }
                    break;

                case "4":
                    double promedio = sistema.calcularPromedio();
                    if (promedio < 0) {
                        System.out.println("No hay calificaciones para calcular el promedio.");
                    } else {
                        System.out.printf("Promedio del grupo: %.2f%n", promedio);
                    }
                    break;

                case "5":
                    continuar = false;
                    System.out.println("Gracias por utilizar el sistema. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Selecciona un número del 1 al 5.");
            }
        }

        scanner.close();
    }
}
