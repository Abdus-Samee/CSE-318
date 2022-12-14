import java.util.ArrayList;
import java.util.List;

public class CSP {
    Constraint constraint;
    Variable[][] varGrid;
    List<Variable> variableList;

    public CSP(int n){
        this.varGrid = new Variable[n][n];
        this.variableList = new ArrayList<>();
    }

    public void updateDomain(){
        for(Variable v : variableList){
            for(int j = 0; j < varGrid.length; j++){
                Variable o = varGrid[v.row][j];
                if(o.val != 0) v.domain.remove(o.val);
            }
            for(int i = 0; i < varGrid.length; i++){
                Variable o = varGrid[i][v.col];
                if(o.val != 0) v.domain.remove(o.val);
            }
        }
    }
}
