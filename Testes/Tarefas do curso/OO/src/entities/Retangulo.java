package entities;

public class Retangulo {
  public double a;
  public double b;

  public double area(){
     return a * b;
  }

  public double perimetro(){
    return 2 * (a + b);
  }

  public double diagonal(){
     return Math.sqrt(a * a + b * b );
  }
}
