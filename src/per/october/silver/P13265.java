package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13265 {

    static final int RED = 0;
    static final int BLUE = 1;
    static final int UNDEFINED = 2;

    static ArrayList<Integer>[] adjacentList; //인접 리스트
    static Circle[] circles; //동그라미들

    static boolean isPossible = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());//테스트 케이스 개수
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //동그라미 개수
            int M = Integer.parseInt(st.nextToken()); //직선 개수
            circles = new Circle[N + 1];
            adjacentList = new ArrayList[N + 1];
            initCircles(N);
            while (M-- > 0) {
                StringTokenizer input = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(input.nextToken());
                int b = Integer.parseInt(input.nextToken());
                if (null == adjacentList[a]) {
                    adjacentList[a] = new ArrayList<>();
                }
                adjacentList[a].add(b);
            }
            System.out.println(solution(N));
            isPossible = true;
        }
    }

    static String solution(int N) {
        for (int i = 1; i <= N; i++) {
            if (circles[i].mColor == UNDEFINED) {
                dfs(i, RED);
            }
        }
        return isPossible ? "possible" : "impossible";
    }

    static void dfs(int to, int color) {
        if (!isPossible) return;
        if (null == adjacentList[to]) return;
        circles[to].mColor = color;
        for (int i = 0; i < adjacentList[to].size(); i++) {
            int index = adjacentList[to].get(i);
            if (circles[index].mColor == UNDEFINED) {
                dfs(index, color == RED ? BLUE : RED);
            } else if (!circles[index].isContrast(color)) {
                isPossible = false;
                return;
            }
        }
    }

    static void initCircles(int N) {
        for (int i = 1; i <= N; i++) {
            circles[i] = new Circle(UNDEFINED);
        }
    }

    static class Circle {
        private int mColor;

        public Circle(int color) {
            mColor = color;
        }

        public boolean isContrast(int color) {
            return mColor != color;
        }

    }
}
