package per.november.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P18429 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N, K, new boolean[N], 0, 500));
    }

    static int solution(int N, int K, boolean[] visited, int index, int weight) {
        if (index == N) {
            return 1;
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && (weight - K + arr[i] >= 500)) {
                visited[i] = true;
                sum += solution(N, K, visited, index + 1, weight - K + arr[i]);
                visited[i] = false;
            }
        }
        return sum;
    }
}
