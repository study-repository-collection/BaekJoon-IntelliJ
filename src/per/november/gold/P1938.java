package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static java.lang.System.in;

public class P1938 {

    static char[][] map;
    static Tree firstTree;
    static Tree answerTree;
    static final char TREE = '1';
    static final char EMPTY = '0';
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] rotate = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static final int VERTICAL = 0;
    static final int HORIZONTAL = 1;

    static final int moveVERTICAL = 0;
    static final int moveHORIZONTAL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        findFirstPositionAndAnswerPosition(N);
        System.out.println(solution(N));
    }

    static void findFirstPositionAndAnswerPosition(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B' && null == firstTree) {
                    if (j + 1 < N && map[i][j + 1] == 'B') {
                        firstTree = new Tree(new Point(j + 1, i), HORIZONTAL);
                    } else if (i + 1 < N && map[i + 1][j] == 'B') {
                        firstTree = new Tree(new Point(j, i + 1), VERTICAL);
                    }
                }
                if (map[i][j] == 'E' && null == answerTree) {
                    if (j + 1 < N && map[i][j + 1] == 'E') {
                        answerTree = new Tree(new Point(j + 1, i), HORIZONTAL);
                    } else if (i + 1 < N && map[i + 1][j] == 'E') {
                        answerTree = new Tree(new Point(j, i + 1), VERTICAL);
                    }
                }
            }
        }
    }

    static int solution(int N) {
        Queue<Tree> trees = new LinkedList<>();
        boolean[][][] visited = new boolean[N][N][2];
        visited[firstTree.point.y][firstTree.point.x][firstTree.direction] = true;
        trees.add(firstTree);
        int count = 0;
        while (!trees.isEmpty()) {
            int size = trees.size();
            for (int i = 0; i < size; i++) {
                Tree tree = trees.poll();
                if (tree.point.y == answerTree.point.y && tree.point.x == answerTree.point.x && tree.direction == answerTree.direction) {
                    return count;
                }
                for (int[] move : dxDy) {
                    int X = tree.point.x + move[0];
                    int Y = tree.point.y + move[1];
                    if (canMove(tree, move, N) && !visited[Y][X][tree.direction]) {
                        visited[Y][X][tree.direction] = true;
                        trees.add(new Tree(new Point(X, Y), tree.direction));
                    }
                }
                if (!visited[tree.point.y][tree.point.x][tree.direction == 1 ? 0 : 1] && canRotate(tree, N)) {
                    visited[tree.point.y][tree.point.x][tree.direction == 1 ? 0 : 1] = true;
                    trees.add(new Tree(new Point(tree.point.x, tree.point.y), tree.direction == 1 ? 0 : 1));
                }
            }
            count++;
        }
        return 0;
    }

    static boolean canMove(Tree tree, int[] move, int N) {
        int moveDirection;
        if (move[0] != 0) moveDirection = moveHORIZONTAL;
        else moveDirection = moveVERTICAL;
        int X = tree.point.x + move[0];
        int Y = tree.point.y + move[1];
        switch (tree.direction) {
            case VERTICAL:
                //나무의 현재 방향이 수직이고 움직이는 방향이 수평일떄
                if (moveDirection == moveHORIZONTAL) {
                    if (X < 0 || X >= N) return false;
                }
                //나무의 현재 방향이 수직이고 움직이는 방향이 수직일떄
                else {
                    if ((Y + 1) >= N || (Y - 1) < 0) return false;
                }
                return map[Y + 1][X] != TREE && map[Y - 1][X] != TREE && map[Y][X] != TREE;
            case HORIZONTAL:
                //나무의 현재 방햐잉 수평이고 움직이는 방향이 수평일 때
                if (moveDirection == moveHORIZONTAL) {
                    if (X - 1 < 0 || X + 1 >= N) return false;
                }
                //나무의 현재 방향이 수평이고 움직이는 방향이 수직일 때
                else {
                    if (Y >= N || Y < 0) return false;
                }
                return map[Y][X + 1] != TREE && map[Y][X] != TREE && map[Y][X - 1] != TREE;
        }
        return false;
    }

    static boolean canRotate(Tree tree, int N) {
        for (int[] move : rotate) {
            int X = tree.point.x + move[0];
            int Y = tree.point.y + move[1];
            if (!canVisit(X, Y, N) || map[Y][X] == TREE) {
                return false;
            }
        }
        return true;
    }

    static boolean canVisit(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class Tree {
        Point point;
        final int direction;

        public Tree(Point point, int direction) {
            this.point = point;
            this.direction = direction;
        }

    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
