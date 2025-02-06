package gong;

import java.util.*;
import java.io.*;

/*
⚾

시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초 (추가 시간 없음) (하단 참고)	512 MB	19350	9068	5855	43.973%

문제
---
⚾는 9명으로 이루어진 두 팀이 공격과 수비를 번갈아 하는 게임이다.

하나의 이닝은 공격과 수비로 이루어져 있고, 총 N이닝 동안 게임을 진행해야 한다.

한 이닝에 3아웃이 발생하면 이닝이 종료되고, 두 팀이 공격과 수비를 서로 바꾼다.

두 팀은 경기가 시작하기 전까지 타순(타자가 타석에 서는 순서)을 정해야 하고, 경기 중에는 타순을 변경할 수 없다.

9번 타자까지 공을 쳤는데 3아웃이 발생하지 않은 상태면 이닝은 끝나지 않고, 1번 타자가 다시 타석에 선다.

타순은 이닝이 변경되어도 순서를 유지해야 한다.

예를 들어, 2이닝에 6번 타자가 마지막 타자였다면, 3이닝은 7번 타자부터 타석에 선다.

공격은 투수가 던진 공을 타석에 있는 타자가 치는 것이다.

공격 팀의 선수가 1루, 2루, 3루를 거쳐서 홈에 도착하면 1점을 득점한다.

타자가 홈에 도착하지 못하고 1루, 2루, 3루 중 하나에 머물러있을 수 있다.

루에 있는 선수를 주자라고 한다. 이닝이 시작될 때는 주자는 없다.

타자가 공을 쳐서 얻을 수 있는 결과는 안타, 2루타, 3루타, 홈런, 아웃 중 하나이다.

각각이 발생했을 때, 벌어지는 일은 다음과 같다.

안타: 타자와 모든 주자가 한 루씩 진루한다.
2루타: 타자와 모든 주자가 두 루씩 진루한다.
3루타: 타자와 모든 주자가 세 루씩 진루한다.
홈런: 타자와 모든 주자가 홈까지 진루한다.
아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가한다.
한 야구팀의 감독 아인타는 타순을 정하려고 한다. 아인타 팀의 선수는 총 9명이 있고, 1번부터 9번까지 번호가 매겨져 있다.

아인타는 자신이 가장 좋아하는 선수인 1번 선수를 4번 타자로 미리 결정했다.

이제 다른 선수의 타순을 모두 결정해야 한다.

아인타는 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있다.

가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 구해보자.

입력
---
첫째 줄에 이닝 수 N(2 ≤ N ≤ 50)이 주어진다.

둘째 줄부터 N개의 줄에는 각 선수가 각 이닝에서 얻는 결과가 1번 이닝부터 N번 이닝까지 순서대로 주어진다.

이닝에서 얻는 결과는 9개의 정수가 공백으로 구분되어져 있다.

각 결과가 의미하는 정수는 다음과 같다.

안타: 1
2루타: 2
3루타: 3
홈런: 4
아웃: 0

각 이닝에는 아웃을 기록하는 타자가 적어도 한 명 존재한다.

출력
---
아인타팀이 얻을 수 있는 최대 점수를 출력한다.

예제 입력 1
2
4 0 0 0 0 0 0 0 0
4 0 0 0 0 0 0 0 0
예제 출력 1
1
예제 입력 2
2
4 0 0 0 1 1 1 0 0
0 0 0 0 0 0 0 0 0
예제 출력 2
4
예제 입력 3
2
0 4 4 4 4 4 4 4 4
0 4 4 4 4 4 4 4 4
예제 출력 3
43
예제 입력 4
2
4 3 2 1 0 4 3 2 1
1 2 3 4 1 2 3 4 0
예제 출력 4
46
예제 입력 5
9
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
4 4 4 4 4 4 4 4 0
예제 출력 5
216
예제 입력 6
9
1 2 4 3 0 2 1 0 3
1 2 1 2 0 0 0 0 1
3 4 2 3 1 2 3 4 0
0 1 2 3 4 2 1 0 0
0 0 0 0 0 0 1 4 4
0 4 0 4 0 4 0 4 0
0 4 2 2 2 2 2 2 2
1 1 1 1 1 1 1 1 0
0 2 0 3 0 1 0 2 0
예제 출력 6
89
 */

/*
1. 1번 선수는 4 번째로 들어간다.
2. 1번 선수를 제외한 모든 순서를 모두 대입 할 수 있도록 제작한다.
3.
 */

public class Exam_17281 {
    public static int[] order = new int[10]; // 몇 번째, 로 맞추기 위해 +1 함.
    public static boolean[] ordered = new boolean[10]; // 이미 해당 번 선수가 할당되었을 경우.
    public static int[][] scoreMap;
    public static int max = -1; // 맥스 점수
    public static int N; // 이닝 수
    public static int currOrder = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 이닝 수
        N = Integer.parseInt(br.readLine());

        // 1 번 선수는 무조건 4 번째 타자로 들어간다.
        order[4] = 1;

        // 1번 선수는 항상 고정되어 있다.
        ordered[1] = true;

        // 각 이닝마다 선수 순서대로 낼 수 있는 점수의 그래프 - scoreMap[1][4] = 1 이닝에 4 번 선수가 내는 점수
        scoreMap = new int[N + 1][10];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                scoreMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sequence(1);

        System.out.println(max);
    }

    // 모든 경우에 대하여 계산을 시작.
    static void sequence(int deep) {
        if(deep == 10) {
//            for(int i = 1; i < 10; i++) {
//                System.out.print(order[i] + " ");
//            }
//
//            System.out.println();

            // 현재 결정된 선수들의 순서에 따라, 각 이닝의 모든 점수를 더하여, max 와 비교한다.
            int endScore = calculate();

            // 최대값 최신화
            max = max < endScore ? endScore : max;

            // 해당 순열로 모든 이닝을 돌려봤으니, 다시 1 번째 타자로 이동.
            currOrder = 1;
            return;
        } else if(deep == 4) { // 4 번째 타자는 무조건 1번.
            // 4번째 타자는 이미 1번 선수로 결정되어 있다.
            sequence(5);
            return;
        }

        // i 번째 선수를 의미.
        for(int i = 1; i <= 9; i++) {
            // 만약 i 번째 선수가 아직 할당되지 않았다면,
            if(!ordered[i]){
                order[deep] = i; // deep 번째 순서로 i 번 선수를 넣는다.
                ordered[i] = true; // i 번째 선수가 할당되었다고 선언.
                sequence(deep + 1);

                ordered[i] = false;
            }
        }
    }

    // 현재 정해진 선수의 순서로 모든 이닝을 진행한다.
    public static int calculate() {

        int score = 0;

        for (int ening = 1; ening <= N; ening++){
            // 해당 이닝에서 1번부터 9번 선수까지의 점수표 레코드 하나를 가져온다.
            int[] scoreRecord = scoreMap[ening];

            score += oneEning(scoreRecord);
        }

        return score;
    }

    // 정해진
    public static int oneEning(int[] scoreRecord) {
        // 1루, 2루, 3루를 의미
        boolean[] seat = new boolean[3];

        // 몇 번 아웃되었는지 확인
        int outpoint = 0;

        // 현재 이닝에서 가져올 수 있는 점수
        int resultScore = 0;

        // 3 번 아웃되기 전 까지 동작.
        while(outpoint < 3) {
            // 9 번째로 정해진 선수 이후라면, 다시 1번으로 돌아온다.
            if(currOrder > 9) {
                currOrder = 1;
            }

            // 현재 타자 순서를 추출한다.
            int currPerson = order[currOrder];

            // 현재 순서(and 현재 이닝)의 선수 점수를 가져온다.
            int point = scoreRecord[currPerson];

            // 다음 선수가 나오도록 설정.
            currOrder++;

            // 현재 선수가 "아웃" 되었다면
            if(point == 0) {
                outpoint++;

            } else if(point == 4){ // 현재 선수가 "홈런" 을 쳤다면
                // 현재, 1, 2, 3 루에 선수가 존재하면 전부 점수이다.
                for(int i = 2; i >= 0; i--) {

                    // i + 1 루에 사람이 있다면,
                    if(seat[i]){
                        resultScore += 1;
                        seat[i] = false;
                    }
                }

                resultScore++; // 현재 타자도 점수.

            } else { // 만약, 안타, 2루타, 3루타라면.
                for(int i = 2; i >= 0; i--) {

                    //i 루에 사람이 있다면,
                    if(seat[i]) {
                        // 만약, 타자로 인해 현재 루수의 사람이 골인 한다면.
                        if(i + point > 2){
                            resultScore++;

                        } else { // 그렇지 않고, 여전히 루수에 남게 된다면.

                            seat[i + point] = true;
                        }

                        seat[i] = false;
                    }
                }

                // 현재 타자는 이동
                seat[point - 1] = true;
            }
        }

        return resultScore;
    }
}
