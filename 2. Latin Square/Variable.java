import java.util.ArrayList;
import java.util.List;

public class Variable {
    List<Integer> domain;
    int n;
    int val;
    int row;
    int col;
    int forward_degree;
    boolean set;

    public Variable(int n, int row, int col){
        this.n = n;
        this.row = row;
        this.col = col;
        this.forward_degree = 0;
        this.set = false;
        this.val = -1;
        this.domain = new ArrayList<>();

        for(int i = 1; i <= n; i++) this.domain.add(i);
    }

    public void setVal(int val){
        this.val = val;
        this.set = true;
    }

    public void setForwardDegree(int forward_degree) { this.forward_degree = forward_degree; }

    public int getForwardDegree() { return this.forward_degree; }

    public void removeFromDomain(int val){
        int idx = this.domain.indexOf(val);

        if(idx != -1) this.domain.remove(idx);
    }

    public List<Variable> findNeighbours(Variable[][] varGrid){
        List<Variable> ans = new ArrayList<>();

        if(row-1 >= 0) ans.add(varGrid[row-1][col]);
        if(row+1 < n) ans.add(varGrid[row+1][col]);
        if(col-1 >= 0) ans.add(varGrid[row][col-1]);
        if(col+1 < n) ans.add(varGrid[row][col+1]);

        return ans;
    }
}

//each cell is a variable
//check if it's already assigned or not
//try assigning values from 1 to N
//maintain a min priority queue to store variables for VAH1
//maintain a max priority queue to store variables for VAH2
