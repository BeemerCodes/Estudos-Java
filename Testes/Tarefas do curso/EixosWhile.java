
import java.util.Scanner;

public class EixosWhile {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      double x;
      double y;

      do { 
          System.out.println("\nDigite a primeira posição: ");
        x = entrada.nextDouble();

        System.out.println("Digite a segunda posição: ");
        y = entrada.nextDouble();

        if (x > 0 && y > 0) {
          System.out.println("Posição: Primeiro.");
        }else if (x < 0 && y > 0) {
          System.out.println("Posição: Segundo.");
        }else if (x < 0 && y < 0) {
          System.out.println("Posição: Terceiro.");
        }else if (x > 0 && y < 0) {
          System.out.println("Posição: Quarto.");
      }
      } while (x != 0 && y != 0);
  }
}
