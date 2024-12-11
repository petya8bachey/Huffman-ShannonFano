import java.util.Map;

public class BinaryStringEncoder {
    public static String encode(String binaryString, Map<String, String> encodingMap, int blockSize) {
        StringBuilder encoded = new StringBuilder();

        // Проходим по строке с шагом blockSize
        for (int i = 0; i < binaryString.length(); i += blockSize) {
            // Извлекаем блок фиксированного размера
            String block = binaryString.substring(i, Math.min(i + blockSize, binaryString.length()));
            // Добавляем код из карты или "?" для неопознанных блоков
            encoded.append(encodingMap.getOrDefault(block, "?"));
        }

        return encoded.toString();
    }
}

