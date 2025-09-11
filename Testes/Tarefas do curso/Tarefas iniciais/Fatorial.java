
import java.util.Scanner;

public class Fatorial {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      System.out.println("\nDigite o numero que deseja fatorar: ");
      int n = entrada.nextInt();

      long fatorial = 1;

      if (n == 0){
        System.out.println("O fatorial de 0 é 1.");
      }else{
        for (int i = n; i >+ 1; i--){
          fatorial = fatorial * i;
        }

        System.out.println("O fatorial de " +n+ " é: " +fatorial);
      }

  }
}
