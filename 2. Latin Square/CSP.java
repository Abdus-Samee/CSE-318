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

    public void updateDomain(Assignment assignment){
        for(Variable v : variableList){
            for(int j = 0; j < varGrid.length; j++){
                if(j != v.col){
                    int val = assignment.getVal(varGrid[v.row][j]);
                    if(val != 0) v.removeFromDomain(val);
                }
            }
            for(int i = 0; i < varGrid.length; i++){
                if(i != v.row){
                    int val = assignment.getVal(varGrid[i][v.col]);
                    if(val != 0) v.removeFromDomain(val);
                }
            }
        }
    }

    public void printGrid(Assignment assignment){
        for(int i = 0; i < varGrid.length; i++){
            for(int j = 0; j < varGrid[i].length; j++){
                System.out.printf(assignment.getVal(varGrid[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
