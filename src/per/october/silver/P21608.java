package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P21608 {
    static ArrayList<Integer>[] adjacentList;
    static int[] sequence;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();


        int N = Integer.parseInt(br.readLine());
        sequence = new int[N * N + 1];
        adjacentList = new ArrayList[N * N + 1];
        map = new int[N][N];
        for (int i = 1; i <= N * N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(input.nextToken());
            adjacentList[number] = new ArrayList<>();
            sequence[i] = number;
            for (int j = 0; j < 4; j++) {
                adjacentList[number].add(Integer.parseInt(input.nextToken()));
            }
        }
        //입력 종료
        solution(N);
        System.out.println(answer(N));
    }

    static int answer(int N){
        int ret = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int number =getIndexInfo(map[i][j],j,i,N).bestFriendNumber;
                switch (number){
                    case 1:
                        ret+=1;
                        break;
                    case 2:
                        ret+=10;
                        break;
                    case 3:
                        ret+=100;
                        break;
                    case 4:
                        ret+=1000;
                        break;
                }
            }
        }
        return ret;
    }
    static int solution(int N) {
        for (int i = 1; i <= N * N; i++) {
            int decide = sequence[i];
            decide(decide, N);
        }
        return 0;
    }

    static void decide(int number, int N) {
        int x = -1;
        int y = -1;
        indexInfo[][] indexInfoMap = new indexInfo[N][N];
        indexInfo currentIndexInfo = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    indexInfoMap[i][j] = getIndexInfo(number, j, i, N);
                    if (null == currentIndexInfo) {
                        currentIndexInfo = indexInfoMap[i][j];
                        x = j;
                        y = i;
                    } else if (change(indexInfoMap[i][j], currentIndexInfo)) {
                        x = j;
                        y = i;
                        currentIndexInfo = indexInfoMap[i][j];
                    }
                }
            }
        }
        map[y][x] = number;
    }

    static boolean change(indexInfo indexInfo, indexInfo currentIndexInfo) {
        if (indexInfo.bestFriendNumber > currentIndexInfo.bestFriendNumber) {
            return true;
        } else if (indexInfo.bestFriendNumber == currentIndexInfo.bestFriendNumber) {
            return indexInfo.emptyBlockNumber > currentIndexInfo.emptyBlockNumber;
        }
        return false;
    }

    static indexInfo getIndexInfo(int number, int x, int y, int N) {
        int bestFriendCount = 0;
        int emptyCount = 0;
        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canVisit(X, Y, N)) {
                if (adjacentList[number].contains(map[Y][X])) {
                    bestFriendCount++;
                } else if (map[Y][X] == 0) {
                    emptyCount++;
                }
            }
        }
        return new indexInfo(bestFriendCount, emptyCount);
    }

    static boolean canVisit(int x, int y, int N) {
        return (x >= 0 && y >= 0 && x < N && y < N);
    }

    static class indexInfo {
        int bestFriendNumber = 0;
        int emptyBlockNumber = 0;

        public indexInfo(int bestFriendNumber, int emptyBlockNumber) {
            this.bestFriendNumber = bestFriendNumber;
            this.emptyBlockNumber = emptyBlockNumber;
        }
    }
}
