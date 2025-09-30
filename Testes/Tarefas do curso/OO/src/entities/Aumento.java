package entities;

public class Aumento {
  public double salario, imposto, salarioLiquido, porcentagem, valorFinal;
  public String nome;

  public double salarioLiquido(){
    salarioLiquido = (salario - imposto);
    return salarioLiquido;
  }

  public double aumento(){
    valorFinal = salarioLiquido * porcentagem / 100;
    return valorFinal + salarioLiquido;
  }
}
