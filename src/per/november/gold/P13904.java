package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13904 {
    static ArrayList<Integer>[] assignments;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        assignments = new ArrayList[1001];
        int maxDay = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer assignmentInfo = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(assignmentInfo.nextToken());
            int score = Integer.parseInt(assignmentInfo.nextToken());
            maxDay = Math.max(index, maxDay);
            if (null == assignments[index]) assignments[index] = new ArrayList<>();
            assignments[index].add(score);
        }

        System.out.println(solution(maxDay));
    }

    static int solution(int N) {
        PriorityQueue<Integer> scores = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (null != assignments[i]) {
                for (int value : assignments[i]) {
                    if (scores.size() < i) {
                        scores.add(value);
                    } else {
                        if (scores.peek() < value) {
                            scores.poll();
                            scores.add(value);
                        }
                    }
                }
            }
        }
        int sum = 0;
        while (!scores.isEmpty()) {
            sum += scores.poll();
        }
        return sum;
    }

}
