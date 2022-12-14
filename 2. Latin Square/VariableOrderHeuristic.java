public class VariableOrderHeuristic {
    String heuristic;

    public VariableOrderHeuristic(String heuristic) { this.heuristic = heuristic; }

    public Variable getNextVariable(CSP csp){
        Variable var = null;
        if(this.heuristic.equals("VAH1")){
            int mn = Integer.MAX_VALUE;

            for(Variable v : csp.variableList){
                if(v.domain.size() < mn){
                    var = v;
                    mn = var.domain.size();
                }
            }
        }

        return var;
    }
}
