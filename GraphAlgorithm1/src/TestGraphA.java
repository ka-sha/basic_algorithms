import java.util.ArrayList;

public class TestGraphA {
    public static void main(String[] args){
        GraphA graph1 = new GraphA();
        for (int i = 1; i <= 7; i++){
            graph1.addVertex();
        }
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 3);
        graph1.addEdge(2, 4);
        graph1.addEdge(2, 5);
        graph1.addEdge(3, 4);
        graph1.addEdge(3, 6);
        graph1.addEdge(3, 7);
        graph1.addEdge(4, 5);
        int[] result = graph1.dfs(1);
        System.out.print("dfs: ");
        for (int a : result) {
            System.out.print(a + " ");
        }
        System.out.print("\n");
        result = graph1.bfs(1);
        System.out.print("bfs: ");
        for (int a : result) {
            System.out.print(a + " ");
        }
        System.out.println();

        GraphA graph2 = new GraphA();
        for (int i = 1; i <= 7; i++)
            graph2.addVertex();
        graph2.addEdge(1, 2, 2);
        graph2.addEdge(1, 3, 1);
        graph2.addEdge(1, 4, 4);
        graph2.addEdge(1, 6, 6);
        graph2.addEdge(2, 4, 1);
        graph2.addEdge(2, 5, 3);
        graph2.addEdge(3, 6, 4);
        graph2.addEdge(3, 4, 2);
        graph2.addEdge(4, 7, 1);
        graph2.addEdge(5, 7, 4);
        graph2.addEdge(6, 7, 7);
        result = graph2.dijkstra(4);
        System.out.print("dijkstra: ");
        for (int a : result)
            System.out.print(a + " ");
        System.out.println();

        ArrayList<Edge> aGraph = graph2.kruskal();
        System.out.print("kruskal: ");
        for (Edge a : aGraph)
            System.out.printf("(%d, %d) ", a.getVertex1(), a.getVertex2());
        System.out.print("\n");

        aGraph = graph2.prim(5);
        System.out.print("prim:" );
        for (Edge a : aGraph)
            System.out.printf("(%d, %d) ", a.getVertex1(), a.getVertex2());
        System.out.println();

        GraphA graph3 = new GraphA();
        for (int i = 0; i < 7; i++)
            graph3.addVertex();
        graph3.addEdge(1, 2, 1);
        graph3.addEdge(1, 3, 2);
        graph3.addEdge(1, 4, 4);
        graph3.addEdge(2, 4, 2);
        graph3.addEdge(2, 5, 5);
        graph3.addEdge(3, 4, 7);
        graph3.addEdge(3, 6, 4);
        graph3.addEdge(3, 7, 1);
        graph3.addEdge(4, 5, 1);
        graph3.addEdge(4, 7, 1);

        int[][] D = graph3.FloydWarshell();
        System.out.println("Floyd-Warshell: ");
        for (int[] a : D){
            for (int b : a)
                System.out.print(b + " ");
            System.out.println();
        }
        GraphA graph4 = new GraphA();
        for (int i = 0; i < 7; i++)
            graph4.addVertex();
        graph4.addEdge(1, 2);
        graph4.addEdge(1, 3);
        graph4.addEdge(1, 4);
        graph4.addEdge(1, 6);
        graph4.addEdge(2, 5);
        graph4.addEdge(2, 4);
        graph4.addEdge(3, 6);
        graph4.addEdge(3, 4);
        graph4.addEdge(4, 7);
        graph4.addEdge(5, 7);
        graph4.addEdge(6, 7);
        result = graph4.dfs(1);
        System.out.print("dfs: ");
        for (int a : result)
            System.out.print(a + " ");
    }
}