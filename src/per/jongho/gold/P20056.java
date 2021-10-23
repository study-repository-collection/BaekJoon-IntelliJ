package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P20056 {
    static final int[][] dxdy = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
    static HashMap<String, ArrayList<Fireball>> hashMap = new HashMap<>();

    static ArrayList<Fireball> fireballs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer info = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(info.nextToken());//격자의 크기
        int M = Integer.parseInt(info.nextToken());//파이어볼의 개수
        int K = Integer.parseInt(info.nextToken());//이동 명령 횟수
        

        while (M-- > 0) {
            StringTokenizer fireballInfo = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(fireballInfo.nextToken());
            int x = Integer.parseInt(fireballInfo.nextToken());
            int weight = Integer.parseInt(fireballInfo.nextToken());
            int speed = Integer.parseInt(fireballInfo.nextToken());
            int direction = Integer.parseInt(fireballInfo.nextToken());
            Fireball fireball = new Fireball(x, y, weight, speed, direction);
            fireballs.add(fireball);
        }
        while (K-- > 0) {
            moveFireball(N);
            check();
        }
        System.out.println(answer());
    }


    static void moveFireball(int N) {
        for (Fireball fireball : fireballs) {
            for (int i = 0; i < fireball.speed; i++) {
                int X = fireball.x + dxdy[fireball.direction][0];
                int Y = fireball.y + dxdy[fireball.direction][1];
                if (X > N) X = 1;
                else if (X == 0) X = N;
                if (Y > N) Y = 1;
                else if (Y == 0) Y = N;
                fireball.x = X;
                fireball.y = Y;
            }
            if (hashMap.containsKey("" + fireball.x + fireball.y)) {
                hashMap.get("" + fireball.x + fireball.y).add(fireball);
            } else {
                ArrayList<Fireball> fireballList = new ArrayList<>();
                fireballList.add(fireball);
                hashMap.put("" + fireball.x + fireball.y, fireballList);
            }
        }
    }

    static void check() {
        for (String keySet : hashMap.keySet()) {
            ArrayList<Fireball> fireballList = hashMap.get(keySet);
            if (fireballList.size() >= 2) {
                union(fireballList);
            }
        }
        hashMap.clear();
    }

    static int answer() {
        int sumOfWeight = 0;
        for (Fireball fireball : fireballs) {
            sumOfWeight += fireball.weight;
        }
        return sumOfWeight;
    }

    static void union(ArrayList<Fireball> fireballList) {
        int sumOfWeight = 0;
        int sumOfSpeed = 0;
        boolean isAllHole = true;
        boolean isAllJJAK = true;
        int x = fireballList.get(0).x;
        int y = fireballList.get(0).y;

        final int fireballSize = fireballList.size();

        for (Fireball fireball : fireballList) {
            sumOfSpeed += fireball.speed;
            sumOfWeight += fireball.weight;
            if ((fireball.direction & 1) == 1) {
                isAllJJAK = false;
            } else {
                isAllHole = false;
            }
            fireballs.remove(fireball);
        }
        if (sumOfWeight / 5 > 0) {
            if (isAllHole || isAllJJAK) {
                fireballs.add(new Fireball(x, y, sumOfWeight / 5, sumOfSpeed / fireballSize, 0));
                fireballs.add(new Fireball(x, y, sumOfWeight / 5, sumOfSpeed / fireballSize, 2));
                fireballs.add(new Fireball(x, y, sumOfWeight / 5, sumOfSpeed / fireballSize, 4));
                fireballs.add(new Fireball(x, y, sumOfWeight / 5, sumOfSpeed / fireballSize, 6));
            } else {
                fireballs.add(new Fireball(x, y, sumOfWeight / 5, sumOfSpeed / fireballSize, 1));
                fireballs.add(new Fireball(x, y, sumOfWeight / 5, sumOfSpeed / fireballSize, 3));
                fireballs.add(new Fireball(x, y, sumOfWeight / 5, sumOfSpeed / fireballSize, 5));
                fireballs.add(new Fireball(x, y, sumOfWeight / 5, sumOfSpeed / fireballSize, 7));
            }
        }
    }

    static class Fireball {
        int x;
        int y;
        int weight;
        int speed;
        int direction;

        public Fireball(int x, int y, int weight, int speed, int direction) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.speed = speed;
            this.direction = direction;
        }
    }
}
