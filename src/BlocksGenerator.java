import java.util.HashMap;
import java.util.Map;

public class BlocksGenerator {

    // Метод для генерации символов и их вероятностей на основе вероятности p и размера блока n
    public static Map<String, Double> generateSymbols(int n, double p) {
        Map<String, Double> symbolProbabilities = new HashMap<>();
        double q = 1 - p;  // Предварительное вычисление (1 - p)

        // Генерируем все возможные комбинации символов длины n (строки из 0 и 1)
        generateBinarySequences(n, "", symbolProbabilities, p, q);

        return symbolProbabilities;
    }

    // Рекурсивная функция для генерации бинарных последовательностей
    private static void generateBinarySequences(int n, String prefix, Map<String, Double> symbolProbabilities, double p, double q) {
        if (n == 0) {
            // Подсчитываем количество единиц в последовательности
            int onesCount = (int) prefix.chars().filter(ch -> ch == '1').count();
            // Рассчитываем вероятность символа
            double probability = Math.pow(p, onesCount) * Math.pow(q, prefix.length() - onesCount);
            // Добавляем символ и его вероятность в карту
            symbolProbabilities.put(prefix, probability);
        } else {
            // Рекурсивно добавляем '0' и '1' к префиксу
            generateBinarySequences(n - 1, "%s0".formatted(prefix), symbolProbabilities, p, q);
            generateBinarySequences(n - 1, "%s1".formatted(prefix), symbolProbabilities, p, q);
        }
    }
}
