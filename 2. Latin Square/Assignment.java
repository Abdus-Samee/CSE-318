import java.util.HashMap;
import java.util.Map;

public class Assignment {
    Map<Variable, Integer> mp;

    public Assignment() { this.mp = new HashMap<>(); }

    public void add(Variable v, int i) { this.mp.put(v, i); }

    public int getVal(Variable v) { return mp.get(v); }
}
