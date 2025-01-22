package gong;

import java.io.*;
import java.util.*;

/*
중량제한
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	128 MB	40937	11484	7063	26.087%

문제
N(2 ≤ N ≤ 10,000)개의 섬으로 이루어진 나라가 있다.

이들 중 몇 개의 섬 사이에는 다리가 설치되어 있어서 차들이 다닐 수 있다.

영식 중공업에서는 두 개의 섬에 공장을 세워 두고 물품을 생산하는 일을 하고 있다.

물품을 생산하다 보면 공장에서 다른 공장으로 생산 중이던 물품을 수송해야 할 일이 생기곤 한다.

그런데 각각의 다리마다 중량제한이 있기 때문에 무턱대고 물품을 옮길 순 없다.

만약 중량제한을 초과하는 양의 물품이 다리를 지나게 되면 다리가 무너지게 된다.

한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하는 프로그램을 작성하시오.

입력
----
첫째 줄에 N, M(1 ≤ M ≤ 100,000)이 주어진다.

다음 M개의 줄에는 다리에 대한 정보를 나타내는 세 정수 A, B(1 ≤ A, B ≤ N), C(1 ≤ C ≤ 1,000,000,000)가 주어진다.

이는 A번 섬과 B번 섬 사이에 중량제한이 C인 다리가 존재한다는 의미이다.

서로 같은 두 섬 사이에 여러 개의 다리가 있을 수도 있으며, 모든 다리는 양방향이다.

마지막 줄에는 공장이 위치해 있는 섬의 번호를 나타내는 서로 다른 두 정수가 주어진다.

공장이 있는 두 섬을 연결하는 경로는 항상 존재하는 데이터만 입력으로 주어진다.


출력
---
첫째 줄에 답을 출력한다.

예제 입력 1
3 3
1 2 2
3 1 3
2 3 2
1 3
예제 출력 1
3

 */

/*

1   2   3    번 섬

    2   3
        2

 */

public class Exam_1939 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] bridges = new long[N + 1][N + 1];

        // M 번 동안 A, B 번 섬의 중량제한을 전부 bridges 2차원 배열에 넣는다.
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int pos1 = Integer.parseInt(st.nextToken());
            int pos2 = Integer.parseInt(st.nextToken());

            // 항상 1번, 3번섬 혹은 1번섬 4번섬 과 같이 오른쪽이 크도록 받는다. - 우상향 dp 유도
            if(pos1 > pos2) {
                int temp = pos1;
                pos1 = pos2;
                pos2 = temp;
            }

            long weight = Long.parseLong(st.nextToken());

            // 이미 pos1 번 섬과 pos2 번 섬의 무게 제한이 있었을 경우, 입력 값과 비교하여 더 큰 쪽을 선택한다. - 최대중량이므로
            bridges[pos1][pos2] = bridges[pos1][pos2] < weight ? weight : bridges[pos1][pos2];


        }


    }
}
