import java.util.Scanner;

public class Fudidometro {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        String nome, ajuda;
        int age, date, opcao;
        double salario;;

        System.out.println("Fudidometro\n");
        System.out.println("Após nossa segura coleta de dados vamos avaliar sua condição atual, vamos começar? \n\n");
        System.out.println("Qual seu nome?");
        nome = entrada.next();

        System.out.println("Qual seu ano de nascimento?");
        date = entrada.nextInt();
        age = (2025 - date);

        System.out.printf("%s, qual sua renda atualmente?%n", nome);
        salario = entrada.nextDouble();

        if (salario < 6000) {
            System.out.printf("Seu nome é: %s%nVocê tem %d anos%nAtualmente recebe %.2f, infelizmente isso te faz um CLT :(\n", nome, age, salario);
            System.out.println("Gostaria de ajuda para sair dessa situação? S/N: ");
            ajuda = entrada.next();

            if (ajuda.equalsIgnoreCase("S")) {
                System.out.println("Escolha a opção que melhor te ajudaria hoje:\n");
                System.out.println("Opção 1: Trabalhar o dobro para receber o dobro.");
                System.out.println("Opção 2: Se profissionalizar para não ser explorado.");
                System.out.println("Opção 3: Bug instantaneo no tigre $$$.");
                System.out.println("Opção 4: Fazer programas...  ");
                System.out.println("Opção 5: Sair.");

                opcao = entrada.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.printf("Se ta no caminho %s, pena que não passa dos 30 assim !⑈ˆ~ˆ!⑈\n", nome);
                        break;
                    case 2:
                        System.out.println("Ótima escolha, que tal aprender programar algo útil? ヽ(͡◕ ͜ʖ ͡◕)ﾉ\n");
                        break;
    
                    case 3:
                        System.out.printf("Sabia que era burro, mas meu deus %s!  ( ರ Ĺ̯ ರೃ )\n", nome);
                        break;
    
                    case 4:
                        System.out.println("Gostei, tá cobrando quan... brincadeira, já já faz até código em Java. (ᅌᴗᅌ* )\n");
                        break;
    
                    case 5:
                        System.out.printf("Vai lá %s, quem sabe na próxima o ChatGPT te ajuda então babaca.\n", nome);
                        break;
                
                    default:
                        System.out.println("Pelo menos digita algo certo. \n");
                        break;
                }
            }
            

        }else {
            System.out.printf("Seu nome é: %s%nVocê tem %d anos%nAtualmente recebe %.2f, sorte a sua não ser CLT kkk%n", nome, age, salario);
        }

        
        entrada.close();
    }
}