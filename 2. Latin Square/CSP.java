import java.util.ArrayList;
import java.util.List;

public class CSP {
    Constraint constraint;
    Variable[][] varGrid;
    List<Variable> variableList;
    List<Variable> forwardList;

    public CSP(int n){
        this.varGrid = new Variable[n][n];
        this.variableList = new ArrayList<>();
        this.forwardList = new ArrayList<>();
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

    public void checkNeighbours(int r, int c, int d, Assignment assignment){
        this.forwardList.clear();

        for(int j = 0; j < varGrid.length; j++){
            if((j != c) && (assignment.getVal(varGrid[r][j]) == 0)){
                if(varGrid[r][j].domain.contains(d)){
                    varGrid[r][j].removeFromDomain(d);
                    this.forwardList.add(varGrid[r][j]);
                }
            }
        }

        for(int i = 0; i < varGrid.length; i++){
            if((i != r) && (assignment.getVal(varGrid[i][c]) == 0)){
                if(varGrid[i][c].domain.contains(d)){
                    varGrid[i][c].removeFromDomain(d);
                    this.forwardList.add(varGrid[i][c]);
                }
            }
        }
    }

    public boolean checkEmptyNeighbourDomain(){
        for(Variable v : this.forwardList){
            if(v.domain.size() == 0) return true;
        }

        return false;
    }

    public void resetDomain(Assignment assignment){
        for(Variable v : this.variableList){
            v.clearDomain();
            v.initDomain();

            for(int j = 0; j < varGrid.length; j++){
                int a = assignment.getVal(varGrid[v.row][j]);
                if((j != v.col) && (a != 0)) v.removeFromDomain(a);
            }

            for(int i = 0; i < varGrid.length; i++){
                int b = assignment.getVal(varGrid[i][v.col]);
                if((i != v.row) && (b != 0)) v.removeFromDomain(b);
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
