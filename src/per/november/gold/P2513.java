package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2513 {

    static PriorityQueue<Apartment> leftApartment = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.position, o2.position));
    static PriorityQueue<Apartment> rightApartment = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.position, o1.position));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //아파트 단지의 수
        int K = Integer.parseInt(input.nextToken()); //통학버스의 정원
        int S = Integer.parseInt(input.nextToken()); //학교의 위치

        for (int i = 0; i < N; i++) {
            StringTokenizer apartInfo = new StringTokenizer(br.readLine());
            int position = Integer.parseInt(apartInfo.nextToken());
            int personCount = Integer.parseInt(apartInfo.nextToken());
            if (position < S) {
                leftApartment.add(new Apartment(position, personCount));
            } else {
                rightApartment.add(new Apartment(position, personCount));
            }
        }
        System.out.println(solution(K, S));
    }

    static int solution(int K, int S) {
        int ret = 0;
        while (!leftApartment.isEmpty()) {
            int currentK = 0;
            int currentDirection = leftApartment.peek().position;
            while (currentK < K && !leftApartment.isEmpty()) {
                if (currentK + leftApartment.peek().personCount <= K) {
                    currentK += leftApartment.poll().personCount;
                } else {
                    int canTake = K - currentK;
                    leftApartment.peek().personCount -= canTake;
                    currentK = K;
                }
            }
            ret += (S - currentDirection) * 2;
        }

        while (!rightApartment.isEmpty()) {
            int currentK = 0;
            int currentDirection = rightApartment.peek().position;
            while (currentK < K && !rightApartment.isEmpty()) {
                if (currentK + rightApartment.peek().personCount <= K) {
                    currentK += rightApartment.poll().personCount;
                } else {
                    int canTake = K - currentK;
                    rightApartment.peek().personCount -= canTake;
                    currentK = K;
                }
            }
            ret += (currentDirection - S) * 2;
        }

        return ret;
    }

    static class Apartment {
        int position;
        int personCount;

        public Apartment(int position, int personCount) {
            this.position = position;
            this.personCount = personCount;
        }
    }
}
