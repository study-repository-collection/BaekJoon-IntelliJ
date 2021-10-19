package per.jongho.test;

import java.io.IOException;
import java.util.Random;

public class P16932Maker {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 500; j++) {
                stringBuilder.append(new Random().nextInt(2)).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
