import java.util.Comparator;

public class CompareNode implements Comparator<Node> {
    int choice;

    public CompareNode(int choice){ this.choice = choice; }

    @Override
    public int compare(Node n1, Node n2) {
        //f(n)=g(n)+h(n)
        if(choice == 1){
            if(n1.getHammingHeuristic() > n2.getHammingHeuristic()) return 1;
            else if(n1.getHammingHeuristic() < n2.getHammingHeuristic()) return -1;
        }else{
            if(n1.getManhattanHeuristic() > n2.getManhattanHeuristic()) return 1;
            else if(n1.getManhattanHeuristic() < n2.getManhattanHeuristic()) return -1;
        }

        return 0;
    }
}
