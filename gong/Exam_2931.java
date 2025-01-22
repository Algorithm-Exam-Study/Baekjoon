package gong;

import java.util.*;
import java.io.*;

/*
가스관 다국어

시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	128 MB	10506	2644	1864	27.713%

문제
-----
러시아 가스를 크로아티아로 운반하기 위해 자그레브와 모스코바는 파이프라인을 디자인하고 있다. 두 사람은 실제 디자인을 하기 전에 파이프 매니아 게임을 이용해서 설계를 해보려고 한다.

이 게임에서 유럽은 R행 C열로 나누어져 있다. 각 칸은 비어있거나, 아래 그림과 같은 일곱가지 기본 블록으로 이루어져 있다.


블록 '|'	블록 '-'	블록 '+'	블록 '1'	블록 '2'	블록 '3'	블록 '4'
가스는 모스크바에서 자그레브로 흐른다. 가스는 블록을 통해 양방향으로 흐를 수 있다. '+'는 특별한 블록으로, 아래 예시처럼 두 방향 (수직, 수평)으로 흘러야 한다.

파이프 라인의 설계를 마친 후 두 사람은 잠시 저녁을 먹으러 갔다. 그 사이 해커가 침임해 블록 하나를 지웠다. 지운 블록은 빈 칸이 되어있다.

해커가 어떤 칸을 지웠고, 그 칸에는 원래 어떤 블록이 있었는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 유럽의 크기 R과 C가 주어진다. (1 ≤ R, C ≤ 25)

다음 R개 줄에는 C개 글자가 주어지며, 다음과 같은 글자로 이루어져 있다.

빈칸을 나타내는 '.'
블록을 나타내는 '|'(아스키 124), '-','+','1','2','3','4'
모스크바의 위치를 나타내는 'M'과 자그레브를 나타내는 'Z'. 두 글자는 한 번만 주어진다.

항상 답이 존재하고, 가스의 흐름이 유일한 경우만 입력으로 주어진다.

또, 모스크바와 자그레브가 하나의 블록과 인접해 있는 입력만 주어진다.

또, 불필요한 블록이 존재하지 않는다.

즉, 없어진 블록을 추가하면, 모든 블록에 가스가 흐르게 된다.

출력
----
지워진 블록의 행과 열 위치를 출력하고, 어떤 블록이었는지를 출력한다.

예제 입력 1
3 7
.......
.M-.-Z.
.......
예제 출력 1
2 4 -

예제 입력 2
3 5
..1-M
1-+..
Z.23.
예제 출력 2
2 4 4

예제 입력 3
6 10
Z.1----4..
|.|....|..
|..14..M..
2-+++4....
..2323....
..........
예제 출력 3
3 3 |
 */

public class Exam_2931 {
    // 0 : 위 방향, 1 : 왼쪽 방향, 2 : 아래 방향, 3 : 오른쪽 방향
    public static int[] f1 = {-1, 0, 1, 0};
    public static int[] f2 = {0, -1, 0, 1};
    public static int currDirection = -1; // 0, 1, 2, 3 중 하나로 방향 설정
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        int countPipe = 0;
        int willSub = 0;

        int[] moskba = new int[2];
        int[] zargrev = new int[2];

        for(int i = 0; i < R; i++) {
            String row = br.readLine();

            for(int j = 0; j < C; j++) {
                char ch = row.charAt(j);

                if(ch == 'M') {
                    moskba[0] = i;
                    moskba[1] = j;
                } else if(ch == 'Z') {
                    zargrev[0] = i;
                    zargrev[1] = j;
                } else if(ch != '.') {
                    countPipe++;
                }

                if(ch == '+')
                    willSub++;

                map[i][j] = ch;
            }
        }

        // 해커가 삭제한 블록은 복구될 것이기 때문.
        countPipe++;

        // 시작 방향 찾기
        findStartDirection(moskba, map);
        // 여기서 currDirection 이 제대로 설정됨.

        int curDirection = currDirection;

        // 현재 위치 모스크바로 설정
        int currY = moskba[0];
        int currX = moskba[1];

        int[] hackerPos = new int[2];

        // 그 댜음 접근 지점이 . 이 될 때 까지 지속. - 해커 위치 파악
        while(true) {
            int nextY = currY + f1[currDirection];
            int nextX = currX + f2[currDirection];

            // 흐름대로 가는데, 만약 접근이 불가하다면, 그 장소는 바로 해커가 뺀 곳이다.
            boolean isCanAccess = canAccess(map[ nextY ][ nextX ]);

            // 접근이 불가하다는 것은, 있어야 할 파이프가 없다는 의미이다.
            if(!isCanAccess) {
                hackerPos[0] = nextY;
                hackerPos[1] = nextX;
                break;
            }

            // 접근이 가능하다면, 이동 및 방향 바꾸기
            currY = nextY;
            currX = nextX;

            changeDirection(map[currY][currX]);
        }

        // 해커가 없앤 블록을 분류별로 넣어가며, 자그레브까지 도착하는지 확인
        // 만능 블록인 '+' 은 맨 나중에 넣어보기

        char[] blocks = {'|', '-', '1', '2', '3', '4', '+'};

        char resultBlock = 'x';

        for(int i = 0; i < blocks.length; i++) {
            // 해커가 뺀 위치에 블록을 순회하며 넣는다.
            map[hackerPos[0]][hackerPos[1]] = blocks[i];

            // 방향 초기화
            currDirection = curDirection;

            // 현재 위치를 다시 모스크바로 초기화
            currY = moskba[0];
            currX = moskba[1];

            boolean[][] pipeBoolMap = new boolean[map.length][map[0].length];

            boolean isSuccess = false;

            while(true) {
                int nextY = currY + f1[currDirection];
                int nextX = currX + f2[currDirection];

                boolean isValid = isValidAxis(nextY, nextX, map);

                if(!isValid) { // 다음 검색 좌표가 유효하지 않음 (벗어남)
                    break;
                }

                char ch = map[nextY][nextX];

                boolean isCanAccess = canAccess(map[ nextY ][ nextX ]);

                if(!isCanAccess) {
                    break;
                }

                if(nextY == zargrev[0] && nextX == zargrev[1]){

                    isSuccess = true;
                    break;
                }

                pipeBoolMap[nextY][nextX] = true;

                currY = nextY;
                currX = nextX;

                changeDirection(map[currY][currX]);
            }

            int tempPipeNum = 0;

            // 지나간 파이프 세기
            for(int t = 0; t < pipeBoolMap.length; t++) {
                for(int p = 0; p < pipeBoolMap[0].length; p++) {
                    if(pipeBoolMap[t][p]){
                        tempPipeNum++;
                    }
                }
            }


            if(isSuccess && (tempPipeNum == countPipe)) {
//          if(isSuccess){
                resultBlock = blocks[i];
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(hackerPos[0] + 1).append(" ").append(hackerPos[1] + 1).append(" ").append(resultBlock);


        System.out.println(sb);
    }

    // 4 가지의 접근 방식 (위, 아래, 왼쪽, 오른쪽) 접근시 해당 블록 접근이 가능한지 판별.
    // 0 : 위 방향, 1 : 왼쪽 방향, 2 : 아래 방향, 3 : 오른쪽 방향
    public static boolean canAccess(char ch) {
        if(ch == 'Z')
            return true;

        // 이 파이프에 접근 할 때, 파이프 입장에서는 아래에서 연결
        if(currDirection == 0) {
            if(ch == '|'){
                return true;
            } else if(ch == '+') {
                return true;
            } else if(ch == '1') {
                return true;
            } else if(ch == '4') {
                return true;
            } else {
                return false;
            }
        } else if(currDirection == 1) { // 파이프 입장에서는 오른쪽에서 연결
            if(ch == '-') {
                return true;
            } else if(ch == '+') {
                return true;
            } else if(ch == '1') {
                return true;
            } else if(ch == '2') {
                return true;
            } else {
                return false;
            }
        } else if(currDirection == 2) { // 파이프 입장에서는 위에서 연결
            if(ch == '|') {
                return true;
            } else if(ch == '+') {
                return true;
            } else if(ch == '2') {
                return true;
            } else if(ch == '3') {
                return true;
            } else {
                return false;
            }
        } else if(currDirection == 3) { // 파이프 입장에서는 왼쪽에서 연결
            if(ch == '-') {
                return true;
            } else if(ch == '+') {
                return true;
            } else if(ch == '3') {
                return true;
            } else if(ch == '4') {
                return true;
            } else {
                return false;
            }
        } else { // 예상치 못한 값 ( 반환값 타입을 위함 )
            return false;
        }
    }

    // 현재 블록과 현재 방향성을 고려 한 뒤, 방향을 고침
    // 0 : 위 방향, 1 : 왼쪽 방향, 2 : 아래 방향, 3 : 오른쪽 방향, 4 : 해커가 없앤 블록이라는 의미
    public static int changeDirection(char ch) {
        // 만약 . 이 나왔을 경우, 해커가 없앤 블록 하나를 의미한다.

        // 현재 위 방향
        if(currDirection == 0) {
            if(ch == '|'){
                currDirection = 0;
                return 0;
            } else if(ch == '+') {
                currDirection = 0;
                return 0;
            } else if(ch == '1') {
                currDirection = 3;
                return 3;
            } else if(ch == '4') {
                currDirection = 1;
                return 1;
            } else { // 해커가 블록을 없애 . 이 된 장소
                return 4;
            }
        } else if(currDirection == 1) { // 현재 왼쪽 방향
            if(ch == '-') {
                currDirection = 1;
                return 1;
            } else if(ch == '+') {
                currDirection = 1;
                return 1;
            } else if(ch == '1') {
                currDirection = 2;
                return 2;
            } else if(ch == '2') {
                currDirection = 0;
                return 0;
            } else { // 해커가 블록을 없애 . 이 된 장소
                return 4;
            }
        } else if(currDirection == 2) { // 현재 아래 방향
            if(ch == '|') {
                currDirection = 2;
                return 2;
            } else if(ch == '+') {
                currDirection = 2;
                return 2;
            } else if(ch == '2') {
                currDirection = 3;
                return 3;
            } else if(ch == '3') {
                currDirection = 1;
                return 1;
            } else { // 해커가 블록을 없애 . 이 된 장소
                return 4;
            }
        } else { // currDirection == 3 ( 현재 오른쪽 방향 )
            if(ch == '-') {
                currDirection = 3;
            } else if(ch == '+') {
                currDirection = 3;
            } else if(ch == '3') {
                currDirection = 0;
            } else if(ch == '4') {
                currDirection = 2;
            } else { // 해커가 블록을 없애 . 이 된 장소
                return 4;
            }
        }
        return -1;
    }

    // 모스크바 좌표를 받고, 주변 4 방향을 살펴 초기 방향을 정해줌.
    public static void findStartDirection(int[] startPoint, char[][] map) {
        int y = startPoint[0];
        int x = startPoint[1];

        // 4 방향 탐색
        for(int i = 0; i < 4; i++) {
            int tempY = y + f1[i];
            int tempX = x + f2[i];

            // 4 방향 지점이 valid 할 때만 (맵을 벗어나지 않는다는 가정)
            if((tempX >= 0 && tempX < map[0].length) && (tempY >= 0 && tempY < map.length)) {
                char search = map[tempY][tempX];

                // 파이프를 찾음
                if(search != '.' && search != 'Z') {
                    // 해당 방향으로 설정 후 탈출
                    currDirection = i;
                    return;
                }
            }
        }
    }

    public static boolean isValidAxis(int y, int x, char[][] map) {
        if((x >= 0 && x < map[0].length) && (y >= 0 && y < map.length)) {
            return true;
        } else {
            return false;
        }

    }
}

