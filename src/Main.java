import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final static int DISCOUNTS[] = new int[]{3, 5, 7, 10, 15};
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {

        double price;
        int quantity;
        double total;
        int discount = -1;

        String productName;
        String countryCode;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Code TVA :");
        displayDiscounts();

        do {

            System.out.println("\nSaisir le nom de l'article");
            productName = scanner.nextLine();

            while (!productName.matches(".*[a-z].*")) {
                System.out.println("Le nom de l'article doit contenir uniquement des lettres");
                System.out.println("Saisir à nouveau le nom de l'article");
                productName = scanner.nextLine();
            }

            try {
                System.out.println("Saisir un prix");
                price = scanner.nextDouble();
            } catch (InputMismatchException exception) {
                System.out.println("Format du prix incorrect");
                return;
            }

            try {
                System.out.println("Saisir une qantité");
                quantity = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Format du prix incorrect");
                return;
            }

            products.add(new Product(productName, price, quantity));
            System.out.println("Produit ajouté au panier");

            total = totalWithoutTaxes();
            System.out.println("Prix total : " + decimalFormat.format(total) + " €");

            System.out.println("Voulez vous ajouter un autre article dans votre panier ? (oui/non)");
            String response = scanner.nextLine();

        } while (!scanner.nextLine().equals("non"));

        System.out.println("Saisir le montant de la TVA");
        double tva =( price / 100) * scanner.nextInt();
        double totalWithTaxes = total + tva;

        System.out.println("Total TTC " + decimalFormat.format(totalWithTaxes));
    }

    private static double totalWithoutTaxes() {
        double total = 0;
        for (Product product : products) {
            total += product.sum();
        }
        return total;
    }

    private static void displayDiscounts() {
        for (int i = 0; i < DISCOUNTS.length; i++) {
            System.out.println(i + 1 + " - " + DISCOUNTS[i] + "%");
        }
    }
}
