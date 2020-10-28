import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GraphB {
    private ArrayList<ArrayList<Integer>> communicatingVertexList;
    private boolean[] visit;
    private Stack<Integer> stack;
    private ArrayList<Edge> edges;
    private ArrayList<Integer> result;

    public GraphB()
    {
        communicatingVertexList = new ArrayList<>();
    }

    private GraphB(GraphB copy)
    {
        this.communicatingVertexList = new ArrayList<>(copy.communicatingVertexList);
        for (int i = 0; i < copy.communicatingVertexList.size(); i++)
            this.communicatingVertexList.set(i, new ArrayList<>(copy.communicatingVertexList.get(i)));
    }

    public void addVertex()
    {
        communicatingVertexList.add(new ArrayList<>());
    }

    public void addEdge(int fromVertex, int toVertex, boolean oriented)
    {
        if (fromVertex != toVertex && fromVertex <= communicatingVertexList.size() && toVertex <= communicatingVertexList.size())
        {
            communicatingVertexList.get(fromVertex - 1).add(toVertex);
            rightOrder(fromVertex);
            if (!oriented)
            {
                communicatingVertexList.get(toVertex - 1).add(fromVertex);
                rightOrder(toVertex);
            }
        }
    }

    private void removeEdge(Integer fromVertex, Integer toVertex, boolean oriented)
    {
        communicatingVertexList.get(fromVertex - 1).remove(toVertex);
        if (!oriented)
            communicatingVertexList.get(toVertex - 1).remove(fromVertex);
    }

    public int[] tarryanAlgorithm()
    {
        int[] result = new int[communicatingVertexList.size()];
        visit = new boolean[communicatingVertexList.size()];
        stack = new Stack<>();

        for (int i = 0; i < communicatingVertexList.size(); i++)
            if (!visit[i])
                dfs(i + 1);

        for (int i = 0; i < communicatingVertexList.size(); i++)
            result[i] = stack.pop();

        return result;
    }

    public ArrayList<Edge> fleuryAlgorithm(int start)
    {
        for (ArrayList a : communicatingVertexList)
            if (a.size() % 2 == 1)
            {
                System.out.print("This graph has not Eulerian Path.");
                System.exit(0);
            }

        GraphB forFleury = new GraphB(this);
        forFleury.visit = new boolean[forFleury.communicatingVertexList.size()];
        forFleury.stack = new Stack<>();
        forFleury.edges = new ArrayList<>();

        forFleury.fleury_inv(start);

        return forFleury.edges;
    }

    public ArrayList<Integer> findEulerPath(int s)
    {
        for (ArrayList a : communicatingVertexList)
            if (a.size() % 2 == 1)
            {
                System.out.print("This graph has not Eulerian Path.");
                System.exit(0);
            }

        GraphB forAlgorithm = new GraphB(this);
        forAlgorithm.result = new ArrayList<>();

        forAlgorithm.findEulerPath_inv(s);

        return forAlgorithm.result;
    }

    public Stack<Integer> kosarajuAlgorithm()
    {
        stack = new Stack<>();
        visit = new boolean[communicatingVertexList.size()];

        GraphB transposedGraph = transpose();

        transposedGraph.stack = new Stack<>();
        transposedGraph.visit = new boolean[transposedGraph.communicatingVertexList.size()];

        for (int i = 0; i < transposedGraph.communicatingVertexList.size(); i++)
            if (!transposedGraph.visit[i])
                transposedGraph.dfs(i + 1);

        while (!transposedGraph.stack.isEmpty())
        {
            int v = transposedGraph.stack.pop();
            if (!visit[v - 1])
            {
                dfs(v);
                stack.push(0);// между последовательностями, образующими сильные компоненты связности,
            }                       //будет располагаться 0.
        }

        return stack;
    }

    private void rightOrder(int vertex)
    {
        int i = communicatingVertexList.get(vertex - 1).size() - 1;
        while (i != 0 && communicatingVertexList.get(vertex - 1).get(i) < communicatingVertexList.get(vertex - 1).get(i - 1))
        {
            int t = communicatingVertexList.get(vertex - 1).get(i);
            communicatingVertexList.get(vertex - 1).set(i, communicatingVertexList.get(vertex - 1).get(i - 1));
            communicatingVertexList.get(vertex - 1).set(i - 1, t);
            i--;
        }
    }

    private void dfs(int s)
    {
        visit[s - 1] = true;
        for (int i = 0; i < communicatingVertexList.get(s - 1).size(); i++)
            if (!visit[communicatingVertexList.get(s - 1).get(i) - 1])
                dfs(communicatingVertexList.get(s - 1).get(i));
        stack.push(s);
    }

    private void fleury_inv(int vertex)
    {
        for (int i = 0; i < communicatingVertexList.get(vertex - 1).size(); i++)
            if (canDeleteEdge(vertex, communicatingVertexList.get(vertex - 1).get(i)))
            {
                edges.add(new Edge(vertex, communicatingVertexList.get(vertex - 1).get(i)));
                int nextVertex = communicatingVertexList.get(vertex - 1).get(i);
                removeEdge(vertex, communicatingVertexList.get(vertex - 1).get(i), false);
                fleury_inv(nextVertex);
            }
    }

    private boolean canDeleteEdge(int fromVertex, int toVertex)
    {
        if (communicatingVertexList.get(fromVertex - 1).size() == 1)
            return true;

        dfs(fromVertex);
        int reachableVertexes1 = stack.size();
        removeEdge(fromVertex, toVertex, false);
        Arrays.fill(visit, false);
        stack.removeAllElements();

        dfs(fromVertex);
        int reachableVertexes2 = stack.size();
        Arrays.fill(visit, false);
        stack.removeAllElements();

        addEdge(fromVertex, toVertex, false);

        return reachableVertexes1 == reachableVertexes2;
    }

    private void findEulerPath_inv(int s)
    {
        for (int i = 0; i < communicatingVertexList.get(s - 1).size(); i++)
        {
            int nextVertex = communicatingVertexList.get(s - 1).get(i);
            removeEdge(s, nextVertex, false);
            findEulerPath_inv(nextVertex);
        }
        result.add(s);
    }

    private GraphB transpose()
    {
        GraphB transposeGraph = new GraphB();

        for (int i = 0; i < communicatingVertexList.size(); i++)
            transposeGraph.addVertex();

        for (int i = 0; i < communicatingVertexList.size(); i++)
            for (int j = 0; j < communicatingVertexList.get(i).size(); j++)
                transposeGraph.communicatingVertexList.get(communicatingVertexList.get(i).get(j) - 1).add(i + 1);

        return transposeGraph;
    }
}

class Edge{
    private int vertex1;
    private int vertex2;

    public Edge(int vertex1, int vertex2)
    {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public void printEdge()
    {
        System.out.print("(" + vertex1 + ", " + vertex2 + ") ");
    }
}