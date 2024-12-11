import java.util.Random;

public class RandomBinaryString {
    public static String generateBinaryString(int length, double probability) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int j = 0; j < length; j++) {
            if (random.nextDouble() < probability) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }

        return sb.toString();
    }
}
