package per.november.generator;

import java.io.IOException;

public class P1719Generator {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                sb.append("5").append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
