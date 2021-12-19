package converters;

import excrptions.InvalidValueException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NotationConverter {
    
    private final Map<String, Integer> romanToArabicMap = new HashMap<>(
            Map.of(
                    "I", 1,
                    "V", 5,
                    "X", 10,
                    "L", 50,
                    "C", 100,
                    "D", 500,
                    "M", 1000));
    
    public int toArabic(String romanNotation) throws InvalidValueException {
        if (romanNotation == null) {
            throw new InvalidValueException("String must contain only valid roman numerals [I, V, X, L, C, D, M].");
        }
        // romanNotation = romanNotation.toUpperCase(); если хотим, чтобы можно было вводть и пропсные буквы
        int result;
        if (romanNumberIsValid(romanNotation)) {
            result = romanToArabicMap.get(Character.toString(romanNotation.charAt(0)));
            List<Integer> symbols = romanNotation
                    .chars().mapToObj(Character::toString)
                    .map(romanToArabicMap::get)
                    .collect(Collectors.toList());
            for (int i = 0; i < symbols.size() - 1; i++) {
                if (symbols.get(i) >= symbols.get(i + 1)) {
                    result += symbols.get(i+1);
                }
                if (symbols.get(i) < symbols.get(i + 1)) {
                    result += symbols.get(i+1) - symbols.get(i)*2;
                }
            }
            return result;
        } else {
            throw new InvalidValueException("String must contain only valid roman numerals [I, V, X, L, C, D, M].");
        }
    }
    
    public boolean romanNumberIsValid(String input) {
        /* кроме валидации конкретных символов, добавил валидацию правльного построения римского числа,
        например 490 правильно не XD, а CDXC. Текст выброшенного исключения не менял, чтобы прошел валдацию,
        согласно ТЗ
        */
        return Pattern.matches("[IVXLCDM]+", input) &&
                !Pattern.matches("I{4,}|X{4,}|C{4,}|M{4,}|V{2,}|L{2,}|D{2,}|IL+|IC+|ID+|IM+|XD+|XM+", input);
    }
}
