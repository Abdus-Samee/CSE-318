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

    public void calculateDegree(Assignment assignment){
        for(Variable v : variableList){
            for(int j = 0; j < varGrid.length; j++){
                if((j != v.col) && (assignment.getVal(varGrid[v.row][j]) == 0)) v.setForwardDegree(v.getForwardDegree()+1);
            }
            for(int i = 0; i < varGrid.length; i++){
                if((i != v.row) && (assignment.getVal(varGrid[i][v.col]) == 0)) v.setForwardDegree(v.getForwardDegree()+1);
            }
        }
    }

    public void updateDegree(Assignment assignment, Variable v, boolean increase){
        for(int j = 0; j < varGrid.length; j++){
            if((j != v.col) && (assignment.getVal(varGrid[v.row][j]) == 0)){
                if(increase) varGrid[v.row][j].setForwardDegree(varGrid[v.row][j].getForwardDegree()+1);
                else varGrid[v.row][j].setForwardDegree(varGrid[v.row][j].getForwardDegree()-1);
            }
        }

        for(int i = 0; i < varGrid.length; i++){
            if((i != v.row) && (assignment.getVal(varGrid[i][v.col]) == 0)){
                if(increase) varGrid[i][v.col].setForwardDegree(varGrid[i][v.col].getForwardDegree()+1);
                else varGrid[i][v.col].setForwardDegree(varGrid[i][v.col].getForwardDegree()-1);
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
