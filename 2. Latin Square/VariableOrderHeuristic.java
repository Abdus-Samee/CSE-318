public class VariableOrderHeuristic {
    String heuristic;

    public VariableOrderHeuristic(String heuristic) { this.heuristic = heuristic; }

    public Variable getNextVariable(CSP csp, Assignment assignment){
        Variable var = null;
        if(this.heuristic.equals("VAH1")){
            int mn = -1;

            for(Variable v : csp.variableList){
                if(assignment.mp.get(v) < mn){
                    var = v;
                    mn = var.val;
                }
            }
        }

        return var;
    }
}
