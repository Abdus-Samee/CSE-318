public class Node {
    int moves;
    int distHamming;
    int distManhattan;
    String[][] grid;
    Node prev;

    public Node(int m, String[][] g, Node p){
        moves = m;
        grid = g;
        prev = p;
        calculateDistHamming();
    }

    public void setMoves(int m){ moves = m; }
    public void setGrid(String[][] g){ grid = g; }
    public void setPrev(Node p){ prev = p; }
    public void setDistHamilton(int distHamming){ this.distHamming = distHamming; }
    public void setDistManhattan(int distManhattan){ this.distManhattan = distManhattan; }

    public int getHammingHeuristic(){ return moves + distHamming; }
    public int getManhattanHeuristic(){ return moves + distManhattan; }

    public void calculateDistHamming(){
        int ans = 0;
        int k = 1;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j].equals("*")) continue;
                else if(!grid[i][j].equals(String.valueOf(k))) ans++;

                k++;
            }
        }

        distHamming = ans;
    }

    public void calculateDistManhattan(){

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
