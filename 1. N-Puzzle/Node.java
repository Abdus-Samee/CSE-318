public class Node {
    int moves;
    int choice;
    int distHamming;
    int distManhattan;
    String[][] grid;
    String[][] goal;
    Node prev;

    public Node(int m, String[][] g, String[][] h, Node p, int c){
        moves = m;
        grid = g;
        goal = h;
        prev = p;
        choice = c;

        if(choice == 1) calculateDistHamming();
        else calculateDistManhattan();
    }

    public void setMoves(int m){ moves = m; }
    public void setGrid(String[][] g){ grid = g; }
    public void setPrev(Node p){ prev = p; }
    public void setDistHamilton(int distHamming){ this.distHamming = distHamming; }
    public void setDistManhattan(int distManhattan){ this.distManhattan = distManhattan; }

    public int getHammingHeuristic(){ return moves + distHamming; }
    public int getManhattanHeuristic(){ return moves + distManhattan; }
    public int getHeuristicValue(){ return (choice == 1)? getHammingHeuristic():getManhattanHeuristic(); }

    public void calculateDistHamming(){
        int ans = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j].equals("*")) continue;
                else if(!grid[i][j].equals(goal[i][j])) ans++;
            }
        }

        this.distHamming = ans;
    }

    public void calculateDistManhattan(){
        this.distManhattan = 0;
        int k = grid.length;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(!grid[i][j].equals("*")){
                    int x = (Integer.parseInt(grid[i][j])-1)/k;
                    int y = (Integer.parseInt(grid[i][j])-1)%k;
                    this.distManhattan += Math.abs(i-x) + Math.abs(j-y);
                }
            }
        }
    }

    public void printGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                System.out.printf(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean equalNode(String[][] g){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                if(!grid[i][j].equals(g[i][j])) return false;
            }
        }

        return true;
    }

    public String toString(){
        String res = "";

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                res += grid[i][j] + " ";
            }
        }

        return res;
    }
}
