package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

public class P1941 {
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static final char[][] map = new char[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        solution(0, 0, new int[7], 0, 0);
        System.out.println(answer);
    }

    static int answer = 0;

    static void solution(int size, int start, int[] arr, int SSize, int YSize) {
        if (size == 7) {
            if (SSize <= YSize) return;
            boolean[][] visited = new boolean[5][5];
            Point point = getPointWithNumber(arr[0]);
            isConnected(arr, visited, point.x, point.y);
            for (int i = 0; i < 7; i++) {
                Point point1 = getPointWithNumber(arr[i]);
                if (!visited[point1.y][point1.x]) {
                    return;
                }
            }
            answer++;
            return;
        }
        for (int i = start; i < 25; i++) {
            arr[size] = i;
            Point point = getPointWithNumber(i);
            if (map[point.y][point.x] == 'S') {
                solution(size + 1, i + 1, arr, SSize + 1, YSize);
            } else {
                solution(size + 1, i + 1, arr, SSize, YSize + 1);
            }
        }
    }

    static void isConnected(int[] arr, boolean[][] visited, int x, int y) {
        visited[y][x] = true;
        for (int[] move : dxDy) {
            int X = x + move[0];
            int Y = y + move[1];
            if (canVisit(X, Y) && !visited[Y][X] && contain(arr, getNumberWithPoint(new Point(X, Y)))) {
                isConnected(arr, visited, X, Y);
            }
        }
    }

    static boolean contain(int[] arr, int number) {
        for (int value : arr) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }


    static Point getPointWithNumber(int number) {
        Point point = new Point();
        point.y = number / 5;
        point.x = number % 5;
        return point;
    }

    static int getNumberWithPoint(Point point) {
        return point.y * 5 + point.x;
    }

    static boolean canVisit(int x, int y) {
        return x >= 0 && y >= 0 && x < 5 && y < 5;
    }

}
