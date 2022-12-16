import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final static int[] DISCOUNTS = new int[]{3, 5, 7, 10, 15};
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static final List<Product> products = new ArrayList<>();

    public static void main(String[] args) {

        double price;
        int quantity;
        double total;
        int discount = 0;

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

        System.out.println("Entrez le code votre pays");
        countryCode = scanner.nextLine();

        System.out.println("Avez-vous un code de réduction");
        if (scanner.nextLine().equalsIgnoreCase("oui")) {
            System.out.println("Sélectionner le pourcentage de votre réduction");
            displayDiscounts();
            discount = setDiscount(scanner.nextInt());
        }

        discount += getDiscountRate(totalWithoutTaxes());
        double totalDiscount = getDiscount(totalWithoutTaxes(), discount);
        double totalWithDiscount = totalWithoutTaxes() - totalDiscount;
        double taxes = applyTVA(total, countryCode);
        double totalWithTaxes = total + taxes;

        products.forEach(Product::createLine);
        System.out.println("----------------------------");
        System.out.println("Prix HT " + decimalFormat.format(totalWithDiscount));
        System.out.println("Prix TTC : " + decimalFormat.format(totalWithTaxes));
        System.out.println("Réduction : " + discount + "€");
        System.out.println("TVA : " + decimalFormat.format(taxes));
    }

    private static double applyTVA(double price, String countryCode) {
        Tva discount = Tva.valueOf_(countryCode.toUpperCase());
        int tva = 0;

        switch (discount) {
            case BE -> tva = Tva.BE.getTva();
            case DE -> tva = Tva.DE.getTva();
            case ES -> tva = Tva.ES.getTva();
            case FR -> tva = Tva.FR.getTva();
        }
        return (price / 100) * tva;
    }

    private static int setDiscount(int choice) {

        switch (choice) {
            case 1 -> {
                return DISCOUNTS[0];
            }
            case 2 -> {
                return DISCOUNTS[1];
            }
            case 3 -> {
                return DISCOUNTS[2];
            }
            case 4 -> {
                return DISCOUNTS[3];
            }
            case 5 -> {
                return DISCOUNTS[4];
            }
            default -> {return 0;}
        }
    }

    private static double getDiscountRate(double price){
        int quantity = getQuantity();
        double discountRate = 0;
        if(quantity > 1000 && quantity < 5000){
            discountRate =  getDiscount(price, DISCOUNTS[0]);
        }
        else if(quantity > 5000 && quantity < 7000){
            discountRate =  getDiscount(price, DISCOUNTS[1]);
        }
        else if(quantity > 7000 && quantity < 10000){
            discountRate =  getDiscount(price, DISCOUNTS[2]);
        }
        else if(quantity > 10000 && quantity < 15000){
            discountRate =  getDiscount(price, DISCOUNTS[3]);
        }
        else if(quantity > 15000){
            discountRate =  getDiscount(price, DISCOUNTS[4]);
        }
        return discountRate;
    }

    private static int getQuantity(){
        return products.stream().mapToInt(Product::getQuantity).sum();
    }

    private static double getDiscount(double price, int discount) {
        return discount * price / 100;
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
