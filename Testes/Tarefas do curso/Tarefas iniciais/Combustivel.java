
import java.util.Scanner;

public class Combustivel {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      int opcao = 0, alcool = 0, gasolina = 0, diesel = 0;

      while (opcao != 4) {

        System.out.println("Chorume Autoposto");
        System.out.println("\nAbasteça por sua conta em risco...");

        System.out.println("1.Álcool\n2.Gasolina\n3.Diesel\n4.Fim");

        System.out.println("\nDigite a opção desejada: ");
        opcao = entrada.nextInt();

        switch (opcao) {
            case 1 -> {
                ++alcool;
                continue;
            }
            case 2 -> {
                ++gasolina;
                continue;
            }
            case 3 -> {
                ++diesel;
                continue;
            }
            case 4 -> System.out.println("\nObrigado por confiar em nosso serviço.");
            
            default -> {
                System.out.println("Digite uma opção valida.");
                continue;
            }
        }

        System.out.println("\nAlcool: " + alcool + "\nGasolina: " + gasolina + "\nDiesel: " + diesel +"\n");

      }
  }
}
