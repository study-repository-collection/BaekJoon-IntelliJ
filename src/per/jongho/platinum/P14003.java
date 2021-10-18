package per.jongho.platinum;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.System.in;

public class P14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        solution(N, sequence);
    }


    static void solution(int N, int[] sequence) {
        ArrayList<Integer> C = new ArrayList<>();
        ArrayList<Point> answer = new ArrayList<>();
        C.add(sequence[0]);
        answer.add(new Point(1, sequence[0]));
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] > C.get(C.size() - 1)) {
                C.add(sequence[i]);
                answer.add(new Point(C.size(), sequence[i]));
            } else {
                int index = Collections.binarySearch(C, sequence[i]);
                if (index < 0) {
                    index = index * -1 - 1;
                    C.set(index, sequence[i]);
                    answer.add(new Point(index+1, sequence[i]));
                }
            }
        }
        ArrayList<Integer> answers = new ArrayList<>();

        int count = C.size();
        for (int i = answer.size() - 1; i >= 0; i--) {
            if (answer.get(i).x == count) {
                count--;
                answers.add(answer.get(i).y);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(C.size()).append("\n");
        for (int i = answers.size() - 1; i >= 0; i--) {
            sb.append(answers.get(i)).append(" ");
        }
        System.out.print(sb);
    }
}
