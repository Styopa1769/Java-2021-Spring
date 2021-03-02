package json;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Вспомогательный класс для работы с Json
 */
public class JsonHelper {
    private final static String DELIMITER = ":";
    public final static String SEPARATOR = ", ";

    private JsonHelper(){}

    public static String toJsonEntry(String property, Number value){
        return toJsonString(property) + DELIMITER + value;
    }

    public static String toJsonEntry(String property, String value){
        return toJsonString(property) + DELIMITER + toJsonString(value);
    }

    public static String toJsonArray(List<? extends JsonSerializable> list){
        return "[" + list.stream().map(JsonSerializable::toJson).collect(Collectors.joining(SEPARATOR)) + "]";
    }

    public static Map<String, String> toPropertiesMap(String json){
        return Stream.of(fromJsonString(json).split(SEPARATOR))
                .collect(Collectors.toMap(
                        entry -> fromJsonString(entry.split(DELIMITER)[0]),
                        entry -> fromJsonString(entry.split(DELIMITER)[1])));
    }

    private static String toJsonString(String property){
        return "\"" + property + "\"";
    }

    private static String fromJsonString(String jsonString){
        return jsonString.substring(1, jsonString.length()-1);
    }
}
