public class Product {

    private String label;
    private double price;
    private int quantity;

    public Product(String label, double price, int quantity) {
        this.label = label;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double sum(){
        return this.price * quantity;
    }

    public void createLine(){
        System.out.println("Nom : " + label + " Quantité : " + " x" + quantity + " Prix unitaire : " + price + "€ Prix total " + sum() + " €");
    }
}
