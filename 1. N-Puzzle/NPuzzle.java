import java.util.Scanner;

public class NPuzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = Integer.parseInt(scanner.nextLine());
        String[][] grid = new String[k][k];
        for(int i = 0; i < k; i++){
            grid[i] = scanner.nextLine().split(" ");
        }
    }
}
