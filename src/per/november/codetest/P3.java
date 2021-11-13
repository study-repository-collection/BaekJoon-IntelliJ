package per.november.codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.System.in;

public class P3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        System.out.println(solution(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
    }

    static int solution(int[] A) {
        int sum = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int value : A) {
            hashMap.put(value, hashMap.getOrDefault(value, 0) + 1);
        }

        for (int keyValue : hashMap.keySet()) {
            int dataValue = hashMap.get(keyValue);
            sum += Math.min(Math.abs(keyValue - dataValue), dataValue);
        }
        return sum;
    }

    static void makeExample() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 100000000; i > 100000000 - 99999; i--) {
            sb.append(i).append(", ");
        }
        sb.append(100000000 - 99999 + "]");
        System.out.println(sb);
    }
}
