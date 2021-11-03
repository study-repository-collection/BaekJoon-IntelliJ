package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P1202 {

    static ArrayList<Jewelry> jewelries = new ArrayList<>();
    static int[] backpacks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());
        backpacks = new int[K];
        for (int i = 0; i < N; i++) {
            StringTokenizer jewelry = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(jewelry.nextToken());
            int price = Integer.parseInt(jewelry.nextToken());
            jewelries.add(new Jewelry(weight, price));
        }
        for (int i = 0; i < K; i++) {
            backpacks[i] = Integer.parseInt(br.readLine());
        }
        Collections.sort(jewelries);
        Arrays.sort(backpacks);
        System.out.println(solution(N, K));

    }

    static long solution(int N, int K) {
        long sum = 0;
        PriorityQueue<Integer> values = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, j = 0; i < K; i++) {
            for (; j < N; j++) {
                if (jewelries.get(j).weight <= backpacks[i]) {
                    values.add(jewelries.get(j).value);
                } else {
                    break;
                }
            }
            if (!values.isEmpty()) sum += values.poll();
        }
        return sum;
    }


    static class Jewelry implements Comparable<Jewelry> {
        final int weight;
        final int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
