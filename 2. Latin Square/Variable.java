import java.util.ArrayList;
import java.util.List;

public class Variable {
    List<Integer> domain;
    int val;
    boolean set;

    public Variable(int n){
        this.domain = new ArrayList<>();

        for(int i = 1; i <= n; i++) this.domain.add(i);
    }
}

//each cell is a variable
//check if it's already assigned or not
//try assigning values from 1 to N
//maintain a min priority queue to store variables for VAH1
//maintain a max priority queue to store variables for VAH2