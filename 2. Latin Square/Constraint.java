public class Constraint {
    Variable v1;
    Variable v2;
    boolean integrity;

    public Constraint(Variable v1, Variable v2){
        this.v1 = v1;
        this.v2 = v2;

        this.integrity = checkIntegrity(this.v1, this.v2);
    }

    public boolean checkIntegrity(Variable v1, Variable v2){
        if(v1.val != v2.val) return true;

        return false;
    }

    public boolean holds(Assignment assignment){
        int i = 0;
        int n = assignment.mp.size();
        Variable[] ar = new Variable[n];
        for(Variable v : assignment.mp.keySet()){
            ar[i++] = v;
        }

        for(i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(!checkIntegrity(ar[i], ar[j])) return false;
            }
        }

        return true;
    }
}
