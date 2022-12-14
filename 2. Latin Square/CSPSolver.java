import java.util.List;

public class CSPSolver {
    int[][] grid;
    Assignment ans;
    VariableOrderHeuristic vh;

    public CSPSolver(int[][] grid, String heuristic) {
        this.grid = grid;
        this.ans = null;
        this.vh = new VariableOrderHeuristic(heuristic);
    }

    public boolean solveBacktrack(CSP csp, Assignment assignment){
        if(csp.constraint.holds(assignment, csp.varGrid)){
            this.ans = assignment;
            return true;
        }

        Variable v = this.vh.getNextVariable(csp);
        csp.variableList.remove(v);

        for(int d : v.domain){
            if(csp.constraint.consistentAssignment(v, d, csp.varGrid, assignment)){
                assignment.add(v, d);
                boolean res = solveBacktrack(csp, assignment);
                if(res) return true;
                assignment.mp.remove(v);
            }
            //else backtrack
        }

        csp.variableList.add(v);

        return false;
    }

    public void printCorrectAssignment(CSP csp){
        for(int i = 0; i < csp.varGrid.length; i++){
            for(int j = 0; j < csp.varGrid[i].length; j++){
                System.out.print(this.ans.getVal(csp.varGrid[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public int valueOrderHeuristic(Variable[][] varGrid, Variable v) {
        List<Variable> list = v.findNeighbours(varGrid);
        for(int d : v.domain){
            for(Variable n : list){
                Constraint constraint = new Constraint();
            }
        }

        return -1;
    }
}
