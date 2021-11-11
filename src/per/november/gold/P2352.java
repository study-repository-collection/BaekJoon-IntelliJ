package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.System.in;

public class P2352 {
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N));
    }

    static int solution(int N) {
        ArrayList<Integer> C = new ArrayList<>();
        C.add(sequence[0]);
        for (int i = 1; i < N; i++) {
            int peek = C.get(C.size() - 1);

            if (peek < sequence[i]) {
                C.add(sequence[i]);
            } else {
                int index = Collections.binarySearch(C, sequence[i]);
                if (index < 0) {
                    index = index * -1 - 1;
                }
                C.set(index, sequence[i]);
            }
        }
        return C.size();
    }
}
