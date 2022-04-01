package algorythm.Practice.Leetcode;

public class P_200_1 {

    public static void main(String[] args) {

    }
    //시간복잡도 O(nm) - 배열의 원소는 딱 한번씩만 탐색하고, 최악일 때는 모든 영역을 탐색해야 하기 때문에
    public int numIslands(char[][] grid) {
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (!visited[row][col] && grid[row][col] == 1) {
                    dfs(row, col, grid, visited);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int row, int col, char[][] grid, boolean[][] visited) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int xx = row + dx[i];
            int yy = col + dy[i];

            if (xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length && !visited[xx][yy] && grid[xx][yy] == 1) {
                dfs(xx, yy, grid, visited);
            }
        }
    }
}
