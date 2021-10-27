package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2580 {

    static int[][] map = new int[9][9];
    static boolean[][] rowNumbers = new boolean[9][10];
    static boolean[][] colNumbers = new boolean[9][10];
    static boolean[][] sectorNumbers = new boolean[9][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
                colNumbers[j][map[i][j]] = true;
                rowNumbers[i][map[i][j]] = true;
                initSectorNumbers(j, i, map[i][j]);
            }
        }
        solution(0, 0);
    }

    static void printAnswer(StringBuilder sb) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean find = false;

    static int getSector(int x, int y) {
        return (x / 3) + ((y / 3) * 3);
    }

    static void solution(int x, int y) {
        if (find) return;
        if (y == 9) {
            find = true;
            printAnswer(new StringBuilder());
            return;
        }
        //9행에 진입함으로써 모든 스도쿠 번호를 찾음
        //이미 스도쿠 답안을 찾았다면 바로 함수를 종료
        int nextX = x + 1;
        int nextY = y;
        if (nextX == 9) {
            nextX = 0;
            nextY += 1;
        }
        //이번 칸을 채울 필요가 없다면 바로 다음 칸으로 이동
        if (map[y][x] != 0) {
            solution(nextX, nextY);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (!rowNumbers[y][i] && !colNumbers[x][i] && !sectorNumbers[getSector(x, y)][i]) {
                    map[y][x] = i;
                    rowNumbers[y][i] = true;
                    colNumbers[x][i] = true;
                    sectorNumbers[getSector(x, y)][i] = true;
                    solution(nextX, nextY);
                    rowNumbers[y][i] = false;
                    colNumbers[x][i] = false;
                    sectorNumbers[getSector(x, y)][i] = false;
                    map[y][x] = 0;
                }
            }
        }

    }

    static void initSectorNumbers(int x, int y, int number) {
        sectorNumbers[getSector(x, y)][number] = true;
    }
}
