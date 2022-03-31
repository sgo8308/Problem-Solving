package algorythm.Practice.programmers.step2;

public class GetGround {
    public static void main(String[] args) {
//        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
//        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 11}, {4, 3, 2, 15}, {7, 6, 9, 13}};
//        int[][] land = {{1,2,6,7},{2,3,10,8},{1,3,9,4},{7,11,9,4}};
        int[][] land = {{4, 3, 2, 1}, {2, 2, 2, 1}, {6, 6, 6, 4}, {8, 7, 6, 5}}; // 반례
        System.out.println(solution(land));
    }
//    static int max = Integer.MIN_VALUE;
//    static int solution(int[][] land) {
//        dfs(land, -1, 0, 0);
//        return max;
//    }
//
//    static void dfs(int[][] land, int nowCol, int depth, int sum) {
//        if (depth == land.length) {
//            max = Math.max(max, sum);
//            return;
//        } else {
//            for (int i = 0; i < 4; i++) {
//                if(i == nowCol) continue;
//                dfs(land, i, depth + 1, sum + land[depth][i]);
//            }
//        }
//    }

    static int solution(int[][] land) {
        // 그 줄에서 제일 큰 놈과 작은 놈을 구한다.
        // 그 줄까지 가장 큰 점수와 그 다음 점수를 구한다.
        // 만약 가장 큰 점수의 마지막 자리와 그 다음 점수의 마지막 자리 둘다 그 다음 줄에 가장 큰 놈을 선택할 수 있다면
        // 가장 큰 점수만을 이용해 그 다음 줄에 가장 큰 놈과 그 다음 큰 놈을 선택해서 다시 점수 2개를 만든다.
        // 마지막에 가장 큰 점수가 정답

        Candidate candA = new Candidate(-1, 0);
        Candidate candB = new Candidate(-1, 0);

        for (int i = 0; i < land.length; i++) {
            int[] nowLine = land[i];
            int candANextPlace = candA.findOptimalPlace(nowLine);
            int candBNextPlace = candB.findOptimalPlace(nowLine);

            if (candANextPlace == candBNextPlace) {
                if (candA.getScore() >= candB.getScore()) {
                    candB = candA.copy();
                    candBNextPlace = candB.findSecondOptimalPlace(nowLine);
                } else {
                    candA = candB.copy();
                    candANextPlace = candA.findSecondOptimalPlace(nowLine);
                }
            }

            candA.moveTo(candANextPlace);
            candB.moveTo(candBNextPlace);

            candA.updateScore(nowLine[candANextPlace]);
            candB.updateScore(nowLine[candBNextPlace]);
        }

        return Math.max(candA.getScore(), candB.getScore());
    }


}

class Candidate {
    private int nowPlace;
    private int score;

    public Candidate(int nowPlace, int score) {
        this.nowPlace = nowPlace;
        this.score = score;
    }

    public int findOptimalPlace(int[] line) {
        int max = Integer.MIN_VALUE;
        int optimalPlace = -1;
        for (int place = 0; place < line.length; place++) {
            if (place != nowPlace && max < line[place]){
                max = line[place];
                optimalPlace = place;
            }
        }
        return optimalPlace;
    }

    public int findSecondOptimalPlace(int[] line) {
        int optimalPlace = findOptimalPlace(line);
        int max = Integer.MIN_VALUE;
        int secondOptimalPlace = -1;
        for (int place = 0; place < line.length; place++) {
            if (place != nowPlace && place!= optimalPlace && max < line[place]){
                max = line[place];
                secondOptimalPlace = place;
            }
        }
        return secondOptimalPlace;

    }

    public Candidate copy() {
        return new Candidate(nowPlace, score);
    }

    public void moveTo(int place) {
        nowPlace = place;
    }

    public void updateScore(int i) {
        score += i;
    }

    public int getScore() {
        return score;
    }
}