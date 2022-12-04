import java.util.HashSet;
import java.util.PriorityQueue;

public class Solver {
    int k;
    int choice;
    String[][] grid;

    public Solver(int k, int choice, String[][] grid){
        this.k = k;
        this.choice = choice;
        this.grid = grid;
    }

    public void solve(){
        PriorityQueue<Node> pq = new PriorityQueue<>(new CompareNode());
        HashSet<String> explored = new HashSet<>();

        Node root = new Node(0, grid, null, choice);
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
                Node newNode = new Node(n.moves+1, newGrid, n, choice);
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
                Node newNode = new Node(n.moves+1, newGrid, n, choice);
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
                Node newNode = new Node(n.moves+1, newGrid, n, choice);
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
                Node newNode = new Node(n.moves+1, newGrid, n, choice);
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
