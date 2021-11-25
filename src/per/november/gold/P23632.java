package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P23632 {
    static ArrayList<Integer>[] canMakeMaterialList;
    static HashMap<Integer, ArrayList<Integer>> materialNeeded = new HashMap<>();
    static int[] degree;
    static Queue<Integer> buildQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(input.nextToken()); //건물의 개수
        int m = Integer.parseInt(input.nextToken()); //이미 지어진 건물의 개수
        int t = Integer.parseInt(input.nextToken()); //제한 시간
        canMakeMaterialList = new ArrayList[n + 1];
        degree = new int[n + 1];
        StringTokenizer alreadyBuiltInfo = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int alreadyBuild = Integer.parseInt(alreadyBuiltInfo.nextToken());
            buildQueue.add(alreadyBuild);
        }
        for (int i = 1; i <= n; i++) {
            canMakeMaterialList[i] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int materialNumbers = Integer.parseInt(st.nextToken());
            while (materialNumbers-- > 0) {
                canMakeMaterialList[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        for (int i = 0; i < n - m; i++) {
            StringTokenizer unbuiltInfo = new StringTokenizer(br.readLine());
            int buildingNumber = Integer.parseInt(unbuiltInfo.nextToken());
            degree[buildingNumber] = Integer.parseInt(unbuiltInfo.nextToken());
            while (unbuiltInfo.hasMoreTokens()) {
                int neededMaterial = Integer.parseInt(unbuiltInfo.nextToken());
                materialNeeded.computeIfAbsent(neededMaterial, k -> new ArrayList<>());
                materialNeeded.get(neededMaterial).add(buildingNumber);
            }
        }
        solution(t);
    }

    static void solution(int t) {
        ArrayList<Integer> canBuild = new ArrayList<>();
        while (!buildQueue.isEmpty() && t-- > 0) {
            int size = buildQueue.size();
            for (int i = 0; i < size; i++) {
                int building = buildQueue.poll();
                canBuild.add(building);
                for (int materialNumber : canMakeMaterialList[building]) {
                    if (materialNeeded.containsKey(materialNumber)) {
                        for (int temp : materialNeeded.get(materialNumber)) {
                            degree[temp]--;
                            if (degree[temp] == 0) {
                                buildQueue.add(temp);
                            }
                        }
                        materialNeeded.remove(materialNumber);
                    }
                }
            }
        }
        while (!buildQueue.isEmpty()) {
            canBuild.add(buildQueue.poll());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(canBuild.size()).append("\n");
        canBuild.sort(null);
        for (int value : canBuild) {
            sb.append(value).append(" ");
        }
        System.out.print(sb);
    }
}
