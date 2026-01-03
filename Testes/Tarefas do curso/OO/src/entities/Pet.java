package entities;

public class Pet {
  public String nomePet, nomeDono, sexo, tipo;
  public int energia = 100, quantoCome, quantoBrinca, xp, idade;

  public void limparTerminal() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

  public void infoPet(){
    System.out.println("Detalhes do seu amiguinho.");
    System.out.println("Nome: " + nomePet);
    System.out.println("Tipo: " + tipo);
    System.out.println("Energia atual: " + energia + "%");
  }

  public void infoDono(){
    System.out.println("Detalhes do cuidador: ");
    System.out.println("Nome: "+ nomeDono);
    System.out.println("Idade: "+ idade);
    System.out.println("Sexo: "+ sexo);
    System.out.println("Mascote principal: "+ nomePet);
    System.err.println("Experiencia: "+ xp);
  }

  public void status(){
    System.out.println("Detalhes de como anda " + nomePet);
    System.out.println("Nome: " + nomePet);
    System.out.println("Tipo: " + tipo);
    System.out.println("Energia atual: " + energia + "%");
    System.out.println("Quantas vezes brincou com você: " + quantoBrinca);
    System.out.println("Quantas vezes comeu: " + quantoCome);
  }

  public void brincar(){
    if (energia < 10){
      System.out.println("A energia de " + nomePet + " está muito baixa para brincar :( ");
    }else {
      energia -= 10;
      quantoBrinca += 1;
      xp += 5;
      System.out.println("Você brincou com " + nomePet + " e agora ele está com " + energia + "% de energia.");
    }
  }

  public void alimentar(){
    if(energia >= 81){
      System.out.println("A energia de " + nomePet + " está cheia, brinque um pouco com ele ;)");
    }else {
      energia += 20;
      quantoCome += 1;
      xp += 7;
      System.out.println("Você alimentou " + nomePet + " e sua energia subiu 20%, energia atual: " + energia);
    }
  }
}
