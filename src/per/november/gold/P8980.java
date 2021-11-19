package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P8980 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //마을 수
        int C = Integer.parseInt(st.nextToken()); //트럭의 용량
        int M = Integer.parseInt(br.readLine()); //보내는 박스 정보의 개수
        PriorityQueue<Info> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.start == o2.start) {
                return Integer.compare(o1.end, o2.end);
            } else return Integer.compare(o1.start, o2.start);
        });
        while (M-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int startVillage = Integer.parseInt(input.nextToken());
            int receiveVillage = Integer.parseInt(input.nextToken());
            int boxNumbers = Integer.parseInt(input.nextToken());
            priorityQueue.add(new Info(startVillage, receiveVillage, boxNumbers));
        }
        System.out.println(solution(N, C, priorityQueue));
    }

    static int solution(int N, int C, PriorityQueue<Info> priorityQueue) {
        int currentVillage = 1;
        int count = 0;
        int totalValue = 0;
        int[] boxes = new int[N + 1];

        //트럭의 현재 위치가 마을 안일 동안
        while (currentVillage <= N) {
            int currentVillageUnLoadCount = 0;
            currentVillageUnLoadCount += boxes[currentVillage];
            totalValue -= currentVillageUnLoadCount;
            if (currentVillageUnLoadCount > C) currentVillageUnLoadCount = C;
            count += currentVillageUnLoadCount;
            //현재 마을에서 내린 짐갯수를 셈 만약, 현재 마을에서 내린 짐이 트럭 용량보다 크면, 트럭 용량만큼 맞춤

            //방금 내린 짐 수량을 기반으로 원래 트럭에 있었어야할 짐 수량을 구함
            int originalCount = C - currentVillageUnLoadCount;
            //도착지점이 젤 먼 짐부터 원래 있었어야할 짐 수량이 될 때까지 짐을 뺴준다.
            for (int i = N; i >= currentVillage + 1 && totalValue > originalCount; i--) {
                totalValue -= boxes[i];
                boxes[i] = 0;
                //만약 트럭에 있는 짐의 총합이 원래 있었어야할 수량보다 작아진다면, 현재 도착지점의 짐을 원래있었어야할 수량만큼 더해줌
                if (totalValue < originalCount) {
                    boxes[i] = originalCount - totalValue;
                    totalValue = originalCount;
                }
            }
            //현재 지점에서 실을 수 있는 짐을 모두 싣는다.
            while (!priorityQueue.isEmpty() && priorityQueue.peek().start == currentVillage) {
                Info info = priorityQueue.poll();
                boxes[info.end] += info.value;
                totalValue += info.value;
            }
            currentVillage++;
        }
        return count;
    }

    static class Info {
        int start;
        int end;
        int value;

        public Info(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}
