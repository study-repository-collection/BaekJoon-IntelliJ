package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1525 {
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static HashMap<Integer, Boolean> visited = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int a = Integer.parseInt(input.nextToken());
                if (a == 0) a = 9;
                sb.append(a);
            }
        }
        visited.put(Integer.parseInt(sb.toString()), true);
        System.out.println(solution(Integer.parseInt(sb.toString())));
    }

    static int solution(int first) {
        int count = 0;
        Queue<Integer> integers = new LinkedList<>();
        integers.add(first);

        while (!integers.isEmpty()) {
            if (visited.containsKey(123456789)) {
                return count;
            }
            int size = integers.size();
            for (int i = 0; i < size; i++) {
                int temp = integers.poll();
                String temps = String.valueOf(temp);
                int index = find9(temps);
                for (int[] move : dxDy) {
                    int y = index / 3;
                    int x = index % 3;
                    int X = x + move[0];
                    int Y = y + move[1];
                    if (X < 0 || Y < 0 || X > 2 || Y > 2) continue;
                    int tempIndex = Y * 3 + X;
                    char[] chars = temps.toCharArray();
                    char swap = chars[tempIndex];
                    chars[tempIndex] = '9';
                    chars[index] = swap;
                    int swapNumber = Integer.parseInt(new String(chars));
                    if (!visited.containsKey(swapNumber)) {
                        visited.put(swapNumber, true);
                        integers.add(swapNumber);
                    }
                }
            }
            count++;
        }

        return -1;
    }


    static int find9(String value) {
        for (int i = 0; i < 9; i++) {
            if (value.charAt(i) == '9') {
                return i;
            }
        }
        return -1;
    }

}
