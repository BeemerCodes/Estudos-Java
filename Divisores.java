
import java.util.Scanner;

public class Divisores {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      System.out.println("Digite o valor para saber seus divisores: ");
      int valor = entrada.nextInt();
      System.out.println("\n");

      for (int i=1; i <= valor; i++){
        if (valor % i == 0){
          System.out.println(i);
        }
      }
  }
}
