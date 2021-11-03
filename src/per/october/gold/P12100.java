package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P12100 {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer info = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(info.nextToken());
            }
        }
        solution(0, N);

        System.out.println(max);
    }

    static void solution(int count, int N) {
        if (count == 5) {
            answer(N);
            return;
        }
        int[][] copyMap = new int[N][N];
        copyMap(N, copyMap);
        for (int i = 0; i < 4; i++) {
            switch (Direction.valueOf(i)) {
                case Top:
                    toTop(N);
                    solution(count + 1, N);
                    break;
                case Left:
                    toLeft(N);
                    solution(count + 1, N);
                    break;
                case Right:
                    toRight(N);
                    solution(count + 1, N);
                    break;
                default:
                    toBottom(N);
                    solution(count + 1, N);
                    break;
            }
            backToOriginal(N, copyMap);
        }
    }

    static int max = -1;

    static void answer(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }

    static void toTop(int N) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[j][i] != 0) {
                    queue.add(map[j][i]);
                    map[j][i] = 0;
                }
            }
            int index = 0;
            int before = -1;
            while (!queue.isEmpty()) {
                if (before == -1) {
                    before = queue.poll();
                } else if (before == queue.peek()) {
                    map[index++][i] = before * 2;
                    before = -1;
                    queue.poll();
                } else {
                    map[index++][i] = before;
                    before = queue.poll();
                }
            }
            if (before != -1) {
                map[index][i] = before;
            }
        }
    }

    static void toLeft(int N) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    queue.add(map[i][j]);
                    map[i][j] = 0;
                }
            }
            int index = 0;
            int before = -1;
            while (!queue.isEmpty()) {
                if (before == -1) {
                    before = queue.poll();
                } else if (before == queue.peek()) {
                    map[i][index++] = before * 2;
                    before = -1;
                    queue.poll();
                } else {
                    map[i][index++] = before;
                    before = queue.poll();
                }
            }
            if (before != -1) {
                map[i][index] = before;
            }
        }
    }

    static void toRight(int N) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j] != 0) {
                    queue.add(map[i][j]);
                    map[i][j] = 0;
                }
            }
            int index = N - 1;
            int before = -1;
            while (!queue.isEmpty()) {
                if (before == -1) {
                    before = queue.poll();
                } else if (before == queue.peek()) {
                    map[i][index--] = before * 2;
                    before = -1;
                    queue.poll();
                } else {
                    map[i][index--] = before;
                    before = queue.poll();
                }
            }
            if (before != -1) {
                map[i][index] = before;
            }
        }
    }

    static void toBottom(int N) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    queue.add(map[j][i]);
                    map[j][i] = 0;
                }
            }
            int index = N - 1;
            int before = -1;
            while (!queue.isEmpty()) {
                if (before == -1) {
                    before = queue.poll();
                } else if (before == queue.peek()) {
                    map[index--][i] = before * 2;
                    before = -1;
                    queue.poll();
                } else {
                    map[index--][i] = before;
                    before = queue.poll();
                }
            }
            if (before != -1) {
                map[index][i] = before;
            }
        }
    }

    static void backToOriginal(int N, int[][] copyMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    static void copyMap(int N, int[][] copyMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
    }

    enum Direction {
        Top(0),
        Left(1),
        Right(2),
        Bottom(3);
        int value;

        Direction(int i) {
            value = i;
        }

        static Direction valueOf(int i) {
            switch (i) {
                case 0:
                    return Top;
                case 1:
                    return Left;
                case 2:
                    return Right;
                default:
                    return Bottom;
            }
        }
    }
}
