
import java.util.Scanner;

public class DuracaoDoJogo {
  public static void main(String[] args) {

      Scanner entrada = new Scanner(System.in);

      System.out.println("Digite o horario de inicio: ");
      int value1 = entrada.nextInt();

      System.out.println("Digite o horario de encerramento: ");
      int value2 = entrada.nextInt();

      if (value1 < value2){
        System.out.println("O jogo durou " + (value2 - value1) + " horas.");
      } else {
        System.out.println("O jogo durou " + (24 - value1 + value2) + " horas.");
      }

  }
}
