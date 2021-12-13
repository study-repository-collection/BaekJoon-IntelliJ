package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1207 {
    static ArrayList<int[][]> blocks = new ArrayList<>();
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        int L = Integer.parseInt(br.readLine());
        map = new int[L][L];
        blocks.add(new int[1][1]);
        int count = 0;
        for (int i = 0; i < 5; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(input.nextToken());
            int M = Integer.parseInt(input.nextToken());
            int[][] block = new int[N][M];
            for (int y = 0; y < N; y++) {
                String blockInfo = br.readLine();
                for (int x = 0; x < M; x++) {
                    block[y][x] = blockInfo.charAt(x);

                    if (blockInfo.charAt(x) == '#') {
                        count++;
                    }
                }
            }
            blocks.add(block);
        }
        if (count != L * L) {
            System.out.println("gg");
            return;
        }
        solution(1, L);
        if (!find) {
            System.out.println("gg");
        }
    }


    static boolean find = false;

    static void solution(int number, int L) {
        if (find) return;
        if (number == 6) {
            find = true;
            printAnswer(L);
            return;
        }
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (find) return;
                if (canAttach(blocks.get(number), j, i, L)) {
                    attach(j, i, number);
                    solution(number + 1, L);
                    detach(j, i, blocks.get(number));
                }
            }
        }
    }

    static void attach(int x, int y, int number) {
        int[][] block = blocks.get(number);
        for (int i = y; i < y + block.length; i++) {
            for (int j = x; j < x + block[0].length; j++) {
                if (block[i - y][j - x] == '#') {
                    map[i][j] = number;
                }
            }
        }
    }

    static void detach(int x, int y, int[][] block) {
        for (int i = y; i < y + block.length; i++) {
            for (int j = x; j < x + block[0].length; j++) {
                if (block[i - y][j - x] == '#') {
                    map[i][j] = 0;
                }
            }
        }
    }


    static void printAnswer(int L) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                stringBuilder.append(map[i][j]);
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    static boolean canAttach(int[][] block, int x, int y, int L) {
        if (y + block.length > L || x + block[0].length > L) return false;
        for (int i = y; i < y + block.length; i++) {
            for (int j = x; j < x + block[0].length; j++) {
                if (map[i][j] != 0 && block[i - y][j - x] == '#') {
                    return false;
                }
            }
        }
        return true;
    }

}
