public class EncodingEfficiencyCalculator {

    // Метод для вычисления эффективности кодирования
    public static double calculateEfficiency(String original, String encoded) {
        // Длина исходной и закодированной строк
        int originalLength = original.length();
        int encodedLength = encoded.length();

        // Вычисление эффективности кодирования
        return (double) encodedLength / originalLength * 100;
    }
}
