import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TvaTest {

    @Test
    @DisplayName("Récupère un objet tva en fonction du code pays")
    void valueOf_() {
        Tva fr = Tva.valueOf_("FR");
        Assertions.assertEquals(Tva.FR, fr);
    }

    @Test
    @DisplayName("Récupère le taux de tva en fonction du code pays")
    void getTva() {
        Tva fr = Tva.valueOf_("FR");
        Assertions.assertEquals(20, fr.getTva());
    }

    @Test
    @DisplayName("Récupère le code pays en fonction du code pays")
    void getCode() {
        Tva fr = Tva.valueOf_("FR");
        Assertions.assertEquals("FR", fr.getCode());
    }
}