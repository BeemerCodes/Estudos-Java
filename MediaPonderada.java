
import java.util.Locale;
import java.util.Scanner;

public class MediaPonderada {
  public static void main(String[] args) {
      Locale.setDefault(Locale.US);
      Scanner entrada = new Scanner(System.in);

      System.out.println("Digite a quantidade de números que deseja calcular: ");
      int quantidade = entrada.nextInt();

      for (int i=0; i < quantidade; i++){

        System.out.println("\nDigite o primeiro número: ");
        double valorA = entrada.nextDouble();

        System.out.println("\nDigite o segundo número: ");
        double valorB = entrada.nextDouble();
        
        System.out.println("\nDigite o terceiro número: ");
        double valorC = entrada.nextDouble();

        double media = (valorA * 2 + valorB * 3 + valorC * 5) / 10;

        System.out.printf("\nA média ponderada é de: %.1f", media);
      }

      
  }
}
