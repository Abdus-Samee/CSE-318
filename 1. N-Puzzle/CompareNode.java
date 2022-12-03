import java.util.Comparator;

public class CompareNode implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        //f(n)=g(n)+h(n)
        if(n1.getHammingHeuristic() > n2.getHammingHeuristic()) return 1;
        else if(n1.getHammingHeuristic() < n2.getHammingHeuristic()) return -1;

        return 0;
    }
}
