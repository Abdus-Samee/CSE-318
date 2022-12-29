import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class LatinSquareCompletion {
    static String solver = "";
    static String heuristic = "";
    static int[][] grid;

    public static void main(String[] args) {
        readFromFile("src/data/d-15-01.txt");
        selectMenu();

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
        csp.calculateDegree(assignment);

        CSPSolver cspSolver = new CSPSolver(grid, heuristic);
        if(solver.equals("backtrack")){
            long start = System.currentTimeMillis();
            if(!cspSolver.solveBacktrack(csp, assignment)) System.out.println("No solution.");
            else{
                long end = System.currentTimeMillis();
                cspSolver.printCorrectAssignment(csp);
                System.out.println("\nTotal time: " + (end-start) + " ms");
                System.out.println("Total nodes: " + cspSolver.total_nodes);
                System.out.println("Backtracks: " + cspSolver.backtracks);
            }
        }else if(solver.equals("forward")){
            long start = System.currentTimeMillis();
            if(!cspSolver.solveForwardChecking(csp, assignment)) System.out.println("No solution.");
            else{
                long end = System.currentTimeMillis();
                cspSolver.printCorrectAssignment(csp);
                System.out.println("\nTotal time: " + (end-start) + " ms");
                System.out.println("Total nodes: " + cspSolver.total_nodes);
                System.out.println("Backtracks: " + cspSolver.backtracks);
            }
        }
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

    public static void selectMenu(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------SELECT SOLVER----------");
        System.out.println("(1)Backtracking\n(2)Forward Checking\n");
        String in = scanner.nextLine();
        if(in.equals("2")) solver = "forward";
        else solver = "backtrack";

        System.out.println("----------SELECT HEURISTIC----------");
        System.out.println("(1)VAH1\n(2)VAH2\n(3)VAH3\n(4)VAH4\n(5)VAH5\n");
        in = scanner.nextLine();
        if(in.equals("1")) heuristic = "VAH1";
        else if(in.equals("2")) heuristic = "VAH2";
        else if(in.equals("3")) heuristic = "VAH3";
        else if(in.equals("4")) heuristic = "VAH4";
        else heuristic = "VAH5";
        System.out.println("----------------------------------\n");
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
