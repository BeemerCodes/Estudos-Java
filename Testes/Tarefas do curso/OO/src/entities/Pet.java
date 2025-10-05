package entities;

public class Pet {
  public String nome, tipo;
  public int energia = 100, quantoCome, quantoBrinca;

  public void limparTerminal() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

  public void infoPet(){
    System.out.println("Detalhes do seu amiguinho.");
    System.out.println("Nome: " + nome);
    System.out.println("Tipo: " + tipo);
    System.out.println("Energia atual: " + energia + "%");
  }

  public void status(){
    System.out.println("Detalhes de como anda " + nome);
    System.out.println("Nome: " + nome);
    System.out.println("Tipo: " + tipo);
    System.out.println("Energia atual: " + energia + "%");
    System.out.println("Quantas vezes brincou com você: " + quantoBrinca);
    System.out.println("Quantas vezes comeu: " + quantoCome);
  }

  public void brincar(){
    if (energia < 10){
      System.out.println("A energia de " + nome + " está muito baixa para brincar :( ");
    }else {
      energia -= 10;
      quantoBrinca += 1;
      System.out.println("Você brincou com " + nome + " e agora ele está com " + energia + "% de energia.");
    }
  }

  public void alimentar(){
    if(energia >= 81){
      System.out.println("A energia de " + nome + " está cheia, brinque um pouco com ele ;)");
    }else {
      energia += 20;
      quantoCome += 1;
      System.out.println("Você alimentou " + nome + " e sua energia subiu 20%, energia atual: " + energia);
    }
  }
}
