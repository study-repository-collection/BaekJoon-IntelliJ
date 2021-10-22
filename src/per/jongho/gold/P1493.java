package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1493 {

    static final ArrayList<Cube> cubes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer boxInfo = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(boxInfo.nextToken());
        int width = Integer.parseInt(boxInfo.nextToken());
        int height = Integer.parseInt(boxInfo.nextToken());

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer cubeInfo = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(cubeInfo.nextToken());
            int Number = Integer.parseInt(cubeInfo.nextToken());
            cubes.add(new Cube(A, Number));
        }
        solution(length, width, height);
    }

    static void solution(int length, int width, int height) {
        long answer = 0;
        long beforeSize = 0;

        for (int i = cubes.size() - 1; i >= 0; i--) {
            beforeSize <<= 3;
            Cube cube = cubes.get(i);
            long tempLength = length >> cube.size;
            long tempWidth = width >> cube.size;
            long tempHeight = height >> cube.size;
            long possibleCube = tempLength * tempWidth * tempHeight;
            possibleCube -= beforeSize;
            long newCube = Math.min(cube.count, possibleCube);
            beforeSize += newCube;
            answer += newCube;
        }

        if (beforeSize == (long) length * width * height) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }


    static class Cube {
        final int size;
        int count;

        public Cube(int size, int count) {
            this.size = size;
            this.count = count;
        }
    }
}
