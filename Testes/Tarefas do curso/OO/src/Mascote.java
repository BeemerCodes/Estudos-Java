import java.util.Scanner;

import entities.Pet;

public class Mascote {
  public static void main(String[] args) {

    Scanner entrada = new Scanner(System.in);

    int escolha = 10;

    Pet dados;
    dados = new Pet();

    System.out.println("Olá humano, eu sou o...");
    System.out.println("\nDigite o nome que deseja dar ao seu novo amiguinho: ");
    dados.nomePet = entrada.next();

    System.out.println("\n" + dados.nomePet + " isso! agora está melhor ^^ ");
    System.out.println("Como posso te chamar? ");
    dados.nomeDono = entrada.next();

    
    System.out.println(dados.nomeDono + ", é muito dificil para mim ficar sozinho, ainda bem que chegou, já imaginou deixar um pobre... hãn...");
    System.out.println("\nDigite a especie de seu novo amiguinho: ");
    dados.tipo = entrada.next();

    System.out.println("Aproveitando que estamos aqui, porque não me conta mais sobre você?");
    System.out.println("Qual sua idade?");
    dados.idade = entrada.nextInt();

    System.out.println("E qual seu sexo? ");
    dados.sexo = entrada.next();

    System.out.println("\nLegal! eu sou um " + dados.tipo + "!");
    System.out.println("Bom, agora que estamos juntos podemos fazer muitas coisas divertidas, por qual quer começar?");

    while (escolha != 0){
      System.out.println("\nSelecione uma opção: ");
      System.out.println("1- Brincar (consome 10% da energia de "+ dados.nomePet +")");
      System.out.println("2- Comer (recupera 20% da energia de "+ dados.nomePet +")");
      System.out.println("3-  Exibe detalhes sobre "+dados.nomePet);
      System.out.println("4- Exibe todas as informações sobre "+dados.nomePet);
      System.out.println("5- Exibe detalhes do seu perfil");
      System.out.println("0- Sair");
      escolha = entrada.nextInt();

      switch (escolha) {
        case 1:
          dados.limparTerminal();
          dados.brincar();
          break;
        case 2:
          dados.limparTerminal();
          dados.alimentar();
          break;
        case 3:
          dados.infoPet();
          break;
        case 4:
          dados.status();
          break;
          case 5:
          dados.infoDono();
          break;
        case 0:
          System.out.println("\nAté logo humano...");
          break;
        default:
          System.out.println("\nOpção inválida. Tente novamente.");
          break;
      }
      if (escolha == 0) {
        break;
      }
        
      }
    }
  }
