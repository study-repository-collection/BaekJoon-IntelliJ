package per.november.platinum;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.in;

public class P3860Generator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int T = 500;
        while (T-- > 0) {
            int W = random.nextInt(30) + 1;
            int H = random.nextInt(30) + 1;
            sb.append(W).append(" ").append(H).append("\n");
            int G = 1;
            if (W * H == 1) {
            } else {
                G = random.nextInt(W * H - 1);
            }

            sb.append(G).append("\n");
            ArrayList<Point> Graves = new ArrayList<>();
            boolean[][] visited = new boolean[H][W];
            for (int i = 0; i < G; ) {
                int x = random.nextInt(W);
                int y = random.nextInt(H);
                if (!visited[y][x]) {
                    i++;
                    visited[y][x] = true;
                    Graves.add(new Point(x, y));
                }
            }
            for (Point point : Graves) {
                sb.append(point.x).append(" ").append(point.y).append("\n");
            }
            int HoleNumber = 0;
            if ((W * H - 1 - G) > 0) {
                HoleNumber = random.nextInt((W * H - 1 - G)) / 2;
            }
            sb.append(HoleNumber).append("\n");
            for (int j = 0; j < HoleNumber; ) {
                int x = random.nextInt(W);
                int y = random.nextInt(H);
                int x1 = random.nextInt(W);
                int y1 = random.nextInt(H);
                if (!visited[y][x] && !visited[y1][x1]) {
                    visited[y][x] = true;
                    visited[y1][x1] = true;
                    int length = random.nextInt(20000) - 10000;
                    sb.append(x).append(" ").append(y).append(" ").append(x1).append(" ").append(y1).append(" ").append(length).append("\n");
                    j++;
                }
            }
        }
        sb.append(0).append(" ").append(0);
        System.out.println(sb);
    }
}
