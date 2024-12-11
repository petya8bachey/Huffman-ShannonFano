import java.util.*;

class ShannonFanoNode {
    String symbol;
    double probability;
    StringBuilder code; // Используем StringBuilder для более эффективного добавления строк

    ShannonFanoNode(String symbol, double probability) {
        this.symbol = symbol;
        this.probability = probability;
        this.code = new StringBuilder(); // Инициализируем StringBuilder
    }
}

public class ShannonFano {

    public static Map<String, String> generateShannonFanoCodes(Map<String, Double> symbolProbabilities) {
        List<ShannonFanoNode> messages = new ArrayList<>();

        // Создаем и сортируем список символов и их вероятностей
        symbolProbabilities.forEach((symbol, probability) -> messages.add(new ShannonFanoNode(symbol, probability)));
        messages.sort(Comparator.comparingDouble((ShannonFanoNode node) -> -node.probability));

        // Запускаем алгоритм Шеннона-Фано
        shannonFanoCoding(messages, 0, messages.size() - 1);

        // Формируем результат
        Map<String, String> codes = new HashMap<>();
        for (ShannonFanoNode message : messages) {
            codes.put(message.symbol, message.code.toString()); // Преобразуем StringBuilder в строку
        }

        return codes;
    }

    // Рекурсивная функция для кодирования
    private static void shannonFanoCoding(List<ShannonFanoNode> messages, int start, int end) {
        if (start >= end) return;

        // Ищем точку разделения с минимальной разностью вероятностей
        int splitIndex = findSplitIndex(messages, start, end);

        // Присваиваем коды: верхней части - "0", нижней - "1"
        for (int i = start; i <= splitIndex; i++) {
            messages.get(i).code.append('0'); // Используем append для добавления символов
        }
        for (int i = splitIndex + 1; i <= end; i++) {
            messages.get(i).code.append('1');
        }

        // Рекурсивно продолжаем деление
        shannonFanoCoding(messages, start, splitIndex);
        shannonFanoCoding(messages, splitIndex + 1, end);
    }

    // Поиск индекса для деления с минимальной разностью вероятностей
    private static int findSplitIndex(List<ShannonFanoNode> messages, int start, int end) {
        double total = messages.subList(start, end + 1).stream().mapToDouble(node -> node.probability).sum();
        double sum = 0;

        for (int i = start; i <= end; i++) {
            sum += messages.get(i).probability;
            if (sum >= total / 2) {
                return i;
            }
        }
        return start; // если не нашли точного деления
    }
}
