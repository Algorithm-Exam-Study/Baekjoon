package gong;

import java.util.*;
import java.io.*;

/*
두 배열의 합

시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
2 초	64 MB	33364	11876	8007	32.443%

문제
----
한 배열 A[1], A[2], …, A[n]에 대해서, 부 배열은 A[i], A[i+1], …, A[j-1], A[j] (단, 1 ≤ i ≤ j ≤ n)을 말한다. 이러한 부 배열의 합은 A[i]+…+A[j]를 의미한다. 각 원소가 정수인 두 배열 A[1], …, A[n]과 B[1], …, B[m]이 주어졌을 때, A의 부 배열의 합에 B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수를 구하는 프로그램을 작성하시오.

예를 들어 A = {1, 3, 1, 2}, B = {1, 3, 2}, T=5인 경우, 부 배열 쌍의 개수는 다음의 7가지 경우가 있다.

T(=5) = A[1] + B[1] + B[2]
      = A[1] + A[2] + B[1]
      = A[2] + B[3]
      = A[2] + A[3] + B[1]
      = A[3] + B[1] + B[2]
      = A[3] + A[4] + B[3]
      = A[4] + B[2]

입력
----
첫째 줄에 T(-1,000,000,000 ≤ T ≤ 1,000,000,000)가 주어진다. 다음 줄에는 n(1 ≤ n ≤ 1,000)이 주어지고, 그 다음 줄에 n개의 정수로 A[1], …, A[n]이 주어진다. 다음 줄에는 m(1 ≤ m ≤ 1,000)이 주어지고, 그 다음 줄에 m개의 정수로 B[1], …, B[m]이 주어진다. 각각의 배열 원소는 절댓값이 1,000,000을 넘지 않는 정수이다.

출력
----
첫째 줄에 답을 출력한다. 가능한 경우가 한 가지도 없을 경우에는 0을 출력한다.

예제 입력 1
5
4
1 3 1 2
3
1 3 2
예제 출력 1
7
 */

public class Exam_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int aLen = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] A = new long[aLen + 1];

        for(int i = 1; i <= aLen; i++){
            A[i] = Long.parseLong(st.nextToken());
        }

        int bLen = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        long[] B = new long[bLen + 1];

        for(int i = 1; i <= bLen; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        // A, B 맵 생성
        Map<Long, Long> aMap = new HashMap<>();
        Map<Long, Long> bMap = new HashMap<>();

        // A Map 제작 시작
        for(int i = 1; i <= aLen; i++) {
            long currValue = 0;

            for(int j = i; j <= aLen; j++) {
                currValue += A[j];

                // 집합이 비어있지 않으며, 해당 키를 가지고 있을 경우,
                if(!aMap.isEmpty() && aMap.containsKey(currValue)) {
                    // 해당 값이 나온 횟수를 꺼내서
                    long temp = aMap.get(currValue);
                    // 하나 더 해서 다시 넣는다.
                    aMap.put(currValue, temp + 1);
                } else { // 비어있거나, 키가 없을 경우
                    aMap.put(currValue, (long) 1);
                }
            }
        }

        // B Map 제작 시작
        for(int i = 1; i <= bLen; i++) {
            long currValue = 0;

            for(int j = i; j <= bLen; j++) {
                currValue += B[j];

                // 집합이 비어있지 않으며, 해당 키를 가지고 있을 경우,
                if(!bMap.isEmpty() && bMap.containsKey(currValue)) {
                    // 해당 값이 나온 횟수를 꺼내서
                    long temp = bMap.get(currValue);
                    // 하나 더 해서 다시 넣는다.
                    bMap.put(currValue, temp + 1);
                } else { // 비어있거나, 키가 없을 경우
                    bMap.put(currValue, (long) 1);
                }
            }
        }

        // A, B map 을 서로 교차하여 횟수 세기

        Iterator<Long> iterA = aMap.keySet().iterator();

        long result = 0;

        while(iterA.hasNext()) {
            // 키의 값은 결국 어떠한 수의 집합 개수를 의미한다.
            long currKey = iterA.next();

            long findBKey = T - currKey;

            if(findBKey == 0 && !bMap.containsKey((long)0)) {
                break;
            }

            // 만약 T 를 이루기 위한 값을 bMap 이 가지고 있다면,
            if(bMap.containsKey(findBKey)) {
                long aValue = aMap.get(currKey);

                long bValue = bMap.get(findBKey);

                result += (aValue * bValue);
            }
        }

        System.out.println(result);
    }
}
