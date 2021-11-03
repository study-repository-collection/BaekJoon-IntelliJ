package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P16235 {
    static int[][] robot; //로봇이 추가해주는 양분의 량
    static int[][] map; //양분 맵
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static Deque<Integer>[][] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapInfo = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(mapInfo.nextToken()); //맵의 높이 크기
        int M = Integer.parseInt(mapInfo.nextToken()); //나무의 개수
        int K = Integer.parseInt(mapInfo.nextToken()); //K년이 지난 후 살아남은 나무의 수를 출력

        robot = new int[N + 1][N + 1];
        trees = new Deque[N + 1][N + 1];
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) Arrays.fill(map[i], 5);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++)
                trees[i][j] = new LinkedList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer robotInfo = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                robot[i][j] = Integer.parseInt(robotInfo.nextToken());
            }
        }
        while (M-- > 0) {
            StringTokenizer treeInfo = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(treeInfo.nextToken());
            int y = Integer.parseInt(treeInfo.nextToken());
            int age = Integer.parseInt(treeInfo.nextToken());
            trees[x][y].add(age);
        }

        while (K-- > 0) {
            spring(N);
            fall(N);
            winter(N);
        }
        System.out.print(answer(N));

    }

    static Queue<Tree> sexTree = new LinkedList<>();

    static void winter(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += robot[i][j];
            }
        }
    }

    static long time = 0;

    static int answer(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                count += trees[i][j].size();
            }
        }
        return count;
    }

    static void fall(int N) {
        while (!sexTree.isEmpty()) {
            Tree tree = sexTree.poll();
            for (int i = 0; i < 8; i++) {
                int X = tree.x + dx[i];
                int Y = tree.y + dy[i];
                if (canVisit(X, Y, N)) {
                    trees[Y][X].addFirst(1);
                }
            }
        }
    }

    static boolean canVisit(int x, int y, int N) {
        return (x >= 1 && y >= 1 && x <= N && y <= N);
    }


    static void spring(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int size = trees[i][j].size();
                int sum = 0;
                for (int k = 1; k <= size; k++) {
                    int age = trees[i][j].pollFirst();
                    if (age <= map[i][j]) {
                        map[i][j] -= age;
                        trees[i][j].addLast(age + 1);
                        if ((age + 1) % 5 == 0) {
                            sexTree.add(new Tree(j, i, age + 1));
                        }
                    } else {
                        sum += age / 2;
                    }
                }
                map[i][j] += sum;
            }
        }
    }

    static class Tree {
        final int x;
        final int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}
