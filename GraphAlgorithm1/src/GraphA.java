import java.util.*;

public class GraphA {
    private ArrayList<ArrayList<Integer>> communicatingVertexList;
    private ArrayList<ArrayList<Integer>> weights;

    public GraphA(){
        communicatingVertexList = new ArrayList<>();
        weights = new ArrayList<>();
    }

    public void addVertex(){
        communicatingVertexList.add(new ArrayList<>());
        weights.add(new ArrayList<>());
        for (int i = 0; i < weights.size() - 1; i++)
            weights.get(weights.size() - 1).add(0);// weights имеет треугольный вид
    }

    public void addEdge(int vertex1, int vertex2){
        int aVertex1 = vertex1 - 1, aVertex2 = vertex2 - 1;
        if (aVertex1 != aVertex2 && aVertex2 < communicatingVertexList.size() && aVertex1 < communicatingVertexList.size()){
            communicatingVertexList.get(aVertex1).add(vertex2);
            communicatingVertexList.get(aVertex2).add(vertex1);
        }
    }

    public void addEdge(int vertex1, int vertex2, int weight){
        int aVertex1 = vertex1 - 1, aVertex2 = vertex2 - 1;
        if (aVertex1 != aVertex2 && aVertex2 < communicatingVertexList.size() && aVertex1 < communicatingVertexList.size()){
            communicatingVertexList.get(aVertex1).add(vertex2);
            communicatingVertexList.get(aVertex2).add(vertex1);
            if (aVertex1 > aVertex2)
                weights.get(aVertex1).set(aVertex2, weight);
            else
                weights.get(aVertex2).set(aVertex1, weight);
        }
    }

    public int[] dfs(int s){
        boolean[] visit = new boolean[communicatingVertexList.size()];
        int i = 0, x;
        int[] result = new int[communicatingVertexList.size()];
        Stack<Integer> stack = new Stack<>();

        stack.push(s);
        visit[s - 1] = true;
        while (!stack.isEmpty()){
            x = stack.pop();
            result[i++] = x;
            for (int j = 0; j < communicatingVertexList.get(x - 1).size(); j++)
                if (!visit[communicatingVertexList.get(x - 1).get(j) - 1]) {
                    stack.push(communicatingVertexList.get(x - 1).get(j));
                    visit[communicatingVertexList.get(x - 1).get(j) - 1] = true;
                }
        }
        return result;
    }

    public int[] bfs(int s) {
        ArrayList<Integer> queueVertex = new ArrayList<>();
        boolean[] visit = new boolean[communicatingVertexList.size()];
        int[] result = new int[communicatingVertexList.size()];
        int i = 0, x;

        queueVertex.add(s);
        visit[s - 1] = true;
        while (!queueVertex.isEmpty()){
            x = queueVertex.get(0);
            queueVertex.remove(0);
            result[i++] = x;
            for (int j = 0; j < communicatingVertexList.get(x - 1).size(); j++)
                if (!visit[communicatingVertexList.get(x - 1).get(j) - 1]){
                    queueVertex.add(communicatingVertexList.get(x - 1).get(j));
                    visit[communicatingVertexList.get(x - 1).get(j) - 1] = true;
                }
        }
        return result;
    }

    public int[] dijkstra(int s){
        int[] D = new int[communicatingVertexList.size()];
        boolean[] U = new boolean[communicatingVertexList.size()];
        boolean allVisit = false;

        Arrays.fill(D, Integer.MAX_VALUE);
        Arrays.fill(U, false);
        D[s - 1] = 0;

        while (!allVisit) {
            int v = 0, min = Integer.MAX_VALUE;
            for (int j = 0; j < D.length; j++)
                if (!U[j] && D[j] < min) {
                    min = D[j];
                    v = j;
                }
            U[v] = true;

            int j = 0;
            while (j < communicatingVertexList.get(v).size()) {
                int u = communicatingVertexList.get(v).get(j) - 1;
                if (v > u) {
                    if (!U[u] && D[u] > D[v] + weights.get(v).get(u))
                        D[u] = D[v] + weights.get(v).get(u);
                } else if (!U[u] && D[u] > D[v] + weights.get(u).get(v))
                    D[u] = D[v] + weights.get(u).get(v);
                j++;
            }

            allVisit = U[0];
            for (int i = 1; i < U.length; i++)
                allVisit = U[i] && allVisit;
        }
        return D;
    }

    public ArrayList<Edge> kruskal(){
        int[] D = new int[communicatingVertexList.size()];
        ArrayList<Edge> result = new ArrayList<>();

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < weights.size(); i++)
            for (int j = 0; j < weights.get(i).size(); j++)
                if (weights.get(i).get(j) != 0)
                    edges.add(new Edge(j + 1, i + 1, weights.get(i).get(j)));
        Edge[] aEdges = new Edge[edges.size()];
        aEdges = edges.toArray(aEdges);

        Arrays.sort(aEdges);

        for (int i = 0; i < D.length; i++)
            D[i] = i;

        for (Edge a : aEdges)//for (int i = 0; i < aEdges.length; i++)
            if (D[a.getVertex1() - 1] != D[a.getVertex2() - 1]){
                result.add(a);
                int t = D[a.getVertex1() - 1];
                for (int j = 0; j < D.length; j++)
                    if (D[j] == t)
                        D[j] = D[a.getVertex2() - 1];
            }
        return result;
    }

    public ArrayList<Edge> prim(int s){
        int[] D = new int[communicatingVertexList.size()];
        int[] parent = new int[communicatingVertexList.size()];
        boolean[] visit = new boolean[communicatingVertexList.size()];
        boolean allVisit = false;

        Arrays.fill(D, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        Arrays.fill(visit, false);
        D[s - 1] = 0;

        while (!allVisit) {
            int n = 0, min = Integer.MAX_VALUE;
            for (int j = 0; j < D.length; j++)
                if (!visit[j] && D[j] < min) {
                    min = D[j];
                    n = j;
                }
            visit[n] = true;

            int j = 0;
            while (j < communicatingVertexList.get(n).size()) {
                int v = communicatingVertexList.get(n).get(j) - 1;
                if (v < n)
                {
                    if (!visit[v] && weights.get(n).get(v) < D[v])
                    {
                        D[v] = weights.get(n).get(v);
                        parent[v] = n;
                    }
                }
                else if (!visit[v] && weights.get(v).get(n) < D[v])
                {
                    D[v] = weights.get(v).get(n);
                    parent[v] = n;
                }
                j++;
            }

            allVisit = visit[0];
            for (int i = 1; i < visit.length; i++)
                allVisit = visit[i] && allVisit;
        }

        ArrayList<Edge> result = new ArrayList<>();
        for (int i = 0; i < communicatingVertexList.size(); i++)
            if (parent[i] != -1)
                result.add(new Edge(i + 1, parent[i] + 1));
        return result;
    }

    public int[][] FloydWarshell(){
        int[][] D = new int[communicatingVertexList.size()][communicatingVertexList.size()];
        for (int i = 0; i < communicatingVertexList.size(); i++){
            D[i][i] = 0;
            for (int j = 0; j < weights.get(i).size(); j++){
                if (weights.get(i).get(j) == 0)
                    D[i][j] = D[j][i] = Integer.MAX_VALUE/2;
                else
                    D[i][j] = D[j][i] = weights.get(i).get(j);
            }
        }

        int n = communicatingVertexList.size();
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
        return D;
    }
}

class Edge implements Comparable<Edge>{
    private int vertex1;
    private int vertex2;
    private int weight;

    public Edge(int vertex1, int vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Edge(int vertex1, int vertex2, int weight){
        this(vertex1, vertex2);
        this.weight = weight;
    }

    public int compareTo(Edge other){
        return weight - other.weight;
    }

    public int getVertex1(){
        return vertex1;
    }
    public int getVertex2(){
        return vertex2;
    }
}