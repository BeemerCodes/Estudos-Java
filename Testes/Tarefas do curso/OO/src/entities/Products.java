package entities;

public class Products {
  public String name;
  public double price;
  public int quantity;

  public double totalValueInStock(){
    return price * quantity;
  }

  public void addQuantity(int quantity){
    this.quantity += quantity; // Acessa o atributo da classe e atribui o valor recebido como paramentro 
  }

  public void removeProducts(int quantity){
    this.quantity -= quantity;
  }
}
