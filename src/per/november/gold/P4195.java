package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P4195 {
    static HashMap<String, Integer> vertexNumber;
    static int[] parent;
    static int[] numberOfSet;
    static int number = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            vertexNumber = new HashMap<>();
            number = 1;
            int F = Integer.parseInt(br.readLine());
            parent = new int[F * 2 + 1];
            numberOfSet = new int[F * 2 + 1];
            while (F-- > 0) {
                String[] input = br.readLine().split(" ");
                sb.append(solution(input[0], input[1])).append("\n");
            }
        }
        System.out.print(sb);
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }

        if (a > b) {
            parent[a] = b;
            numberOfSet[b] += numberOfSet[a];
        } else {
            parent[b] = a;
            numberOfSet[a] += numberOfSet[b];
        }
    }

    static public int solution(String input1, String input2) {
        if (!vertexNumber.containsKey(input1)) {
            vertexNumber.put(input1, number);
            numberOfSet[number] = 1;
            parent[number] = number++;
        }
        if (!vertexNumber.containsKey(input2)) {
            vertexNumber.put(input2, number);
            numberOfSet[number] = 1;
            parent[number] = number++;
        }

        union(vertexNumber.get(input1), vertexNumber.get(input2));
        return numberOfSet[find(vertexNumber.get(input1))];

    }
}
