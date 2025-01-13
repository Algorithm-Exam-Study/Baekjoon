package gong;

import java.io.*;
import java.util.*;

/*
감시
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	512 MB	57132	27621	16927	45.264%
문제
스타트링크의 사무실은 1×1크기의 정사각형으로 나누어져 있는 N×M 크기의 직사각형으로 나타낼 수 있다.

사무실에는 총 K개의 CCTV가 설치되어져 있는데, CCTV는 5가지 종류가 있다.

각 CCTV가 감시할 수 있는 방법은 다음과 같다.


1번	2번	3번	4번	5번

1번 CCTV는 한 쪽 방향만 감시할 수 있다.

2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고, 3번은 직각 방향이어야 한다.

4번은 세 방향, 5번은 네 방향을 감시할 수 있다.

CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다.

사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다.

CCTV가 감시할 수 없는 영역은 사각지대라고 한다.

CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.

0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0

지도에서 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호이다.

위의 예시에서 1번의 방향에 따라 감시할 수 있는 영역을 '#'로 나타내면 아래와 같다.

0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 # 6 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
# # 1 0 6 0
0 0 0 0 0 0
0 0 # 0 0 0
0 0 # 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 # 0 0 0
→	←	↑	↓
CCTV는 벽을 통과할 수 없기 때문에, 1번이 → 방향을 감시하고 있을 때는 6의 오른쪽에 있는 칸을 감시할 수 없다.

0 0 0 0 0 0
0 2 0 0 0 0
0 0 0 0 6 0
0 6 0 0 2 0
0 0 0 0 0 0
0 0 0 0 0 5
위의 예시에서 감시할 수 있는 방향을 알아보면 아래와 같다.

0 0 0 0 0 #
# 2 # # # #
0 0 0 0 6 #
0 6 # # 2 #
0 0 0 0 0 #
# # # # # 5
0 0 0 0 0 #
# 2 # # # #
0 0 0 0 6 #
0 6 0 0 2 #
0 0 0 0 # #
# # # # # 5
0 # 0 0 0 #
0 2 0 0 0 #
0 # 0 0 6 #
0 6 # # 2 #
0 0 0 0 0 #
# # # # # 5
0 # 0 0 0 #
0 2 0 0 0 #
0 # 0 0 6 #
0 6 0 0 2 #
0 0 0 0 # #
# # # # # 5
왼쪽 상단 2: ↔, 오른쪽 하단 2: ↔	왼쪽 상단 2: ↔, 오른쪽 하단 2: ↕	왼쪽 상단 2: ↕, 오른쪽 하단 2: ↔	왼쪽 상단 2: ↕, 오른쪽 하단 2: ↕
CCTV는 CCTV를 통과할 수 있다. 아래 예시를 보자.

0 0 2 0 3
0 6 0 0 0
0 0 6 6 0
0 0 0 0 0
위와 같은 경우에 2의 방향이 ↕ 3의 방향이 ←와 ↓인 경우 감시받는 영역은 다음과 같다.

# # 2 # 3
0 6 # 0 #
0 0 6 6 #
0 0 0 0 #
사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에는 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다.

CCTV의 최대 개수는 8개를 넘지 않는다.

출력
첫째 줄에 사각 지대의 최소 크기를 출력한다.

예제 입력 1
4 6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0
예제 출력 1
20
예제 입력 2
6 6
0 0 0 0 0 0
0 2 0 0 0 0
0 0 0 0 6 0
0 6 0 0 2 0
0 0 0 0 0 0
0 0 0 0 0 5
예제 출력 2
15
예제 입력 3
6 6
1 0 0 0 0 0
0 1 0 0 0 0
0 0 1 0 0 0
0 0 0 1 0 0
0 0 0 0 1 0
0 0 0 0 0 1
예제 출력 3
6
예제 입력 4
6 6
1 0 0 0 0 0
0 1 0 0 0 0
0 0 1 5 0 0
0 0 5 1 0 0
0 0 0 0 1 0
0 0 0 0 0 1
예제 출력 4
2
예제 입력 5
1 7
0 1 2 3 4 5 6
예제 출력 5
0
예제 입력 6
3 7
4 0 0 0 0 0 0
0 0 0 2 0 0 0
0 0 0 0 0 0 4
예제 출력 6
0
 */

public class Exam_15683 {
    public static int minimum = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        char[][] map = new char[height][width];

        for(int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < width; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        recursive(map, 0, 0);

        System.out.println(minimum);
    }
    static void recursive(char[][] map, int x, int y) {
        // 이차원 배열의 끝에 다다랐을 경우, 계산
        if (y == map.length) {
            int tempResult = result(map);
            minimum = minimum > tempResult ? tempResult : minimum;
            return;
        }

        // 한 x 축을 전부 보았을 때, 다음 줄로 넘어간다.
        if (x == map[0].length) {
            recursive(map, 0, y + 1);
            return;
        }

        // 현재 장소 탐색
        char ch = map[y][x];

        if (ch == '1' || ch == '3' || ch == '4') {
            for(int i = 0; i < 4; i++) {
                char[][] newMap = fill(map, x, y, i);
                recursive(newMap, x + 1, y);
            }
        } else if (ch == '2') {
            for(int i = 0; i < 2; i++) {
                char[][] newMap = fill(map, x, y, i);
                recursive(newMap, x + 1, y);
            }
        } else if (ch == '5') {
            char[][] newMap = fill(map, x, y, 0);
            recursive(newMap, x + 1, y);
        } else {
            recursive(map, x + 1, y);
        }
    }

    public static char[][] fill(char[][] map, int x, int y, int rotate) {
        char[][] newMap = new char[map.length][map[0].length];

        // 복사
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }


        char ch = map[y][x];

        if(ch == '1'){
            fillSide(newMap, x, y, rotate % 4);
        } else if(ch == '2') {
            fillSide(newMap, x, y, rotate % 4);
            fillSide(newMap, x, y, (rotate + 2) % 4);
        } else if(ch == '3') {
            fillSide(newMap, x, y, rotate % 4);
            fillSide(newMap, x, y, (rotate + 1) % 4);
        } else if(ch == '4') {
            fillSide(newMap, x, y, rotate % 4);
            fillSide(newMap, x, y, (rotate + 1) % 4);
            fillSide(newMap, x, y, (rotate + 2) % 4);
        } else if(ch == '5') {
            fillSide(newMap, x, y, rotate % 4);
            fillSide(newMap, x, y, (rotate + 1) % 4);
            fillSide(newMap, x, y, (rotate + 2) % 4);
            fillSide(newMap, x, y, (rotate + 3) % 4);
        }

        return newMap;
    }

    static void fillSide(char[][] map, int x, int y, int rotate) {
        if(rotate == 1) { // 오른쪽
            x += 1;
            while(x < map[0].length) {
                char curr = map[y][x];

                // 벽을 만난다면, 그 즉시 종료
                if(curr == '6')
                    break;
                else if(curr >= '1' && curr <= '5') { // 감시카메라는 건너뛴다.
                    x++;
                    continue;
                }
                map[y][x] = '#';
                x++;
            }
        } else if(rotate == 2) { // 밑쪽
            y += 1;
            while(y < map.length) {
                char curr = map[y][x];

                if(curr == '6')
                    break;
                else if(curr >= '1' && curr <= '5') { // 감시카메라는 건너뛴다.
                    y++;
                    continue;
                }
                map[y][x] = '#';
                y++;
            }

        } else if(rotate == 3) { // 왼쪽
            x -= 1;
            while(x >= 0) {
                char curr = map[y][x];

                if(curr == '6')
                    break;
                else if(curr >= '1' && curr <= '5') {
                    x--;
                    continue;
                }

                map[y][x] = '#';
                x--;
            }

        } else { // 위쪽
            y -= 1;
            while(y >= 0) {
                char curr = map[y][x];

                if(curr == '6')
                    break;
                else if(curr >= '1' && curr <= '5') {
                    y--;
                    continue;
                }

                map[y][x] = '#';
                y--;
            }
        }
    }

    public static int result(char[][] map) {
        int result = 0;

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] == '0')
                    result++;
            }
        }

        return result;
    }
}
