import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

class HuffmanNode {
    final String symbol; // Сделаем поле финальным для неизменяемости
    final double probability;
    HuffmanNode left, right;

    HuffmanNode(String symbol, double probability) {
        this.symbol = symbol;
        this.probability = probability;
    }
}

public class Huffman {
    public static Map<String, String> huffmanCodes = new HashMap<>();

    public static Map<String, String> generateHuffmanCodes(Map<String, Double> symbolProbabilities) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.probability));

        // Создаем очередь с приоритетом для каждого символа
        symbolProbabilities.forEach((symbol, probability) ->
                pq.add(new HuffmanNode(symbol, probability))
        );

        // Построение дерева Хаффмана
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            // Создаем новый узел с объединённой вероятностью
            HuffmanNode newNode = new HuffmanNode(null, left.probability + right.probability);
            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        // Корневой узел дерева
        HuffmanNode root = pq.poll();
        generateHuffmanCode(root, ""); // Генерация кодов для всех символов

        return huffmanCodes;
    }

    // Рекурсивное построение кодов
    private static void generateHuffmanCode(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }

        // Если узел является листом, сохраняем код для символа
        if (node.symbol != null) {
            huffmanCodes.put(node.symbol, code);
        }

        // Рекурсивно переходим к левому и правому поддереву
        generateHuffmanCode(node.left, "%s0".formatted(code));
        generateHuffmanCode(node.right, "%s1".formatted(code));
    }
}
