package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P14002 {
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        solution(N);
    }

    static void solution(int N) {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(sequence[0]);

        Stack<Point> queue = new Stack<>();
        queue.add(new Point(sequence[0], 1));
        for (int i = 1; i < N; i++) {
            int number = answer.get(answer.size() - 1);
            if (sequence[i] > number) {
                answer.add(sequence[i]);
                queue.add(new Point(sequence[i], answer.size()));
            } else {
                int index = Collections.binarySearch(answer, sequence[i]);
                if (index < 0) {
                    index = index * -1 - 1;
                    try {
                        answer.set(index, sequence[i]);
                        queue.add(new Point(sequence[i], index));
                    } catch (IndexOutOfBoundsException ignored) {

                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        sb.append(answer.size()).append("\n");
        PriorityQueue<Integer> realAnswer = new PriorityQueue<>();

        for (int i = answer.size(); i >= 1; i--) {
            while (true) {
                Point point = queue.pop();
                if (point.y == i) {
                    realAnswer.add(point.x);
                    break;
                }
            }
        }
        for (int value : realAnswer) {
            sb.append(value).append(" ");
        }
        System.out.println(sb);
    }
}
