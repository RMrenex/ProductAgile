import java.util.HashMap;
import java.util.Map;

public enum Tva {

    FR(20, "FR"), BE(21, "BE"), DE(19, "DE"), ES(21, "ES");

    private int tva;
    private String code;


    private static final Map<String, Tva> TVA = new HashMap<>();

    static {
        for (Tva discount : values()) {
            TVA.put(discount.getCode(), discount);
        }
    }


    Tva(int tva, String code) {
        this.tva = tva;
        this.code = code;
    }

    public static Tva valueOf_(String name){
        return TVA.get(name);
    }


    public int getTva() {
        return tva;
    }

    public String getCode() {
        return code;
    }
}
