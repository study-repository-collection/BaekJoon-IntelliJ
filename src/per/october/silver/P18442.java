package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P18442 {

    static long[] villageLocations; //마을 좌표
    static long min = Long.MAX_VALUE;
    static ArrayList<Long> postOfficeLists = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); //마을의 개수
        int P = Integer.parseInt(st.nextToken()); //우체국의 개수
        long L = Long.parseLong(st.nextToken()); //큰 둘레 길의 길이

        villageLocations = new long[V];

        StringTokenizer villageLocation = new StringTokenizer(br.readLine());

        for (int i = 0; i < V; i++) villageLocations[i] = Long.parseLong(villageLocation.nextToken());

        solution(0, true, P, new ArrayList<>(), L);
        solution(0, false, P, new ArrayList<>(), L);

        sb.append(min).append("\n");
        for (Long value : postOfficeLists) {
            sb.append(value).append(" ");
        }
        System.out.println(sb);

    }

    static void solution(int index, boolean choose, int P, ArrayList<Long> postOfficeList, long L) {
        //선택된 우체국 개수가 최대 우체국 개수와 같으면
        if (postOfficeList.size() == P) {
            computeDistance(postOfficeList, L);
            return;
        }
        if (index == villageLocations.length) {
            return;
        }

        if (choose) {
            postOfficeList.add(villageLocations[index]);
            solution(index + 1, true, P, postOfficeList, L);
            solution(index + 1, false, P, postOfficeList, L);
            postOfficeList.remove(villageLocations[index]);
        } else {
            solution(index + 1, true, P, postOfficeList, L);
            solution(index + 1, false, P, postOfficeList, L);
        }

    }

    static void computeDistance(ArrayList<Long> postOfficeList, long L) {
        long sum = 0;
        for (long village : villageLocations) {
            long distance = Long.MAX_VALUE;
            for (long postOffice : postOfficeList) {
                long minDistance = Math.min(Math.abs(village - postOffice), Math.abs(L - Math.abs(village - postOffice)));
                distance = Math.min(distance, minDistance);
            }
            sum += distance;
        }

        if (sum < min) {
            min = sum;
            postOfficeLists.clear();
            postOfficeLists.addAll(postOfficeList);
        }
    }

}
