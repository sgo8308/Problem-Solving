package algorythm.Practice.programmers.CodeChallenge3;

public class Problem1_1 {
    public static void main(String[] args) {
        // 모든 노드에 대해서 동서남북으로 체크
        // 온 방향, 지금 만난 애 따져서 다음 갈 방향 정하고 노드에 올 때마다 경로 체크해서 처음과 비교하기
        String[] grid = {"SL","LR"};
    }

    public static int[] solution(String[] grid) {
        int[] answer = {};
        String[][] newGrid = new String[grid.length][];
        for (int i = 0; i < grid.length; i++)
            newGrid[i] = grid[i].split("");

        String[] directions = {"top", "bottom", "right", "left"};
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[0].length; j++) {
                Light light = new Light(i, j, newGrid[i][j]);

                for (String direction : directions) {
                    light.moveTo(direction);

//                    while()
                }


            }
        }


        return answer;
    }

    //다음 방향 정하기
    static String getNextDirection(String from, String nodeType) {
        switch (nodeType) {
            case "S":
                return from;
            case "L":
                switch (from) {
                    case "top":
                        return "right";
                    case "bottom":
                        return "left";
                    case "right":
                        return "bottom";
                    case "left":
                        return "top";
                }
                break;
            case "R":
                switch (from) {
                    case "top":
                        return "left";
                    case "bottom":
                        return "right";
                    case "right":
                        return "top";
                    case "left":
                        return "bottom";
                }

        }
        return "none";
    }

    //오른쪽으로 보낸거는 받는 입장에선 왼쪽이니까
    static String changeDirection(String dir){
        switch (dir){
            case "top":
                return "bottom";
            case "bottom":
                return "top";
            case "right":
                return "left";
            case "left":
                return "right";
        }
        return "none";
    }
}

class Light{
    int nowRow;
    int nowColumn;
    String nowNode;
    String firstRoute;
    String previousRoute;

    void moveTo(String direction){

    }

    public Light(int nowRow, int nowColumn, String nowNode) {
        this.nowRow = nowRow;
        this.nowColumn = nowColumn;
        this.nowNode = nowNode;
    }
}
