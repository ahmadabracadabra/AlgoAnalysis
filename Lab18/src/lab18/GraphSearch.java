//Graph serach algorithms using BRIDGES visualization (https://bridgesuncc.github.io)
//Created by James Vanderhyde, 31 March 2025
//Modified by Ahmad Abraham, 9 April 2025
package lab18;

import bridges.base.Color;
import bridges.base.Edge;
import bridges.base.Element;
import bridges.base.GraphAdjList;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphSearch {

    /**
     * Runs all the logic for the BRIDGES assignment. Call from main.
     *
     * @param bridges The initialized Bridges object
     * @throws java.io.IOException when there is a problem communicating with
     * the BRIDGES server.
     */
    public void run(Bridges bridges) throws IOException, RateLimitException {
        bridges.setTitle("Graph search algorithms");

        var highways = buildHighwayGraph();

        int numComponents = countAndLabelComponents(highways);

        System.out.println("Total components: " + numComponents);

        // Display result
        bridges.setDataStructure(highways);
        bridges.visualize();
    }

    private GraphAdjList<String, String, Void> depthFirstSearch( GraphAdjList<String, String, Double> g, String start, HashMap<String, Integer> vertexMarks, int label) {

        GraphAdjList<String, String, Void> searchTree = new GraphAdjList<>();

        // using a STACK as the bag
        Stack<Edge<String, Void>> bag = new Stack<>();

        // put (∅, start) in the bag
        bag.push(new Edge<>(null, start, null));

        while (!bag.isEmpty()) {
            // take (p, v) from the bag (*)
            Edge<String, Void> e = bag.pop();
            String p = e.getFrom();
            String v = e.getTo();

            if (vertexMarks.get(v) == 0) { // if v is unmarked
                vertexMarks.put(v, label); // mark v

                // add v to the search tree
                searchTree.addVertex(v, v);
                // color by component label instead of vertex name
                searchTree.getVertex(v).setColor(randomColor("" + label));

                if (p != null) { // parent(v) ← p
                    searchTree.addEdge(p, v);
                }

                // Check if adjacency list is null before iterating
                if (g.getAdjacencyList(v) != null) {
                    for (Edge<String, Double> vw : g.getAdjacencyList(v)) {
                        bag.push(new Edge<>(v, vw.getTo(), null));
                    }
                }
            }
        }
        return searchTree;
    }

    int countAndLabelComponents(GraphAdjList<String, String, Double> g) {
        int compLabel = 0;
        HashMap<String, Integer> vertexMarks = new HashMap<>();
        for (String v : g.getVertices().keySet()) {
            vertexMarks.put(v, 0);
        }

        for (String v : g.getVertices().keySet()) {
            if (vertexMarks.get(v) == 0) {
                compLabel++;
                this.depthFirstSearch(g, v, vertexMarks, compLabel);
            }
        }
        return compLabel;
    }

    private GraphAdjList<String, String, Void> breadthFirstSearch(GraphAdjList<String, String, Double> g, String start) {
        GraphAdjList<String, String, Void> searchTree = new GraphAdjList<>();
        HashMap<String, Boolean> vertexMarks = new HashMap<>();

        for (String v : g.getVertices().keySet()) {
            vertexMarks.put(v, false);
        }

        Queue<Edge<String, Void>> bag = new LinkedList<>();
        bag.add(new Edge<>(null, start, null));

        while (!bag.isEmpty()) {
            Edge<String, Void> e = bag.poll();
            String p = e.getFrom();
            String v = e.getTo();

            if (!vertexMarks.get(v)) {
                vertexMarks.put(v, true);

                searchTree.addVertex(v, v);
                searchTree.getVertex(v).setColor(g.getVertex(v).getColor());

                if (p != null) {
                    searchTree.addEdge(p, v);
                }

                for (Edge<String, Double> vw : g.getAdjacencyList(v)) {
                    bag.add(new Edge<>(v, vw.getTo(), null));
                }
            }
        }
        return searchTree;
    }

    private static GraphAdjList<String, String, Double> buildHighwayGraph() {
        //Start a graph
        GraphAdjList<String, String, Double> highways = new GraphAdjList<>();

        //Add vertices (cities) using the city name for the and for the vertex data
        highways.addVertex("Chicago", "Chicago");
        highways.addVertex("Detroit", "Detroit");
        highways.addVertex("Atlanta", "Atlanta");
        highways.addVertex("St. Louis", "St. Louis");
        highways.addVertex("New Orleans", "New Orleans");
        highways.addVertex("Birmingham", "Birmingham");
        highways.addVertex("Kansas City", "Kansas City");
        highways.addVertex("Indianapolis", "Indianapolis");
        highways.addVertex("Cincinnati", "Cincinnati");

        //Add edges (highways) using the length of the highway for the edge data
        highways.addEdge("Chicago", "Detroit", 286.0);
        highways.addEdge("Detroit", "Chicago", 286.0);
        highways.addEdge("Chicago", "Indianapolis", 183.0);
        highways.addEdge("Indianapolis", "Chicago", 183.0);
        highways.addEdge("St. Louis", "Chicago", 297.0);
        highways.addEdge("Chicago", "St. Louis", 297.0);
        highways.addEdge("Detroit", "Cincinnati", 264.0);
        highways.addEdge("Cincinnati", "Detroit", 264.0);
        highways.addEdge("Atlanta", "Cincinnati", 461.0);
        highways.addEdge("Cincinnati", "Atlanta", 461.0);
        highways.addEdge("Indianapolis", "Cincinnati", 112.0);
        highways.addEdge("Cincinnati", "Indianapolis", 112.0);
        highways.addEdge("Birmingham", "New Orleans", 342.0);
        highways.addEdge("New Orleans", "Birmingham", 342.0);

        // Some colors
        for (Element<String> vertex : highways.getVertices().values()) {
            vertex.setColor(randomColor(vertex.getValue()));
        }

        return highways;
    }

    private static Color randomColor(String s) {
        int hash = s.hashCode();
        float hue = (hash % 1000) / 1000.0f;
        java.awt.Color c555 = java.awt.Color.getHSBColor(hue, .5f, .8f);
        return new Color(c555.getRed(), c555.getGreen(), c555.getBlue());
    }
}
