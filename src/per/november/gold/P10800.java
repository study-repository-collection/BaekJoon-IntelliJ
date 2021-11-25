package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P10800 {
    static PriorityQueue<Ball> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.size));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        //크기가 작고 색이 다른 공을 얻어 그 공의 크기만큼 점수를 얻을 수 있다.
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(input.nextToken());
            int size = Integer.parseInt(input.nextToken());
            priorityQueue.add(new Ball(i + 1, color, size));
        }
        solution(N);
    }

    static void solution(int N) {
        int[] answer = new int[N + 1];
        int[] currentSum = new int[N + 1];
        int currentTotalSum = 0;
        Queue<Ball> equalSizeBalls = new LinkedList<>();
        //공이 계속 같은 사이즈일 경우는 따로 생각해야함
        while (!priorityQueue.isEmpty()) {
            Ball ball = priorityQueue.poll();
            equalSizeBalls.add(ball);
            //현재 꺼낸 공과 같은크기의 공들을 모두 큐에 넣어준다.
            while (!priorityQueue.isEmpty() && ball.size == priorityQueue.peek().size) {
                equalSizeBalls.add(priorityQueue.poll());
            }
            for (Ball tempBall : equalSizeBalls) {
                answer[tempBall.number] = currentTotalSum - currentSum[tempBall.color];
            }

            while (!equalSizeBalls.isEmpty()) {
                Ball tempBall = equalSizeBalls.poll();
                currentTotalSum += tempBall.size;
                currentSum[tempBall.color] += tempBall.size;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb);
    }

    static class Ball {
        int number;
        int color;
        int size;

        public Ball(int number, int color, int size) {
            this.number = number;
            this.color = color;
            this.size = size;
        }
    }
}
