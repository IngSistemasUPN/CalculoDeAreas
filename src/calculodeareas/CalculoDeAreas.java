/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculodeareas;
import java.util.Scanner;

// ==========================================
// 1. MODELO / DOMINIO
// ==========================================

abstract class FiguraGeometrica {
    private final String nombre;

    protected FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularArea();

    public void imprimirResultado() {
        System.out.printf("El área del %s es: %.2f%n", nombre, calcularArea());
    }
}

class Cuadrado extends FiguraGeometrica {
    private final double longitudLado;

    public Cuadrado(double longitudLado) {
        super("Cuadrado");
        this.longitudLado = longitudLado;
    }

    @Override
    public double calcularArea() {
        return longitudLado * longitudLado;
    }
}

class Rectangulo extends FiguraGeometrica {
    private final double longitudBase;
    private final double longitudAltura;

    public Rectangulo(double longitudBase, double longitudAltura) {
        super("Rectángulo");
        this.longitudBase = longitudBase;
        this.longitudAltura = longitudAltura;
    }

    @Override
    public double calcularArea() {
        return longitudBase * longitudAltura;
    }
}

class Triangulo extends FiguraGeometrica {
    private final double longitudBase;
    private final double longitudAltura;

    public Triangulo(double longitudBase, double longitudAltura) {
        super("Triángulo");
        this.longitudBase = longitudBase;
        this.longitudAltura = longitudAltura;
    }

    @Override
    public double calcularArea() {
        return (longitudBase * longitudAltura) / 2.0;
    }
}

class Circulo extends FiguraGeometrica {
    private final double longitudRadio;

    public Circulo(double longitudRadio) {
        super("Círculo");
        this.longitudRadio = longitudRadio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(longitudRadio, 2);
    }
}

// ==========================================
// 2. MÓDULO DE INTERACCIÓN POR CONSOLA
// ==========================================

class Consola {
    private final Scanner lector;

    public Consola() {
        this.lector = new Scanner(System.in);
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return lector.nextLine().trim();
    }

    public double leerNumeroPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = lector.nextLine().trim();
            try {
                double valor = Double.parseDouble(entrada);
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Error: El valor debe ser un número estrictamente mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un formato numérico válido.");
            }
        }
    }

    public void cerrar() {
        lector.close();
    }
}

// ==========================================
// 3. APLICACIÓN PRINCIPAL
// ==========================================

public class CalculoDeAreas {
    private final Consola consola;

    public CalculoDeAreas() {
        this.consola = new Consola();
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenuOpciones();
            String opcionSeleccionada = consola.leerTexto("Seleccione una opción: ");

            switch (opcionSeleccionada) {
                case "1":
                    procesarFigura(crearCuadrado());
                    break;
                case "2":
                    procesarFigura(crearRectangulo());
                    break;
                case "3":
                    procesarFigura(crearTriangulo());
                    break;
                case "4":
                    procesarFigura(crearCirculo());
                    break;
                case "5":
                    System.out.println("Cerrando el programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Ingrese un valor del 1 al 5.");
                    break;
            }
        }

        consola.cerrar();
    }

    private void mostrarMenuOpciones() {
        System.out.println("\n=== CÁLCULO DE ÁREAS GEOMÉTRICAS ===");
        System.out.println("1. Cuadrado");
        System.out.println("2. Rectángulo");
        System.out.println("3. Triángulo");
        System.out.println("4. Círculo");
        System.out.println("5. Salir");
    }

    private void procesarFigura(FiguraGeometrica figura) {
        System.out.println();
        figura.imprimirResultado();
    }

    // Métodos fábrica modulares para desacoplar la creación del flujo del switch
    private Cuadrado crearCuadrado() {
        double lado = consola.leerNumeroPositivo("Ingrese el lado del cuadrado: ");
        return new Cuadrado(lado);
    }

    private Rectangulo crearRectangulo() {
        double base = consola.leerNumeroPositivo("Ingrese la base del rectángulo: ");
        double altura = consola.leerNumeroPositivo("Ingrese la altura del rectángulo: ");
        return new Rectangulo(base, altura);
    }

    private Triangulo crearTriangulo() {
        double base = consola.leerNumeroPositivo("Ingrese la base del triángulo: ");
        double altura = consola.leerNumeroPositivo("Ingrese la altura del triángulo: ");
        return new Triangulo(base, altura);
    }

    private Circulo crearCirculo() {
        double radio = consola.leerNumeroPositivo("Ingrese el radio del círculo: ");
        return new Circulo(radio);
    }

    public static void main(String[] args) {
        new CalculoDeAreas().iniciar();
    }
}