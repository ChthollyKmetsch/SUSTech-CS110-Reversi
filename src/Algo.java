import java.util.ArrayList;

public class Algo {
    private int[] dx = {1,1,1,0,0,-1,-1,-1};
    private int[] dy = {0,1,-1,1,-1,1,-1,0};
    private int[][] map = new int[10][10];
    private ArrayList<ValidMoves> validMoves = new ArrayList<ValidMoves>();
    public void initiate() {
        this.map[4][4] = 2;
        this.map[5][5] = 2;
        this.map[4][5] = 1;
        this.map[5][4] = 1;
    }
    public void findValidPlace(int currentOperator) {
        for (int i = 1; i <= 8; ++i) {
            for (int j = 1; j <= 8; ++j) { // The 2 loops find the coordinate of the a chess
                if (map[i][j] != currentOperator) continue;
                for (int dir = 0; dir < 8; ++dir) { // Search along 8 directions
                    boolean atLeastAOpposite = false;
                    int steps = 1;
                    int nx = i + dx[dir], ny = j + dy[dir];
                    while (!isLimitOverflow(nx,ny)) { // Search along a direction
                        if (this.map[nx][ny] == 0 && atLeastAOpposite) {
                            ValidMoves n = new ValidMoves(nx,ny,steps,7-dir);
                            validMoves.add(n);
                            break;
                        } else if (this.map[nx][ny] == 0 && !atLeastAOpposite) {
                            break;
                        } else if (this.map[nx][ny] == (currentOperator == 1 ? 2 : 1)) { // Opposite color, keeping on searching
                            atLeastAOpposite = true;
                        } else break;
                        nx = nx + dx[dir];
                        ny = ny + dy[dir];
                        ++steps;
                    }
                }
            }
        }
    }
    private int idxOfNextMove(int x, int y) {
        boolean flag = false;
        int idxOfValidMoves = -1;
        for (int i = 0; i < validMoves.size(); ++i) {
            if (validMoves.get(i).x == x && validMoves.get(i).y == y) {
                idxOfValidMoves = i;
                break;
            }
        }
        return idxOfValidMoves;
    }
    public boolean placeChess(int x, int y, int currentOperator) {
        int idx = idxOfNextMove(x,y);
        if (idx == -1) {
            return false;
        }

        return true;
    }
    public void printValidPlace() {
        for (int i = 0; i < validMoves.size(); ++i) {
            System.out.printf("x=%d, y=%d, len=%d, negdir=%d\n",
                    validMoves.get(i).x, validMoves.get(i).y, validMoves.get(i).len, validMoves.get(i).negDirIdx);
        }
    }
    private boolean isLimitOverflow(int x, int y) {
        return x > 8 || x < 1 || y > 8 || y < 1;
    }
}
