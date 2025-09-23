import java.util.Scanner;

import entities.Products;

public class ProductsUse {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Products product = new Products();

        System.out.println("- Insira as informações do produto -");
        System.out.print("Nome: ");
        product.name = entrada.nextLine();

        System.out.print("Valor: ");
        product.price = entrada.nextDouble();

        System.out.print("Quantidade: ");
        product.quantity = entrada.nextInt();

        System.out.println("Estoque atual: ");
        System.out.println("Nome: "+ product.name);
        System.out.println("Preço: "+ product.price);
        System.out.println("Quantidade: "+ product.quantity);
        System.out.println("Valor total em estoque: "+ product.totalValueInStock());
        entrada.close();
    }
}
