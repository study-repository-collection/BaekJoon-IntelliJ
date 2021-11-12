package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14466 {

    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static ArrayList<Integer>[] adjacentList;
    static int[] cows;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //정사각형 맵의 크기
        int K = Integer.parseInt(input.nextToken()); //K마리의 소가 존재
        int R = Integer.parseInt(input.nextToken()); //R개의 길이 존재
        adjacentList = new ArrayList[N * N + 1];
        cows = new int[N * N + 1];
        for (int i = 1; i <= N * N; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        initAdjacentList(N);
        //길 정보 입력 ((행열)<->(행열))
        while (R-- > 0) {
            StringTokenizer roadInfo = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(roadInfo.nextToken());
            int x = Integer.parseInt(roadInfo.nextToken());
            int y1 = Integer.parseInt(roadInfo.nextToken());
            int x1 = Integer.parseInt(roadInfo.nextToken());
            int number1 = getNumberFromPoint(x, y, N);
            int number2 = getNumberFromPoint(x1, y1, N);
            adjacentList[number1].remove(Integer.valueOf(number2));
            adjacentList[number2].remove(Integer.valueOf(number1));
        }

        int cowNumber = 1;
        //소 위치 입력 (행 열)
        int tempK = K;
        while (K-- > 0) {
            StringTokenizer cowInfo = new StringTokenizer(br.readLine());
            int cowY = Integer.parseInt(cowInfo.nextToken());
            int cowX = Integer.parseInt(cowInfo.nextToken());
            cows[getNumberFromPoint(cowX, cowY, N)] = cowNumber++;
        }
        System.out.println(solution(N, tempK));
    }

    static int solution(int N, int K) {
        boolean[][] cowVisit = new boolean[K + 1][K + 1];
        for (int i = 1; i <= cows.length - 1; i++) {
            if (cows[i] != 0) {
                bfs(cows[i], i, new boolean[N * N + 1], cowVisit);
            }
        }
        int solution = 0;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= K; j++) {
                if (i == j) continue;
                if (!cowVisit[i][j]) {
                    solution++;
                    cowVisit[j][i] = true;
                }
            }
        }
        return solution;
    }

    static void bfs(int cowNumber, int cowStart, boolean[] visited, boolean[][] cowVisit) {
        visited[cowStart] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(cowStart);

        while (!queue.isEmpty()) {
            int number = queue.poll();
            for (int goTo : adjacentList[number]) {
                if (!visited[goTo]) {
                    visited[goTo] = true;
                    queue.add(goTo);
                    cowVisit[cowNumber][cows[goTo]] = true;
                }
            }
        }
    }

    static void initAdjacentList(int N) {
        for (int i = 1; i <= N * N; i++) {
            for (int[] move : dxDy) {
                int number = getNumberFromDif(i, move[0], move[1], N);
                if (number != -1) {
                    adjacentList[i].add(number);
                }
            }
        }
    }

    static int getNumberFromPoint(int x, int y, int N) {
        return (y - 1) * N + x;
    }

    static int getNumberFromDif(int number, int xDif, int yDif, int N) {
        int y = ((number - 1) / N) + 1;
        int x = (number) % N;
        if (x == 0) x = N;
        x += xDif;
        y += yDif;
        if (canVisit(x, y, N)) {
            return getNumberFromPoint(x, y, N);
        } else return -1;
    }

    static boolean canVisit(int x, int y, int N) {
        return (x >= 1 && y >= 1 && x <= N && y <= N);
    }
}
