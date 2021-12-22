package per.november.gold;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        final long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));

    }
}
