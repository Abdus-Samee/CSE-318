public class Constraint {
    Variable v1;
    Variable v2;
    boolean integrity;
    int c = 0;

    public Constraint(){}

    public Constraint(Variable v1, Variable v2){
        this.v1 = v1;
        this.v2 = v2;

        //this.integrity = checkIntegrity(this.v1, this.v2);
    }

    public boolean checkIntegrity(Variable v1, Variable v2){
        if(v1.val != v2.val) return true;

        return false;
    }

    public boolean consistentAssignment(Variable v, int val, Variable[][] varGrid, Assignment assignment){
        int r = v.row;
        int c = v.col;
        for(int j = 0; j < varGrid.length; j++){
            if((c != j) && assignment.getVal(varGrid[r][j]) == val) return false;
        }
        for(int i = 0; i < varGrid.length; i++){
            if((r != i) && assignment.getVal(varGrid[i][c]) == val) return false;
        }

        return true;
    }

    public boolean holds(Assignment assignment, Variable[][] varGrid){
        for(int i = 0; i < varGrid.length; i++){
            for(int j = 0; j < varGrid[i].length; j++){
                if(assignment.getVal(varGrid[i][j]) == 0) return false;
            }
        }

        return true;
    }
}
