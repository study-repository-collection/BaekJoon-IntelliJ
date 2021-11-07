package per.november.gold;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P9177 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());


        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            sb.append("Data set ").append(i).append(": ");
            sb.append(isCorrect(input.nextToken(), input.nextToken(), input.nextToken()) ? "yes" : "no").append("\n");
        }
        System.out.print(sb);
    }


    static boolean isCorrect(String word1, String word2, String word3) {
        boolean[][] visited = new boolean[word1.length() + 1][word2.length() + 1];
        Queue<Point> points = new LinkedList<>();
        visited[0][0] = true;
        points.add(new Point(0, 0));

        while (!points.isEmpty()) {
            Point point = points.poll();
            if (point.x == word1.length() && point.y == word2.length()) return true;
            char c = word3.charAt(point.x + point.y);
            if (point.x < word1.length() && word1.charAt(point.x) == c && !visited[point.x + 1][point.y]) {
                visited[point.x + 1][point.y] = true;
                points.add(new Point(point.x + 1, point.y));
            }
            if (point.y < word2.length() && word2.charAt(point.y) == c && !visited[point.x][point.y + 1]) {
                visited[point.x][point.y + 1] = true;
                points.add(new Point(point.x, point.y + 1));
            }
        }
        return false;
    }

}
