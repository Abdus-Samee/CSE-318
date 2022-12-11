import java.util.Scanner;

public class LatinSquareCompletion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[][] grid = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = scanner.nextInt();
            }
        }


    }

    public static void printGrid(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                System.out.printf(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
