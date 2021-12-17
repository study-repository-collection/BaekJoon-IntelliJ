package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17140 {
    static ArrayList<ArrayList<Integer>> A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(input.nextToken());
        int c = Integer.parseInt(input.nextToken());
        int k = Integer.parseInt(input.nextToken());
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                A.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
    }
}
