import java.io.BufferedReader;
import java.io.FileReader;

public class LatinSquareCompletion {
    static int[][] grid;

    public static void main(String[] args) {
        readFromFile("src/data/d-10-06.txt");

        int n = grid.length;
        CSP csp = new CSP(n);
        Assignment assignment = new Assignment();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Variable v = new Variable(n, i, j);
                assignment.add(v, grid[i][j]);
                csp.varGrid[i][j] = v;
                if(grid[i][j] == 0) csp.variableList.add(v);
            }
        }

        Constraint constraint = new Constraint();
        csp.constraint = constraint;
        csp.updateDomain(assignment);

        CSPSolver cspSolver = new CSPSolver(grid, "VAH1");
        if(!cspSolver.solveBacktrack(csp, assignment)) System.out.println("No solution.");
        else cspSolver.printCorrectAssignment(csp);
    }

    public static void readFromFile(String file){
        int c = -1;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while(true){
                String in = reader.readLine();
                if(in == null) break;


                if(c == -1){
                    int N = Integer.parseInt(in.replaceAll("[^0-9]+", ""));
                    grid = new int[N][N];
                    reader.readLine();
                    reader.readLine();
                    c++;
                }else{
                    int i = 0;
                    String[] ar = in.replaceAll("[,|\\];]", "").trim().split(" ");
                    for(String s : ar) grid[c][i++] = Integer.parseInt(s);
                    c++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void printGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                System.out.printf(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
