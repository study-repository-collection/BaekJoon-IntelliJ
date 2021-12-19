package per.november.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2568 {
    static ArrayList<Pair> sequence = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            sequence.add(new Pair(a, b));
        }
        sequence.sort(Comparator.comparingInt(o -> o.index));
        solution(N);
    }

    static void solution(int N) {
        ArrayList<Integer> C = new ArrayList<>();
        C.add(sequence.get(0).value);
        sequence.get(0).cIndex = 1;
        for (int i = 1; i < sequence.size(); i++) {
            if (C.get(C.size() - 1) < sequence.get(i).value) {
                C.add(sequence.get(i).value);
                sequence.get(i).cIndex = C.size();
            } else {
                int index = Collections.binarySearch(C, sequence.get(i).value);
                if (index < 0) {
                    index = index * -1 - 1;
                }
                sequence.get(i).cIndex = index + 1;
                C.set(index, sequence.get(i).value);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(N - C.size()).append("\n");
        int size = C.size();
        for (int i = sequence.size() - 1; i >= 0; i--) {
            if (sequence.get(i).cIndex == size) {
                size--;
                sequence.get(i).use = true;
            }
        }
        for (Pair pair : sequence) {
            if (!pair.use) {
                sb.append(pair.index).append("\n");
            }
        }
        System.out.println(sb);
    }

    static class Pair {
        int index;
        int value;
        int cIndex;
        boolean use;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}


