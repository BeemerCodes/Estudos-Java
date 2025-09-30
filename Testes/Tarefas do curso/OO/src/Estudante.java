import java.util.Scanner;

import entities.Status;

public class Estudante {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      Status dados;
      dados = new Status();

      System.out.println("Digite o nome do aluno: ");
       dados.nome = entrada.next();

      System.out.println("Insira as notas de " + dados.nome + " a baixo. (O primeiro trimestre tem nota maxima de 30 e o segundo e terceiro de 35 pontos.)");
      dados.a = entrada.nextDouble();
      dados.b = entrada.nextDouble();
      dados.c = entrada.nextDouble();

      if(dados.a > 30 || dados.b > 35 || dados.c > 35){
        dados.errorException();
      }else {
        System.out.println("\nNota final de " + dados.nome + ": " + dados.notaFinal());

      if (dados.notaFinal() < 60) {
        System.out.println("Para ser aprovado eram necessario mais " + dados.missing() + " Pontos.");
      }

      System.out.println("Status do aluno: " + dados.statusAluno());
      }
      

  }
}
