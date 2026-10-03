/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculodeareas;
import java.util.Scanner;

// ==========================================
// 1. CLASE BASE (ABSTRACTA)
// ==========================================
abstract class FiguraGeometrica {
    private String nombre;

    public FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método abstracto que cada figura debe implementar obligatoriamente
    public abstract double calcularArea();

    // Método común para mostrar el resultado
    public void mostrarResultado() {
        System.out.printf("El área del %s es: %.2f%n", nombre, calcularArea());
    }
}

// ==========================================
// 2. CLASES HIJAS (HERENCIA Y POLIMORFISMO)
// ==========================================
class Cuadrado extends FiguraGeometrica {
    private double lado;

    public Cuadrado(double lado) {
        super("Cuadrado");
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }
}

class Rectangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Rectangulo(double base, double altura) {
        super("Rectángulo");
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }
}

class Triangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        super("Triángulo");
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2.0;
    }
}

class Circulo extends FiguraGeometrica {
    private double radio;

    public Circulo(double radio) {
        super("Círculo");
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }
}

// ==========================================
// 3. CLASE PRINCIPAL Y CONTROLADORA
// ==========================================
public class CalculoDeAreas {
    private Scanner scanner;

    public CalculoDeAreas() {
        this.scanner = new Scanner(System.in);
    }

    // Método auxiliar para validar que el usuario ingrese un número mayor a cero
    private double pedirNumero(String mensaje) {
        double valor = -1;
        while (valor <= 0) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine());
                if (valor <= 0) {
                    System.out.println("Por favor, ingresa un número mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número válido.");
            }
        }
        return valor;
    }

    // Flujo principal del programa
    public void ejecutar() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- CÁLCULO DE ÁREAS (JAVA - POO) ---");
            System.out.println("1. Cuadrado");
            System.out.println("2. Rectángulo");
            System.out.println("3. Triángulo");
            System.out.println("4. Círculo");
            System.out.println("5. Salir");
            System.out.print("Elige una opción (1-5): ");

            String opcion = scanner.nextLine().trim();
            FiguraGeometrica figura = null;

            switch (opcion) {
                case "1":
                    double lado = pedirNumero("Ingresa el lado del cuadrado: ");
                    figura = new Cuadrado(lado);
                    break;

                case "2":
                    double baseRect = pedirNumero("Ingresa la base del rectángulo: ");
                    double altRect = pedirNumero("Ingresa la altura del rectángulo: ");
                    figura = new Rectangulo(baseRect, altRect);
                    break;

                case "3":
                    double baseTri = pedirNumero("Ingresa la base del triángulo: ");
                    double altTri = pedirNumero("Ingresa la altura del triángulo: ");
                    figura = new Triangulo(baseTri, altTri);
                    break;

                case "4":
                    double radio = pedirNumero("Ingresa el radio del círculo: ");
                    figura = new Circulo(radio);
                    break;

                case "5":
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    continue;

                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
                    continue;
            }

            // Polimorfismo: se ejecuta mostrarResultado() sobre la referencia abstracta
            if (figura != null) {
                System.out.println();
                figura.mostrarResultado();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        CalculoDeAreas app = new CalculoDeAreas();
        app.ejecutar();
    }
}