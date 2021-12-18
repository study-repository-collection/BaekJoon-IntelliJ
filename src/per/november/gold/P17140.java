package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17140 {
    static int[][] A = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(input.nextToken());
        int c = Integer.parseInt(input.nextToken());
        int k = Integer.parseInt(input.nextToken());
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(r - 1, c - 1, k));
    }

    static int solution(int r, int c, int k) {
        int time = 0;
        int rowSize = 3; //열 사이즈
        int columnSize = 3; //행 사이즈
        while (A[r][c] != k && time <= 100) {
            if (columnSize >= rowSize) {
                rowSize = operatorR(rowSize, columnSize);
            } else {
                columnSize = operatorC(rowSize, columnSize);
            }

            time++;
        }
        return A[r][c] == k ? time : -1;
    }

    static PriorityQueue<Pair> pairs = new PriorityQueue<>();
    static int[] counts = new int[101];

    /**
     * @param rowSize
     * @param columnSize
     * @return max Row 사이즈
     */
    static int operatorR(int rowSize, int columnSize) {
        int maxRowSize = rowSize;
        for (int i = 0; i < columnSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                counts[A[i][j]]++;
                A[i][j] = 0;
            }
            for (int j = 1; j < 101; j++) {
                if (counts[j] > 0) {
                    pairs.add(new Pair(j, counts[j]));
                    counts[j] = 0;
                }
            }
            maxRowSize = Math.max(pairs.size() * 2, maxRowSize);
            maxRowSize = Math.min(100, maxRowSize);
            for (int k = 0; k < 100 && !pairs.isEmpty(); k += 2) {
                Pair pair = pairs.poll();
                A[i][k] = pair.number;
                A[i][k + 1] = pair.count;
            }
            pairs.clear();
        }
        return maxRowSize;
    }

    static int operatorC(int rowSize, int columnSize) {
        int maxColumnSize = columnSize;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                counts[A[j][i]]++;
                A[j][i] = 0;
            }
            for (int j = 1; j < 101; j++) {
                if (counts[j] > 0) {
                    pairs.add(new Pair(j, counts[j]));
                    counts[j] = 0;
                }
            }
            maxColumnSize = Math.max(pairs.size() * 2, maxColumnSize);
            maxColumnSize = Math.min(100, maxColumnSize);
            for (int k = 0; k < 100 && !pairs.isEmpty(); k += 2) {
                Pair pair = pairs.poll();
                A[k][i] = pair.number;
                A[k + 1][i] = pair.count;
            }
            pairs.clear();
        }
        return maxColumnSize;
    }

    static class Pair implements Comparable<Pair> {
        int number;
        int count;

        public Pair(int number, int count) {
            this.number = number;
            this.count = count;
        }


        @Override
        public int compareTo(Pair o) {
            if (this.count == o.count) {
                return Integer.compare(this.number, o.number);
            } else {
                return Integer.compare(this.count, o.count);
            }
        }
    }
}
