package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.in;

public class P1339 {
    static int[] numbers = new int[26];
    static ArrayList<Character> characters = new ArrayList<>();
    static final ArrayList<String> strings = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            strings.add(br.readLine());
        }
        initCharacters();
        solution(0, new boolean[10]);
        System.out.println(max);
    }

    static void initCharacters() {
        for (int i = 0; i < strings.size(); i++) {
            String temp = strings.get(i);
            for (int j = 0; j < temp.length(); j++) {
                if (!characters.contains(temp.charAt(j))) {
                    characters.add(temp.charAt(j));
                }
            }
        }
        characters.sort(null);
    }

    static int max = -1;

    static void sum() {
        int sum = 0;
        for (int i = 0; i < strings.size(); i++) {
            String temp = strings.get(i);
            StringBuilder number = new StringBuilder();
            for (int j = 0; j < temp.length(); j++) {
                number.append(numbers[temp.charAt(j) - 'A']);
            }
            sum += Integer.parseInt(number.toString());
        }
        max = Math.max(max, sum);
    }

    static void solution(int index, boolean[] visited) {
        if (index == characters.size()) {
            sum();
            return;
        }
        char temp = characters.get(index);
        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                visited[i] = true;
                numbers[temp - 'A'] = i;
                solution(index + 1, visited);
                visited[i] = false;
            }
        }
    }
}
