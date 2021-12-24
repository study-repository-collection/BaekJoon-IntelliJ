package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P2655 {
    static Brick[] bricks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int N = Integer.parseInt(br.readLine());
        bricks = new Brick[N + 1];
        bricks[0] = new Brick(0, 100000000, 0, 0);
        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(input.nextToken());
            int height = Integer.parseInt(input.nextToken());
            int weight = Integer.parseInt(input.nextToken());
            bricks[i] = new Brick(i, width, height, weight);
        }
        Arrays.sort(bricks, (o1, o2) -> Integer.compare(o2.width, o1.width));
        solution(N);
    }

    static void solution(int N) {
        int[] dp = new int[N + 1];
        int[] parent = new int[N + 1];
        StringBuilder sb = new StringBuilder();
        dp[1] = bricks[1].height;
        for (int i = 2; i <= N; i++) {
            Brick thisBrick = bricks[i];
            dp[i] = thisBrick.height;
            for (int j = 1; j < i; j++) {
                if (bricks[j].weight > thisBrick.weight) {
                    if (dp[i] < dp[j] + thisBrick.height) {
                        dp[i] = dp[j] + thisBrick.height;
                        parent[i] = j;
                    }
                }
            }
        }
        int max = -1;
        int maxIndex = 0;
        for (int i = 1; i <= N; i++) {
            if (max < dp[i]) {
                max = dp[i];
                maxIndex = i;
            }
        }
        Queue<Integer> numbers = new LinkedList<>();
        numbers.add(bricks[maxIndex].number);
        int nextParent = parent[maxIndex];
        while (nextParent != 0) {
            numbers.add(bricks[nextParent].number);
            nextParent = parent[nextParent];
        }
        sb.append(numbers.size()).append("\n");
        while (!numbers.isEmpty()) {
            sb.append(numbers.poll()).append("\n");
        }
        System.out.println(sb);
    }

    static class Brick {
        final int number;
        final int width;
        final int height;
        final int weight;

        public Brick(int number, int width, int height, int weight) {
            this.number = number;
            this.width = width;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Brick{" +
                    "number=" + number +
                    ", width=" + width +
                    ", height=" + height +
                    ", weight=" + weight +
                    '}';
        }
    }

}
