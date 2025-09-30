import java.util.Scanner;

import entities.Aumento;

public class AumentodeSalario {
  public static void main(String[] args) {
      Scanner entrada = new Scanner(System.in);

      Aumento dados;
      dados = new Aumento();

      System.out.println("Digite o nome do funcionario: ");
      dados.nome = entrada.next();

      System.out.println("Digite o salário bruto do colaborador " + dados.nome + ": ");
      dados.salario = entrada.nextDouble();

      System.out.println("Digite o desconto sobre o salário: ");
      dados.imposto = entrada.nextDouble();

      System.out.println("Dados do colaborador: Nome: " + dados.nome + ", salário: R$: " + dados.salarioLiquido());

      System.out.println("Qual a porcentagem de aumento desejada? ");
      dados.porcentagem = entrada.nextDouble();

      System.out.println("Valor atualizado: Nome: " + dados.nome + ", salário atualizado: R$:" + dados.aumento());
  }
}
