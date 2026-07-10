import java.util.*;

public class DrunkenMaze {

    static class State {
        int row, col;
        int lastDir;       
        int consecutive;   

        State(int row, int col, int lastDir, int consecutive) {
            this.row = row;
            this.col = col;
            this.lastDir = lastDir;
            this.consecutive = consecutive;
        }
    }

    static int[][] DIRS = {
        {0, 1},   
        {0, -1},  
        {1, 0},   
        {-1, 0}   
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] grid = new char[n][m];
        int startRow = 0, startCol = 0, targetRow = 0, targetCol = 0;

        for (int i = 0; i < n; i++) {
            String row = sc.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j);
                if (grid[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                }
                if (grid[i][j] == 'T') {
                    targetRow = i;
                    targetCol = j;
                }
            }
        }

        boolean[][][][] visited = new boolean[n][m][4][4];
        Queue<State> queue = new LinkedList<>();

        for (int d = 0; d < 4; d++) {
            visited[startRow][startCol][d][0] = true;
            queue.add(new State(startRow, startCol, d, 0));
        }

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                State current = queue.poll();

                if (current.row == targetRow && current.col == targetCol) {
                    System.out.println(distance);
                    return;
                }

                for (int nd = 0; nd < 4; nd++) {
                    int newRow = current.row + DIRS[nd][0];
                    int newCol = current.col + DIRS[nd][1];

                    if (newRow < 0 || newCol < 0 || newRow >= n || newCol >= m)
                        continue;
                    if (grid[newRow][newCol] == '#')
                        continue;

                    int newCount;
                    if (nd == current.lastDir)
                        newCount = current.consecutive + 1;
                    else
                        newCount = 1;

                    if (newCount > 3)
                        continue;

                    if (!visited[newRow][newCol][nd][newCount]) {
                        visited[newRow][newCol][nd][newCount] = true;
                        queue.add(new State(newRow, newCol, nd, newCount));
                    }
                }
            }
            distance++;
        }

        System.out.println(-1);
    }
}
