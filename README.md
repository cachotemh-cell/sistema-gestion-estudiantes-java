# Sistema de Gestión de Estudiantes (Java)

Proyecto académico para practicar Programación Orientada a Objetos (POO) con Java.

## Requisitos
- JDK 8 o superior
- No requiere librerías externas

## Cómo ejecutar
Abre una terminal en la carpeta del proyecto y ejecuta:

```bash
javac Main.java
java Main
```

También puedes abrir `Main.java` en IntelliJ IDEA, Eclipse, NetBeans o VS Code con la extensión de Java y ejecutar el método `main`.

## Funcionalidades
- Registrar estudiantes con nombre, matrícula y calificación.
- Mostrar estudiantes registrados.
- Buscar por matrícula.
- Calcular el promedio del grupo.
- Validar calificaciones entre 0 y 100.
- Evitar matrículas duplicadas.

## Conceptos de POO
- **Clases:** `Estudiante` y `GestionEstudiantes` (definidas dentro de `Main` para facilitar la ejecución de un solo archivo).
- **Atributos:** nombre, matrícula, calificación y lista de estudiantes.
- **Métodos:** `aprobo`, `mostrarInformacion`, `registrar`, `listar`, `buscarPorMatricula` y `calcularPromedio`.
- **Objetos:** cada estudiante se crea con `new Estudiante(...)`.
- **Encapsulamiento:** los atributos de `Estudiante` y la lista son privados.
- **Validación y excepciones:** datos vacíos, calificaciones inválidas y matrículas repetidas se rechazan.

## Pruebas recomendadas
1. Registra a Ana con matrícula A001 y calificación 85.
2. Registra a Luis con matrícula A002 y calificación 60.
3. Muestra los registros: Ana debe aparecer como aprobada y Luis como no aprobado.
4. Busca A001 y luego una matrícula inexistente.
5. Calcula el promedio: debe ser 72.50.
6. Intenta introducir una calificación de 120 y verifica que se rechace.
7. Intenta registrar de nuevo la matrícula A001.

## Subir a GitHub
1. Crea un repositorio vacío en https://github.com/new con el nombre `sistema-gestion-estudiantes-java`.
2. En la terminal, dentro de esta carpeta, ejecuta los comandos siguientes y sustituye `TU-USUARIO` por tu usuario de GitHub:

```bash
git init
git add Main.java README.md
git commit -m "Crear sistema de gestion de estudiantes en Java"
git branch -M main
git remote add origin https://github.com/TU-USUARIO/sistema-gestion-estudiantes-java.git
git push -u origin main
```

Si Git no reconoce tu identidad, configúrala con `git config --global user.name "Tu Nombre"` y `git config --global user.email "tu-correo"`.
