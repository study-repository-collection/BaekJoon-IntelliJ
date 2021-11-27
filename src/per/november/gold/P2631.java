package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.in;

public class P2631 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution(N));
    }

    static int solution(int N) {
        ArrayList<Integer> C = new ArrayList<>();
        C.add(arr[0]);
        for (int i = 1; i < N; i++) {
            if (C.get(C.size() - 1) < arr[i]) {
                C.add(arr[i]);
            } else {
                int index = Collections.binarySearch(C, arr[i]);
                if (index < 0) {
                    index = index * -1 - 1;
                }
                C.set(index, arr[i]);
            }
        }
        return N - C.size();
    }
}
