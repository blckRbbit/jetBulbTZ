import converters.NotationConverter;
import excrptions.InvalidValueException;

public class Main {
    private static final NotationConverter converter = new NotationConverter();
    public static void main(String[] args) {
        try {
            System.out.println(converter.toArabic(null));
        } catch (InvalidValueException e) {
            e.printStackTrace();
        }
    }
}
