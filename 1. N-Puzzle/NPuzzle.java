import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NPuzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = Integer.parseInt(scanner.nextLine());
        String[][] grid = new String[k][k];
        for(int i = 0; i < k; i++){
            grid[i] = scanner.nextLine().split(" ");
        }

        if(!solvable(grid)) System.out.println("Not solvable");
        else{
            System.out.println("Solvable");
            PriorityQueue<Node> pq = new PriorityQueue<>(new CompareNode());
            HashSet<String> explored = new HashSet<>();

            Node root = new Node(0, grid, null);
            pq.add(root);
            explored.add(root.toString());

            int t = 1;
            String[][] goal = new String[k][k];
            for(int i = 0; i < goal.length; i++){
                for(int j = 0; j < goal[i].length; j++){
                    goal[i][j] = String.valueOf(t++);
                }
            }
            goal[k-1][k-1] = "*";

            int exploredNodes = 0, expandedNodes = 1;
            while(!pq.isEmpty()){
                Node n = pq.poll();
                exploredNodes++;
                if(n.equalNode(goal)) {
                    n.printGrid();
                    System.out.println("Total moves: " + n.moves);
                    break;
                }
                n.printGrid();

                int x = 0,y = 0;
                for(int i = 0; i < k; i++){
                    for(int j = 0; j < k; j++){
                        if(n.grid[i][j].equals("*")){
                            x = i;
                            y = j;
                            break;
                        }
                    }
                }

                if(x > 0){
                    String[][] newGrid = new String[k][k];
                    for(int i = 0; i < k; i++){
                        for(int j = 0; j < k; j++){
                            newGrid[i][j] = n.grid[i][j];
                        }
                    }
                    newGrid[x][y] = newGrid[x-1][y];
                    newGrid[x-1][y] = "*";
                    Node newNode = new Node(n.moves+1, newGrid, n);
                    if(!explored.contains(newNode.toString())){
                        pq.add(newNode);
                        explored.add(newNode.toString());
                        expandedNodes++;
                    }
                }
                if(x < k-1){
                    String[][] newGrid = new String[k][k];
                    for(int i = 0; i < k; i++){
                        for(int j = 0; j < k; j++){
                            newGrid[i][j] = n.grid[i][j];
                        }
                    }
                    newGrid[x][y] = newGrid[x+1][y];
                    newGrid[x+1][y] = "*";
                    Node newNode = new Node(n.moves+1, newGrid, n);
                    if(!explored.contains(newNode.toString())){
                        pq.add(newNode);
                        explored.add(newNode.toString());
                        expandedNodes++;
                    }
                }
                if(y > 0){
                    String[][] newGrid = new String[k][k];
                    for(int i = 0; i < k; i++){
                        for(int j = 0; j < k; j++){
                            newGrid[i][j] = n.grid[i][j];
                        }
                    }
                    newGrid[x][y] = newGrid[x][y-1];
                    newGrid[x][y-1] = "*";
                    Node newNode = new Node(n.moves+1, newGrid, n);
                    if(!explored.contains(newNode.toString())){
                        pq.add(newNode);
                        explored.add(newNode.toString());
                        expandedNodes++;
                    }
                }
                if(y < k-1){
                    String[][] newGrid = new String[k][k];
                    for(int i=0;i<k;i++){
                        for(int j=0;j<k;j++){
                            newGrid[i][j] = n.grid[i][j];
                        }
                    }
                    newGrid[x][y] = newGrid[x][y+1];
                    newGrid[x][y+1] = "*";
                    Node newNode = new Node(n.moves+1, newGrid, n);
                    if(!explored.contains(newNode.toString())){
                        pq.add(newNode);
                        explored.add(newNode.toString());
                        expandedNodes++;
                    }
                }
            }

            System.out.println("Number of explored nodes: " + exploredNodes);
            System.out.println("Number of expanded nodes: " + expandedNodes);
        }
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
                if(ar[i][j].equals("*")){
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
