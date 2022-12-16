import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ProductTest {

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
    @DisplayName("Affiche le label de l'article")
    void getLabel() {
        Product product = products.stream().findFirst().get();
        Assertions.assertEquals("Tomate", product.getLabel());
    }

    @Test
    @DisplayName("Affiche le prix de l'article")
    void getPrice() {
        Product product = products.stream().findFirst().get();
        Assertions.assertEquals(1.99, product.getPrice());
    }

    @Test
    @DisplayName("Affiche la quantité d'atricle(s)")
    void getQuantity() {
        Product product = products.stream().findFirst().get();
        Assertions.assertEquals(10, product.getQuantity());
    }

    @Test
    @DisplayName("Affiche la somme totale des article")
    void sum() {
        double total = 0;
        for (Product product : products) {
            total += product.sum();
        }
        Assertions.assertEquals(total, 75.47);
    }

}