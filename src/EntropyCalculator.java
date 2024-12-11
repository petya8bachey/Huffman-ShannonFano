public class EntropyCalculator {
    public static double calculateEntropy(double p) {
        double q = 1 - p;
        double entropy = 0.0;
        if (p > 0) {
            entropy -= p * Math.log(p) / Math.log(2); // Условие p > 0 для избежания log(0)
        }
        if (q > 0) {
            entropy -= q * Math.log(q) / Math.log(2); // Условие q > 0 для избежания log(0)
        }
        return entropy * 100;
    }
}
