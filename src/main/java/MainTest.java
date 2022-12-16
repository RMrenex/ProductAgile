import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MainTest {

    static List<Product> products = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        products = List.of(
                new Product("Tomate", 1.99, 10),
                new Product("Banane", 2.40, 6),
                new Product("Pomme", 0.99, 15),
                new Product("Poire", 3.29, 8)
        );
    }

    @Test
    @DisplayName("Demande et applique le taux de tva au montant total")
    void applyTVA(){
        double totalWithTVA = Main.applyTVA(150.56, "FR");
        Assertions.assertEquals("30,11", Main.decimalFormat.format(totalWithTVA));
    }

    @Test
    @DisplayName("Demande et applique le montant de la réduction donné")
    void setDiscount(){
        int discount =  Main.setDiscount(2);
        Assertions.assertEquals(5, discount);
    }

    @Test
    @DisplayName("Détermine si l'utilisateur et éligible à une promotion automatique en fonction du nombre total d'article")
    void getDiscountRate(){
        double discountRate = Main.getDiscountRateForTest(450.00, 6500);
        Assertions.assertEquals(22.5, discountRate);
    }

    @Test
    @DisplayName("Calcul la quantité total d'article")
    void getQuantity(){
        int quantity = products.stream().mapToInt(Product::getQuantity).sum();
        Assertions.assertEquals(39, quantity);
    }

    @Test
    @DisplayName("Calul le montant de la réduction en €")
    void getDiscount(){
        double discount = Main.getDiscount(64.23, 10);
        Assertions.assertEquals("6,42", Main.decimalFormat.format(discount));
    }

    @Test
    @DisplayName("Demande la désignation de l'article à l'utilisateur")
    void getUserLabelEntry(){
        String input = "Tomate";
        InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        String expected = scanner.next();

        Assertions.assertEquals(input, expected);
        System.setIn(inputStream);
        scanner.close();
    }

    @Test
    @DisplayName("Demande le prix de l'article à l'utilisateur")
    void getUserPriceEntry(){
        String input = "1,99";
        InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        String expected = scanner.next();

        Assertions.assertEquals(input, expected);
        System.setIn(inputStream);
        scanner.close();
    }

    @Test
    @DisplayName("Demande la quantité de l'article à l'utilisateur")
    void getUserQuantityEntry(){
        String input = "25";
        InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        String expected = scanner.next();

        Assertions.assertEquals(input, expected);
        System.setIn(inputStream);
        scanner.close();
    }
}