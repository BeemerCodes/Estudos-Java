import java.util.Scanner;

import entities.Products;

public class ProductsUse {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Products product = new Products();
        int adicionarItens, removerItens;

        System.out.println("\n- Insira as informações do produto -");
        System.out.print("Nome: ");
        product.name = entrada.nextLine();

        System.out.print("Valor: ");
        product.price = entrada.nextDouble();

        System.out.print("Quantidade: ");
        product.quantity = entrada.nextInt();

        System.out.println("\nEstoque atual: ");
        System.out.println(product.toString());

        System.out.println("\nEntre com o número de produtos a ser adiconado ao estoque: ");
        adicionarItens = entrada.nextInt();
        product.addQuantity(adicionarItens); 

        System.out.println("Estoque atualizado: ");
        System.out.println(product.toString());

        System.out.println("\n Entre com o número de produtos a ser removido do estoque: ");
        removerItens = entrada.nextInt();
        product.removeProducts(removerItens); 

        System.out.println("Estoque atualizado: ");
        System.out.println(product.toString());

        entrada.close();
    }
}
