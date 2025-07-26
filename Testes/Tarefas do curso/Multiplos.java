import java.util.Scanner;

public class Multiplos {

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    
    System.err.println("Digite o primeiro número: ");
    int value1 = entrada.nextInt();

    System.err.println("Digite o segundo número: ");
    int value2 = entrada.nextInt();
    
    if (value1 % value2 == 0 || value2 % value1 == 0) {
      System.out.println("É multiplo.");
    } else {
      System.out.println("Não é multiplo.");
    }

    entrada.close();
  }
}
