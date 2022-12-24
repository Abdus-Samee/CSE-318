import java.util.Random;

public class VariableOrderHeuristic {
    String heuristic;

    public VariableOrderHeuristic(String heuristic) { this.heuristic = heuristic; }

    public Variable getNextVariable(CSP csp){
        if(this.heuristic.equals("VAH1")) return firstHeuristic(csp);
        else if(this.heuristic.equals("VAH2")) return secondHeuristic(csp);
        else if(this.heuristic.equals("VAH3")) return thirdHeuristic(csp);
        else if(this.heuristic.equals("VAH4")) return fourthHeuristic(csp);
        else if(this.heuristic.equals("VAH5")) return fifthHeuristic(csp);

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

    public Variable thirdHeuristic(CSP csp){
        Variable var = null;
        int mn = Integer.MAX_VALUE;

        for(Variable v : csp.variableList){
            if(v.domain.size() < mn){
                var = v;
                mn = var.domain.size();
            }else if(v.domain.size() == mn){
                int first = v.getForwardDegree();
                int second = var.getForwardDegree();
                if(first >= second){
                    var = v;
                    mn = var.domain.size();
                }
            }
        }

        return var;
    }

    public Variable fourthHeuristic(CSP csp){
        Variable var = null;
        double mn = Double.MAX_VALUE;

        for(Variable v : csp.variableList){
            double val = (v.domain.size()*1.0) / v.getForwardDegree();
            if(val < mn){
                var = v;
                mn = val;
            }
        }

        return var;
    }

    public Variable fifthHeuristic(CSP csp){
        Random random = new Random();
        int idx = random.nextInt(csp.variableList.size());

        return csp.variableList.get(idx);
    }
}
