package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P12886 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer info = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(info.nextToken());
        int B = Integer.parseInt(info.nextToken());
        int C = Integer.parseInt(info.nextToken());
        if ((A + B + C) % 3 != 0) {
            System.out.println(0);
        } else {
            if (solution(A, B, A + B + C)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    static boolean solution(int A, int B, int sum) {
        int max = Math.max(A, B);
        boolean[][] visited = new boolean[1501][1501];
        visited[A][B] = true;
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(A, B));

        while (!points.isEmpty()) {
            Point point = points.poll();
            int[] arr = {point.x, point.y, sum - (point.x + point.y)};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i] > arr[j] && !visited[arr[i] - arr[j]][arr[j] * 2]) {
                        visited[arr[i] - arr[j]][arr[j] * 2] = true;
                        points.add(new Point(arr[i] - arr[j], arr[j] * 2));
                    }
                }
            }
        }

        return visited[sum / 3][sum / 3];
    }

}
