
import java.util.Scanner;

public class Impares {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      System.out.println("Digite um valor: ");
      int x = entrada.nextInt();
      System.out.println("\n");

      for (int i = 1; i < x; i++) {
          if (i % 2 == 0){
            continue;
          }else {
            System.out.println(+i);
          }
      }
  }
}
