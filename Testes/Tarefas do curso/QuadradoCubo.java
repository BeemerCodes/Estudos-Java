
import java.util.Scanner;

public class QuadradoCubo {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      System.out.println("Digite o valor: ");
      int n = entrada.nextInt();

      for (int i=1; i<n; i++){
        int primeiro = i;
        int segundo = i * i;
        int terceiro = i* i * i;

        System.out.printf("%d %d %d\n", primeiro, segundo, terceiro);
      }
  }
}


