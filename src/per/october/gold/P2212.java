package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.lang.System.in;

public class P2212 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //센서의 개수
        int K = Integer.parseInt(br.readLine()); //집중국의 개수
        int[] sensors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(sensors);
        System.out.println(answer(N, K, makeDistance(N, sensors)));

    }

    static PriorityQueue<Integer> makeDistance(int N, int[] sensors) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < N - 1; i++) {
            priorityQueue.add(sensors[i + 1] - sensors[i]);
        }
        return priorityQueue;
    }

    static int answer(int N, int K, PriorityQueue<Integer> distances) {
        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += distances.poll();
        }
        return sum;
    }

}
