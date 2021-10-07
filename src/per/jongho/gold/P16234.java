package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16234 {
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int L;
    static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken()); //나라의 크기
        L = Integer.parseInt(input.nextToken());//두 나라의 인구 차이의 최소
        R = Integer.parseInt(input.nextToken());//두 나라의 인구 차이의 최대
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer personNumber = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(personNumber.nextToken());
            }
        }
        //입력 종료
        System.out.println(solution(N));
    }

    static int solution(int N) {
        int day = 0;
        ArrayList<Integer>[] adjacentList;

        //더 이상 국경이 열리지 않을 때 까지 인구 이동을 반복한다.
        while (null != (adjacentList = makeAdjacentList(N))) {
            day++;
            bfs(N, adjacentList);
        }
        return day;
    }

    static void bfs(int N, ArrayList<Integer>[] adjacentList) {
        boolean[] visit = new boolean[N * N];
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> adjacentMap = new ArrayList<>();
        for (int i = 0; i < N * N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                queue.add(i);
                adjacentMap.add(i);
                while (!queue.isEmpty()) {
                    int temp = queue.poll();
                    if (null != adjacentList[temp]) {
                        for (int j = 0; j < adjacentList[temp].size(); j++) {
                            if (!visit[adjacentList[temp].get(j)]) {
                                visit[adjacentList[temp].get(j)] = true;
                                queue.add(adjacentList[temp].get(j));
                                adjacentMap.add(adjacentList[temp].get(j));
                            }
                        }
                    }
                }
                calculateAverage(adjacentMap, N);
                adjacentMap.clear();
            }
        }
    }

    static void calculateAverage(ArrayList<Integer> adjacentMap, int N) {
        int sum = 0;
        for (int i = 0; i < adjacentMap.size(); i++) {
            int number = adjacentMap.get(i);
            int y = number / N;
            int x = number % N;

            sum += map[y][x];
        }
        sum /= adjacentMap.size();
        for (int i = 0; i < adjacentMap.size(); i++) {
            int number = adjacentMap.get(i);
            int y = number / N;
            int x = number % N;
            map[y][x] = sum;
        }
    }

    static ArrayList<Integer>[] makeAdjacentList(int N) {
        ArrayList<Integer>[] arrayLists = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ArrayList<Integer> list = getList(N, j, i);
                if (null != list) {
                    if (null == arrayLists) {
                        arrayLists = new ArrayList[N * N];
                    }
                    int number = i * N + j;
                    arrayLists[number] = new ArrayList<>();
                    arrayLists[number] = list;
                }
            }
        }
        return arrayLists;
    }

    static ArrayList<Integer> getList(int N, int x, int y) {
        ArrayList<Integer> list = null;
        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canVisit(X, Y, N) && openCountryBorder(x, y, X, Y)) {
                if (null == list) {
                    list = new ArrayList<>();
                }
                list.add(Y * N + X);
            }
        }
        return list;
    }

    static boolean openCountryBorder(int x, int y, int dx, int dy) {
        int number = Math.abs(map[y][x] - map[dy][dx]);
        return (number >= L && number <= R);
    }

    static boolean canVisit(int x, int y, int N) {
        return (x >= 0 && y >= 0 && x < N && y < N);
    }
}
