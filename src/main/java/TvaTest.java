import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TvaTest {

    @Test
    void valueOf_() {
        Tva fr = Tva.valueOf_("FR");
        Assertions.assertEquals(Tva.FR, fr);
    }

    @Test
    void getTva() {
        Tva fr = Tva.valueOf_("FR");
        Assertions.assertEquals(20, fr.getTva());
    }

    @Test
    void getCode() {
        Tva fr = Tva.valueOf_("FR");
        Assertions.assertEquals("FR", fr.getCode());
    }
}