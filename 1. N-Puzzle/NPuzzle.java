import java.util.Scanner;

public class NPuzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = Integer.parseInt(scanner.nextLine());
        String[][] grid = new String[k][k];
        for(int i = 0; i < k; i++){
            grid[i] = scanner.nextLine().split(" ");
        }

        if(solvable(grid)) System.out.println("Solvable");
        else System.out.println("Not solvable");
    }

    public static boolean solvable(String[][] ar){
        boolean solvable = false;

        int k = ar.length;
        int[] new_ar = new int[k*k-1];
        k = 0;
        for(int i = 0; i < ar.length; i++){
            for(int j = 0; j < ar[i].length; j++){
                if(!ar[i][j].equals("*")) new_ar[k++] = Integer.parseInt(ar[i][j]);
            }
        }

        int inversions = 0;
        for(int i = 0; i < new_ar.length; i++){
            for(int j = i+1; j < new_ar.length; j++){
                if(new_ar[i] > new_ar[j]) inversions++;
            }
        }

        k = ar.length;
        if(k%2==1 && inversions%2==0) solvable = true;
        else if(k%2==0 && inversions%2==1 && countBlankPos(ar)%2==0) solvable = true;
        else if(k%2==0 && inversions%2==0 && countBlankPos(ar)%2==1) solvable = true;

        return solvable;
    }

    public static int countBlankPos(String[][] ar){
        boolean done = false;
        int ans = -1;

        for(int i = ar.length-1; i >= 0; i--){
            for(int j = 0; j < ar[i].length; j++){
                if(ar[i][j] == "*"){
                    ans = i;
                    done = true;
                    break;
                }
            }
            if(done) break;
        }

        return ar.length-ans;
    }
}
