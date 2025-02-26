package gong;

import java.util.*;
import java.io.*;

/*
특정한 최단 경로

시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	256 MB	101711	27996	19003	25.469%

문제
---
방향성이 없는 그래프가 주어진다.

세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다.

또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다.

하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라.

1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.



입력
---
첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다.

(2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데,
a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000)

다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1)

임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.


출력
---
첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다.

그러한 경로가 없을 때에는 -1을 출력한다.

예제 입력 1
4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
예제 출력 1
7
 */

public class Exam_1504 {
    public static List<ArrayList<int[]>> nodeInfo;
    public static int[] distance;
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수
        N = Integer.parseInt(st.nextToken());
        // 간선의 개수
        int E = Integer.parseInt(st.nextToken());

        nodeInfo = new ArrayList<>();

        // 초기화
        for(int i = 0; i < N + 1; i++) {
            nodeInfo.add(new ArrayList<>());
        }

        // 간선 정보 저장
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodeInfo.get(a).add(new int[] {b, c});
            nodeInfo.get(b).add(new int[] {a, c});
        }

        st = new StringTokenizer(br.readLine());

        // 무조건 건너가야 할 두 개의 정점
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];

        for(int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        long result = solve(v1, v2);

        result = result >= Integer.MAX_VALUE ? -1 : result;

        System.out.println(result);
    }

    static long solve(int v1, int v2) {
        int[] answer1 = dijkstra(1);


        // v1 에서의.
        int[] answer2 = dijkstra(v1);

        //v2
        int[] answer3 = dijkstra(v2);

        long ans1 = 0;
        ans1 += answer1[v1];
        ans1 += answer2[v2];
        ans1 += answer3[N];

        long ans2 = 0;
        ans2 += answer1[v2];
        ans2 += answer3[v1];
        ans2 += answer2[N];

        return Math.min(ans1, ans2);
    }

    static int[] dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);

        // 전역 배열 초기화.
        clearDis();

        pq.offer(new int[] {0, start});
        distance[start] = 0;

        while(!pq.isEmpty()) {
            int[] pollNodeInfo = pq.poll();

            int currDistance = pollNodeInfo[0];
            int currNode = pollNodeInfo[1];

            // 만약 현재 최소값보다 더 길게 나왔다면, 고려 할 필요가 없다.
            if(distance[currNode] < currDistance) {
                continue;
            }

            ArrayList<int[]> list = nodeInfo.get(currNode);

            // 현재 노드와 연결된 모든 노드를 비교한다.
            for(int i = 0; i < list.size(); i++) {
                int[] tempInfo = list.get(i);

                int tempNode = tempInfo[0];
                int tempDistance = tempInfo[1] + currDistance;

                if(distance[tempNode] > tempDistance) {
                    distance[tempNode] = tempDistance;
                    pq.offer(new int[] {tempDistance, tempNode});
                }
            }
        }
        int[] copy = distance.clone();


        return copy;
    }

    static void clearDis() {
        for(int i = 0; i < distance.length; i++)
            distance[i] = Integer.MAX_VALUE;
    }
}

