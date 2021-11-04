package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P7682 {

    static char[][] map = new char[3][3];
    static char[][] board = new char[3][3];
    static final char EMPTY = '.';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) Arrays.fill(board[i], '.');
        while (true) {
            String input = br.readLine();
            find = false;
            if (input.equals("end")) break;
            int XCount = 0;
            int OCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = input.charAt(i * 3 + j);
                    if (map[i][j] == 'O') OCount++;
                    else if (map[i][j] == 'X') XCount++;
                }
            }
            if (Math.abs(XCount - OCount) >= 2 || XCount + OCount < 5) {
                sb.append("invalid").append("\n");
            } else {
                sb.append(solution(XCount + OCount)).append("\n");
            }
        }
        System.out.println(sb);
    }


    static String solution(int totalCount) {
        backTracking(new boolean[3][3], totalCount, 0);
        if (find) return "valid";
        else return "invalid";
    }

    static boolean find = false;

    static void backTracking(boolean[][] visited, int count, int turn) {
        if (find) return;
        if (turn != count && isEnd()) {
            return;
        }
        if (turn == count) {
            if (turn == 9 && isSame()) {
                find = true;
            } else if (isSame() && isEnd()) {
                find = true;
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    board[i][j] = (turn & 1) == 0 ? 'X' : 'O';
                    backTracking(visited, count, turn + 1);
                    board[i][j] = EMPTY;
                    visited[i][j] = false;
                }
            }
        }
    }


    static boolean isEnd() {
        for (int i = 0; i < 3; i++) {
            char start;
            boolean end = true;
            if ((start = board[i][0]) == EMPTY) continue;
            for (int j = 1; j < 3; j++) {
                if (board[i][j] != start) {
                    end = false;
                    break;
                }
            }
            if (end) return true;
        }
        for (int i = 0; i < 3; i++) {
            char start;
            boolean end = true;
            if ((start = board[0][i]) == EMPTY) continue;
            for (int j = 1; j < 3; j++) {
                if (board[j][i] != start) {
                    end = false;
                    break;
                }
            }
            if (end) return true;
        }
        if (board[0][0] != EMPTY && (board[0][0] == board[1][1] && board[1][1] == board[2][2])) {
            return true;
        }
        if (board[0][2] != EMPTY && (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return true;
        }
        return false;

    }

    static boolean isSame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] != board[i][j]) return false;
            }
        }
        return true;
    }
}
