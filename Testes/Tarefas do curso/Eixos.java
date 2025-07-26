
import java.util.Scanner;

public class Eixos {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      System.out.println("Digite o primeiro valor: ");
      double x = entrada.nextDouble();

      System.out.println("Digite o segundo valor: ");
      double y = entrada.nextDouble();

      if (x == 0 && y == 0){
        System.out.println("Posição: Origem");
      } else if (x > 0 && y == 0){
        System.out.println("Posição: Eixo X em " + x);
      }else if (y > 0 && x == 0) {
        System.out.println("Posição: Eixo Y em " + y);
      }else if (x > 0 && y > 0) {
        System.out.println("Posição: Q1");
      }else if (x < 0 && y > 0) {
        System.out.println("Posição: Q2");
      }else if (x < 0 && y < 0) {
        System.out.println("Posição: Q3");
      }else if (x > 0 && y < 0) {
        System.out.println("Posição: Q4");
      }

    entrada.close();
  }
}
