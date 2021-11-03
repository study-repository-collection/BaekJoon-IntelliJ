package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1043 {

    static int[] personWhoKnowTruth; //진실을 아는 자의 집합
    static ArrayList<Integer>[] parties; //파티의 집합

    static ArrayList<Integer>[] adjacentList;

    static ArrayList<Integer> peopleWhoKnowTruth = new ArrayList<>(); //진실을 알아야 하는 사람들
    static ArrayList<Integer> peopleWhoKnowLie = new ArrayList<>(); //거짓을 알아야 하는 사람들


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        parties = new ArrayList[M];
        adjacentList = new ArrayList[N + 1];
        StringTokenizer personWhoKnowTruthInfo = new StringTokenizer(br.readLine());
        int number = Integer.parseInt(personWhoKnowTruthInfo.nextToken());
        if (number == 0) {
            System.out.println(M);
            return;
        }
        personWhoKnowTruth = new int[number];
        for (int i = 0; i < number; i++) {
            personWhoKnowTruth[i] = Integer.parseInt(personWhoKnowTruthInfo.nextToken());
        }
        for (int i = 0; i < M; i++) {
            parties[i] = new ArrayList<>();
            StringTokenizer partyInfo = new StringTokenizer(br.readLine());
            partyInfo.nextToken();
            while (partyInfo.hasMoreTokens()) {
                parties[i].add(Integer.parseInt(partyInfo.nextToken()));
            }
        }
        makeAdjacentList(N);
        makeGroup(N);
        System.out.println(answer(M));
    }

    static int answer(int M) {
        int count = 0;
        for (int i = 0; i < M; i++) {
            boolean canLie = true;
            for (int j = 0; j < parties[i].size(); j++) {
                if (peopleWhoKnowTruth.contains(parties[i].get(j))) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) count++;
        }
        return count;
    }

    /**
     * 파티에 같이 참여하는 사람들의 인접 리스트를 생성하는 함수
     *
     * @param N 총 사람의 수
     */
    static void makeAdjacentList(int N) {
        for (int i = 0; i < parties.length; i++) {

            for (int j = 0; j < parties[i].size(); j++) {
                for (int k = 0; k < parties[i].size(); k++) {
                    if (j == k) continue;
                    int a = parties[i].get(j);
                    int b = parties[i].get(k);
                    if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
                    if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();

                    if (!adjacentList[a].contains(b)) {
                        adjacentList[a].add(b);
                    }
                    if (!adjacentList[b].contains(a)) {
                        adjacentList[b].add(a);
                    }
                }
            }
        }
    }

    static void makeGroup(int N) {
        boolean[] visited = new boolean[N + 1];


        for (int i = 0; i < personWhoKnowTruth.length; i++) {
            if (!visited[personWhoKnowTruth[i]]) {
                dfs(personWhoKnowTruth[i], visited);
            }
        }
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                peopleWhoKnowTruth.add(i);
            } else {
                peopleWhoKnowLie.add(i);
            }
        }

    }

    static void dfs(int to, boolean[] visited) {
        visited[to] = true;
        if (null != adjacentList[to]) {
            for (int toGo : adjacentList[to]) {
                if (!visited[toGo]) {
                    dfs(toGo, visited);
                }
            }
        }
    }
}
