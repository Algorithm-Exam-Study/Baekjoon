package gong;

import java.util.*;
import java.io.*;

public class Exam_1753 {
    public static List<ArrayList<int[]>> nodeInfo;
    public static boolean[] isVisited;
    public static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // i 번 인덱스에
        nodeInfo = new ArrayList<>();

        // start
        int K = Integer.parseInt(br.readLine());

        for(int i = 0; i < V + 1; i++) {
            nodeInfo.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            // 노드 1
            int A = Integer.parseInt(st.nextToken());
            // 노드 2
            int B = Integer.parseInt(st.nextToken());
            // 간의 거리 or 가중치
            int C = Integer.parseInt(st.nextToken());

            // A --> B 에는 C 거리
            nodeInfo.get(A).add(new int[]{B, C});
            // B --> A 에는 C 거리
//            nodeInfo.get(B).add(new int[]{A, C});
        }

        // 방문 여부 확인
        isVisited = new boolean[V + 1];

        // 시작 노드로부터 최소 길이를 기록하기 위함.
        distance = new int[V + 1];

        // 최소값을 구하기 위해, 일단 최대 값으로 매핑.
        for(int i = 0; i < V + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        dijkstra(K);


        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < distance.length; i++) {
            if(distance[i] == Integer.MAX_VALUE)
                sb.append("INF").append("\n");
            else
                sb.append(distance[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void dijkstra(int start) {
        // [0] : 간선의 최소길이, [1] : 간선으로 이어지는 해당 노드
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        pq.offer(new int[]{0, start});
        isVisited[start] = true;
        distance[start] = 0;

        while(!pq.isEmpty()) {
            // 현재 정보 중에서 가장 간선 길이가 짧은 노드 꺼내기
            int[] currInfo = pq.poll();

            // 현재 엘리먼트 최소 길이
            int currDis = currInfo[0];
            // 현재 엘리먼트 번호
            int currNode = currInfo[1];

            // 만약 지금까지 구해진 최소값보다, 뽑아낸 현재 노드의 길이가 더 크거나 같다면 신경 쓸 필요 없이 넘긴다.
            if(distance[currNode] < currDis){
                continue;
            }

            // 현재 뽑아낸 노드에서 연결된 모든 노드를 가져온다.
            for(int i = 0; i < nodeInfo.get(currNode).size(); i++) {

                int[] nextNodeInfo = nodeInfo.get(currNode).get(i);

                int nextNode = nextNodeInfo[0];
                int nextDistance = nextNodeInfo[1] + currDis;

                if(distance[nextNode] > nextDistance) {
                    distance[nextNode] = nextDistance;
                    pq.offer(new int[]{nextDistance, nextNode});
                }
            }
        }
    }
}
