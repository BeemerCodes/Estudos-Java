import java.util.Scanner;

import entities.Retangulo;


public class AreadoRetangulo {
  public static void main(String[] args) {

    Scanner entrada = new Scanner(System.in);
    double area, perimetro, diagonal;

    Retangulo valor;
    valor = new Retangulo();

    System.out.println("Digite primeiro a altura do retângulo e, em seguida, a largura:");
    valor.a = entrada.nextDouble();
    valor.b = entrada.nextDouble();

    area = valor.area();
    perimetro = valor.perimetro();
    diagonal = valor.diagonal();
    
    System.out.println("Área de: " + area);
    System.out.println("Perímetro de: " + perimetro);
    System.out.println("Diagonal de: " + diagonal);
  }
}
