import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {
    int k;
    int choice;
    String[][] grid;

    int total_moves;
    int explored_nodes;
    int expanded_nodes;

    public Solver(int k, int choice, String[][] grid){
        this.k = k;
        this.choice = choice;
        this.grid = grid;
    }

    public void solve(){
        PriorityQueue<Node> pq = new PriorityQueue<>(new CompareNode(choice));
        HashSet<String> explored = new HashSet<>();

        int t = 1;
        String[][] goal = new String[k][k];
        for(int i = 0; i < goal.length; i++){
            for(int j = 0; j < goal[i].length; j++){
                goal[i][j] = String.valueOf(t++);
            }
        }
        goal[k-1][k-1] = "*";

        Node root = new Node(0, grid, goal, null, choice);
        pq.add(root);

        int exploredNodes = 0, expandedNodes = 1;
        while(!pq.isEmpty()){
            Node n = pq.poll();

            if(!explored.contains(n.toString())){
                explored.add(n.toString());
                exploredNodes++;

                if(n.equalNode(goal)) {
                    printSolution(n);
                    total_moves = n.moves;
                    explored_nodes = exploredNodes;
                    expanded_nodes = expandedNodes;
                    break;
                }

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
                    Node newNode = new Node(n.moves+1, newGrid, goal, n, choice);
                    if(!explored.contains(newNode.toString())){
                        pq.add(newNode);
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
                    Node newNode = new Node(n.moves+1, newGrid, goal, n, choice);
                    if(!explored.contains(newNode.toString())){
                        pq.add(newNode);
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
                    Node newNode = new Node(n.moves+1, newGrid, goal, n, choice);
                    if(!explored.contains(newNode.toString())){
                        pq.add(newNode);
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
                    Node newNode = new Node(n.moves+1, newGrid, goal, n, choice);
                    if(!explored.contains(newNode.toString())){
                        pq.add(newNode);
                        expandedNodes++;
                    }
                }
            }
        }
    }

    public void printSolution(Node node){
        if(node == null) return;
        printSolution(node.prev);
        node.printGrid();
    }
}
