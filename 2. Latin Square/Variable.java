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
        this.forward_degree = 1;
        this.set = false;
        this.val = -1;
        this.domain = new ArrayList<>();

        initDomain();
    }

    public void setVal(int val){
        this.val = val;
        this.set = true;
    }

    public void setForwardDegree(int forward_degree) { this.forward_degree = forward_degree; }

    public int getForwardDegree() { return this.forward_degree; }

    public void initDomain(){
        for(int i = 1; i <= n; i++) this.domain.add(i);
    }

    public void addToDomain(int d) { this.domain.add(d); }

    public void removeFromDomain(int val){
        int idx = this.domain.indexOf(val);

        if(idx != -1) this.domain.remove(idx);
    }

    public void setDomain(ArrayList<Integer> domain) { this.domain = domain; }

    public void clearDomain() { this.domain.clear(); }
}
