public class CSPSolver {
    VariableOrderHeuristic vh;
    CSP csp;
    Assignment assignment;

    public CSPSolver(String heuristic, CSP csp, Assignment assignment) {
        this.vh = new VariableOrderHeuristic(heuristic);
        this.csp = new CSP();
        this.assignment = new Assignment();
    }

    public void solve(){

    }

    public int valueOrderHeuristic(Variable v) { return this.assignment.getVal(v); }
}
