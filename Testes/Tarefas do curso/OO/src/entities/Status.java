package entities;

public class Status {
  public double a, b, c, nota;
  public String status, nome;

  public double notaFinal(){
    if(a > 30 || b > 35 || c > 35){
      return 0.0;
    }

    nota = a + b + c;
    return nota; 
  }
  public void errorException(){
    System.out.println("Os dados inseridos excedem a nota maxima, tente novamente.");
  }

  public String statusAluno(){
    if(nota >= 60){
      status = "Aprovado";
    }else {
      status = "Reprovado";
    }
    return status;
  }

  public double missing(){
    return 60 - nota;
  }
}
