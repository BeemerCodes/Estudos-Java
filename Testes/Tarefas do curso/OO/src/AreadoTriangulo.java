import java.util.Scanner;

import entities.Triangulo;

public class AreadoTriangulo {
  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);

    double xp, yp, xarea, yarea;
    Triangulo x, y;

    x = new Triangulo();
    y = new Triangulo();

    System.out.print("Digite o primeiro valor: ");
    x.a = entrada.nextDouble();

    System.out.print("Digite o segundo valor: ");
    x.b = entrada.nextDouble();

    System.out.print("Digite o terceiro valor: ");
    x.c = entrada.nextDouble();

    xp = (x.a + x.b + x.c)/2.0;
    xarea = Math.sqrt(xp*(xp-x.a)*(xp-x.b)*(xp-x.c)); 

    System.out.printf("Area do triangulo X: %.2f%n", xarea);

    System.out.print("Digite o primeiro valor: ");
    y.a = entrada.nextDouble();

    System.out.print("Digite o segundo valor: ");
    y.b = entrada.nextDouble();

    System.out.print("Digite o terceiro valor: ");
    y.c = entrada.nextDouble();

    yp = (y.a + y.b + y.c)/2.0;
    yarea = Math.sqrt(yp*(yp-y.a)*(yp-y.b)*(yp-y.c)); 

    System.out.printf("Area do triangulo Y: %.2f%n", yarea);

    if (xarea > yarea) {
        System.out.println("A maior area é do triangulo X.");
    }else {
      System.out.println("A maior area é do triangulo Y");
    }

  }
}
