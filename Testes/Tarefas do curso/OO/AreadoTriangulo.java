import java.util.Scanner;

public class AreadoTriangulo {
  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);

    double xarea, xa, xb, xc, xp;
    double yarea, ya, yb, yc, yp;

    System.out.print("Digite o primeiro valor: ");
    xa = entrada.nextDouble();

    System.out.print("Digite o segundo valor: ");
    xb = entrada.nextDouble();

    System.out.print("Digite o terceiro valor: ");
    xc = entrada.nextDouble();

    xp = (xa + xb + xc)/2.0;
    xarea = Math.sqrt(xp*(xp-xa)*(xp-xb)*(xp-xc)); 

    System.out.printf("Area do triangulo X: %.2f%n", xarea);

    System.out.print("Digite o primeiro valor: ");
    ya = entrada.nextDouble();

    System.out.print("Digite o segundo valor: ");
    yb = entrada.nextDouble();

    System.out.print("Digite o terceiro valor: ");
    yc = entrada.nextDouble();

    yp = (ya + yb + yc)/2.0;
    yarea = Math.sqrt(yp*(yp-ya)*(yp-yb)*(yp-yc)); 

    System.out.printf("Area do triangulo Y: %.2f%n", yarea);

    if (xarea > yarea) {
        System.out.println("A maior area é do triangulo X.");
    }else {
      System.out.println("A maior area é do triangulo Y");
    }

  }
}
