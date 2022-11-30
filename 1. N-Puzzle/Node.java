public class Node {
    int moves;
    String[][] grid;
    Node prev;

    public Node(int m, String[][] g, Node p){
        moves = m;
        grid = g;
        prev = p;
    }

    public void setMoves(int m){ moves = m; }
    public void setGrid(String[][] g){ grid = g; }
    public void setPrev(Node p){ prev = p; }

    public int getMoves(){ return moves; }
    public String[][] getGrid(){ return grid; }
    public Node getPrev(){ return prev; }
}
