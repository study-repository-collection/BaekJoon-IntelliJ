package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

import static java.lang.System.in;

public class P20055 {

    static int[] durability;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //컨베이어벨트 칸의 개수
        int K = Integer.parseInt(st.nextToken()); //내구도가 0인 칸의 개수

        StringTokenizer durabilityInfo = new StringTokenizer(br.readLine());
        durability = new int[2 * N + 1];
        for (int i = 1; i <= 2 * N; i++) {
            durability[i] = (Integer.parseInt(durabilityInfo.nextToken()));
        }
        //1번은 올리는 위치, N번은 내리는 위치

        System.out.println(solution(N, K));
    }

    static int solution(int N, int K) {
        int ret = 0;
        int zeroCount = 0;
        int boardPosition = 1; //탑승 위치
        int disembarkPosition = N; //내리는 위치

        boolean[] robotExist = new boolean[2 * N + 1]; //현재 위치에 로봇이 존재하는지?

        while (zeroCount < K) {
            ret++;
            //컨베이어 벨트 회전!
            boardPosition = boardPosition == 1 ? 2 * N : boardPosition - 1;
            disembarkPosition = disembarkPosition == 1 ? 2 * N : disembarkPosition - 1;
            //내리는 위치의 로봇이 전부 사라진다.
            robotExist[disembarkPosition] = false;
            zeroCount += moveRobot(disembarkPosition, robotExist, N);
            zeroCount += board(boardPosition, robotExist);
        }
        return ret;
    }

    static int board(int boardPosition, boolean[] robotExist) {
        if (durability[boardPosition] > 0) {
            //탑승처리한다.
            robotExist[boardPosition] = true;
            //탑승했으므로 내구도를 1줄임
            durability[boardPosition]--;
            //만약 탑승지점의 내구도가 0이되면?
            if (durability[boardPosition] == 0) {
                return 1;
            }
        }
        return 0;
    }

    static int moveRobot(int disembarkPosition, boolean[] robotExist, int N) {
        int zeroCount = 0;
        //내리는 위치 바로 이전부터 역순으로 로봇을 이동시킨다.
        for (int i = 0, currentPosition = disembarkPosition == 1 ? 2 * N : disembarkPosition - 1; i < N - 2; i++) {
            //로봇이 존재할 때만 이동시킴
            if (robotExist[currentPosition]) {
                int nextPosition = currentPosition == 2 * N ? 1 : currentPosition + 1;
                //만약 다음 컨베이어의 내구도가 0보다 크고 로봇이 존재하지 않을 때만 이동
                if (durability[nextPosition] > 0 && !robotExist[nextPosition]) {
                    robotExist[currentPosition] = false;
                    durability[nextPosition]--;
                    if (durability[nextPosition] == 0) {
                        zeroCount++;
                    }
                    if (nextPosition != disembarkPosition) {
                        robotExist[nextPosition] = true;
                    }
                }
            }
            //탐색 위치를 이동
            currentPosition = currentPosition == 1 ? 2 * N : currentPosition - 1;
        }
        return zeroCount;
    }

}
