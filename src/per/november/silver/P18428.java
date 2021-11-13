package per.november.silver;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P18428 {

    static final char TEACHER = 'T';
    static final char STUDENT = 'S';
    static final char OBSTACLE = 'O';
    static final char EMPTY = 'X';

    static char[][] map;
    static ArrayList<Point> teachers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stringTokenizer.nextToken().charAt(0);
                if (map[i][j] == TEACHER) {
                    teachers.add(new Point(j, i));
                }
            }
        }
        installObstacle(0, 0, 0, N);
        System.out.println(find ? "YES" : "NO");
    }

    static boolean find = false;

    static void installObstacle(int count, int x, int y, int N) {
        if (find) return;
        if (count == 3) {
            if (canAvoid(N)) {
                find = true;
            }
            return;
        }
        for (int i = y; i < N; i++) {
            for (int j = x; j < N; j++) {
                if (map[i][j] == EMPTY) {
                    map[i][j] = OBSTACLE;
                    int X = x + 1;
                    int Y = y;
                    if (X == N) {
                        X = 0;
                        Y = y + 1;
                    }
                    installObstacle(count + 1, X, Y, N);
                    map[i][j] = EMPTY;
                }
            }
        }
    }

    static boolean canAvoid(int N) {
        for (Point point : teachers) {
            for (int i = point.x - 1; i >= 0; i--) {
                if (map[point.y][i] == STUDENT) {
                    return false;
                } else if (map[point.y][i] == OBSTACLE) {
                    break;
                }
            }
            for (int i = point.x + 1; i < N; i++) {
                if (map[point.y][i] == STUDENT) {
                    return false;
                } else if (map[point.y][i] == OBSTACLE) {
                    break;
                }
            }
            for (int i = point.y + 1; i < N; i++) {
                if (map[i][point.x] == STUDENT) {
                    return false;
                } else if (map[i][point.x] == OBSTACLE) {
                    break;
                }
            }
            for (int i = point.y - 1; i >= 0; i--) {
                if (map[i][point.x] == STUDENT) {
                    return false;
                } else if (map[i][point.x] == OBSTACLE) {
                    break;
                }
            }
        }
        return true;
    }
}
