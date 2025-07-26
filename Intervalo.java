
import java.util.Scanner;

public class Intervalo {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      System.out.println("Digite a quantidade que deseja calcular o intervalo: ");
      int n = entrada.nextInt(), x, dentro=0, fora=0;

      for (int i=0; i < n; i++){

        System.out.println("Digite um valor: ");
        x = entrada.nextInt();

        if (x >= 10 && x <= 20){
          ++dentro;
        }else {
          ++fora;
        }
      }

      System.out.println("\nDentro do intervalo: " +dentro+ "\nFora do intervalo: " +fora+ "\n");
  }
}
