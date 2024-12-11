
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int i = 96000;
        for (double p = 0.2; p <= 0.4; p += 0.05) {
            System.out.printf("Вероятность: %.2f Энтропия : %.2f%%%n", p, EntropyCalculator.calculateEntropy(p));
            for (int n = 2; n <= 4; n++) {
                System.out.printf("Размер блока: %d ", n);
                String binaryString = RandomBinaryString.generateBinaryString(i, p);
                Map<String, Double> symbolProbabilities = BlocksGenerator.generateSymbols(n, p);

                Map<String, String> codes = ShannonFano.generateShannonFanoCodes(symbolProbabilities);
                String encodedString = BinaryStringEncoder.encode(binaryString, codes, n);
                System.out.printf("Шеннон-Фано: %.2f%% ", EncodingEfficiencyCalculator.calculateEfficiency(binaryString, encodedString));

                codes = Huffman.generateHuffmanCodes(symbolProbabilities);
                encodedString = BinaryStringEncoder.encode(binaryString, codes, n);
                System.out.printf("Хаффман: %.2f%%%n", EncodingEfficiencyCalculator.calculateEfficiency(binaryString, encodedString));
            }
        }
    }
}