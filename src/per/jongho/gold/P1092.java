package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P1092 {
    static ArrayList<Integer> crane;
    static ArrayList<Integer> boxes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());//항구에 있는 크레인의 개수 -> 1분에 박스를 하나씩 배에 실을 수 있음

        int maxCrain = -1;
        StringTokenizer crainInfo = new StringTokenizer(br.readLine());
        crane = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(crainInfo.nextToken()));
            maxCrain = Math.max(crane.get(i), maxCrain);
        }
        int maxBox = -1;
        int M = Integer.parseInt(br.readLine());//박스의 개수
        boxes = new ArrayList<>();
        StringTokenizer boxInfo = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(boxInfo.nextToken()));
            maxBox = Math.max(maxBox, boxes.get(i));
        }
        if (maxBox > maxCrain) {
            System.out.println(-1);
        } else {
            boxes.sort(Comparator.reverseOrder());
            crane.sort(Comparator.reverseOrder());
            System.out.println(solution(N, M));
        }
    }

    static int solution(int N, int M) {
        int time = 0;
        while (!boxes.isEmpty()) {
            int index = 0;
            for (int i = 0; i < crane.size();) {
                if (index == boxes.size()) break;
                if (crane.get(i) >= boxes.get(index)) {
                    boxes.remove(index);
                    i++;
                } else {
                    index++;
                }
            }
            time++;
        }
        return time;
    }

}
