import java.util.ArrayList;
import java.util.Stack;

public class TestGraphB {
    public static void main(String[] args){
        GraphB graph1 = new GraphB();
        for (int i = 0; i < 7; i++)
            graph1.addVertex();
        graph1.addEdge(1, 2, true);
        graph1.addEdge(1, 3, true);
        graph1.addEdge(2, 4, true);
        graph1.addEdge(3, 4, true);
        graph1.addEdge(3, 5, true);
        graph1.addEdge(4, 7, true);
        graph1.addEdge(5, 6, true);
        graph1.addEdge(6, 7, true);
        int[] forGraph1 = graph1.tarryanAlgorithm();
        System.out.print("Tarryan algorithm: ");
        for (int a : forGraph1)
            System.out.print(a + " ");
        System.out.print("\n");

        GraphB graph2 = new GraphB();
        for (int i = 0; i < 6; i++)
            graph2.addVertex();
        graph2.addEdge(1,2,false);
        graph2.addEdge(1,3,false);
        graph2.addEdge(1,4,false);
        graph2.addEdge(1,5,false);
        graph2.addEdge(2,3,false);
        graph2.addEdge(2,4,false);
        graph2.addEdge(2,5,false);
        graph2.addEdge(3,4,false);
        graph2.addEdge(3,6,false);
        graph2.addEdge(4,5,false);
        graph2.addEdge(5,6,false);
        ArrayList<Edge> forGraph2 = graph2.fleuryAlgorithm(1);
        System.out.print("Fleury algorithm from 1: ");
        for (Edge a : forGraph2)
            a.printEdge();
        System.out.print("\n");

        forGraph2 = graph2.fleuryAlgorithm(6);
        System.out.print("Fleury algorithm from 6: ");
        for (Edge a : forGraph2)
            a.printEdge();
        System.out.print("\n");

        ArrayList<Integer> forGraph2_1 = graph2.findEulerPath(6);
        System.out.print("Алгоритм на основе циклов из вершины 6: ");
        for (int a : forGraph2_1)
            System.out.print(a + " ");
        System.out.print("\n");

        forGraph2_1 = graph2.findEulerPath(4);
        System.out.print("Алгоритм на основе циклов из вершины 4: ");
        for (int a : forGraph2_1)
            System.out.print(a + " ");
        System.out.print("\n");

        GraphB graph3 = new GraphB();
        for (int i = 0; i < 8; i++)
            graph3.addVertex();
        graph3.addEdge(1, 4, false);
        graph3.addEdge(2, 1, true);
        graph3.addEdge(3, 2, true);
        graph3.addEdge(3, 5, true);
        graph3.addEdge(3, 7, false);
        graph3.addEdge(4, 5, true);
        graph3.addEdge(5, 2, true);
        graph3.addEdge(6, 3, true);
        graph3.addEdge(6, 5, true);
        graph3.addEdge(7, 6, true);
        graph3.addEdge(8, 5, true);
        graph3.addEdge(8, 7, true);
        Stack<Integer> forGraph3 = graph3.kosarajuAlgorithm();
        int n1 = forGraph3.size();
        System.out.print("Kosaraju Algorithm: ");
        for (int i = 0; i < n1; i++)
            System.out.print(forGraph3.pop() + " ");
        System.out.print("\n");

        GraphB graph4 = new GraphB();
        for (int i = 0; i < 8; i++)
            graph4.addVertex();
        graph4.addEdge(1, 5, true);
        graph4.addEdge(2, 1, true);
        graph4.addEdge(3, 2, true);
        graph4.addEdge(3, 4, false);
        graph4.addEdge(5, 2, true);
        graph4.addEdge(6, 5, true);
        graph4.addEdge(6, 2, true);
        graph4.addEdge(6, 7, false);
        graph4.addEdge(7, 3, true);
        graph4.addEdge(8, 4, true);
        graph4.addEdge(8, 7, true);
        Stack<Integer> forGraph4 = graph4.kosarajuAlgorithm();
        int n2 = forGraph4.size();
        System.out.print("Kosaraju algorithm: ");
        for (int i = 0; i < n2; i++)
            System.out.print(forGraph4.pop() + " ");
        System.out.print("\n");
    }
}