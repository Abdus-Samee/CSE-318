import java.util.ArrayList;
import java.util.List;

public class CSP {
    List<Variable> variableList;
    List<Constraint> constraintList;

    public CSP(){
        this.variableList = new ArrayList<>();
        this.constraintList = new ArrayList<>();
    }

    public void addVariable(Variable variable) { this.variableList.add(variable); }

    public void addConstraint(Constraint constraint) { this.constraintList.add(constraint); }
}
