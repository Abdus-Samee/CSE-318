public class VariableOrderHeuristic {
    String heuristic;

    public VariableOrderHeuristic(String heuristic) { this.heuristic = heuristic; }

    public Variable getNextVariable(CSP csp){
        if(this.heuristic.equals("VAH1")) return firstHeuristic(csp);

        return null;
    }

    public Variable firstHeuristic(CSP csp){
        Variable var = null;
        int mn = Integer.MAX_VALUE;

        for(Variable v : csp.variableList){
            if(v.domain.size() < mn){
                var = v;
                mn = var.domain.size();
            }
        }

        return var;
    }

    public Variable secondHeuristic(CSP csp){
        Variable var = null;
        int mx = Integer.MIN_VALUE;

        for(Variable v : csp.variableList){
            if(v.getForwardDegree() > mx){
                var = v;
                mx = var.getForwardDegree();
            }
        }

        return var;
    }
}
