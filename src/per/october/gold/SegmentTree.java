package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class SegmentTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

    }


 /*   //start: 시작 인덱스, end: 끝 인덱스, node:값을 저장할 노드
    int init(int start, int end, int node) {
        if (start == end) return tree[node] = a[start];
        int mid = (start + end) / 2;
        //재귀적으로 두 부분으로 나눈 뒤 그 합을 자기 자신으로 한다.
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    //start: 이번 노드의 시작, end: 이번 노드의 끝, node: 이번 노드, left: 구간 합을 구할 시작점, right: 구간 합을 구할 끝점
    int sum(int start, int end, int node, int left, int right) {
        //해당 노드가 범위 밖에 있는 경우
        if (left > end || right < start) return 0;

        //해당 노드가 범위 안에 있는 경우
        if (start >= left && end <= right) return tree[node];
        //그렇지 않으면, 두 부분으로 나누어 합을 구한다.
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right)
                + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    //start: 이번 노드의 시작, end : 이번 노드의 끝, node: 이번 노드, index: 변경할 인덱스, dif:기존 값과의 차이
    void update(int start, int end, int node, int index, int dif) {
        //해당 노드가 변경할 인덱스를 포함하지 않으면, 바로 종료
        if (index < start || index > end) return;
        tree[node] += dif;
        //더 이상 내려갈 구간이 존재하지 않으면 종료
        if (start == end) return;
        int mid = (start + end) / 2;
        //내려가면서 구간 합을 업데이트
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }*/
}
