package lab18;
//Graph example using BRIDGES visualization (https://bridgesuncc.github.io)
//Created by James Vanderhyde, 26 March 2025
//Modified by Ahmad Abraham, 27 March 2025

import bridges.base.Edge;
import bridges.base.GraphAdjList;
import bridges.connect.Bridges;

public class BridgesApp 
{
    /**
     * Runs all the logic for the BRIDGES assignment. Call from main.
     * @param bridges The initialized Bridges object
     * @throws java.io.IOException when there is a problem communicating
     *   with the BRIDGES server.
     */
    public void run(Bridges bridges) throws java.io.IOException
    {
        //Set some information for the BRIDGES object
        bridges.setTitle("Highway Graph");

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
        highways.addEdge("Detroit", "Atlanta", 722.0);
        highways.addEdge("Atlanta", "Detroit", 722.0);
        
        //Missing edges (highways)
        highways.addEdge("St. Louis", "Kansas City", 248.0);
        highways.addEdge("Kansas City", "St. Louis", 248.0);
        highways.addEdge("Indianapolis", "Kansas City", 482.0);
        highways.addEdge("Kansas City", "Indianapolis", 482.0);
        highways.addEdge("St. Louis", "Indianapolis", 242.0);
        highways.addEdge("Indianapolis", "St. Louis", 242.0);
        highways.addEdge("Cincinnati", "Indianapolis", 112.0);
        highways.addEdge("Indianapolis", "Cincinnati", 112.0);
        highways.addEdge("Birmingham", "Indianapolis", 417.0);
        highways.addEdge("Indianapolis", "Birmingham", 417.0);
        highways.addEdge("Birmingham", "Chicago", 580.0);
        highways.addEdge("Chicago", "Birmingham", 580.0);
        highways.addEdge("Atlanta", "Birmingham", 147.0);
        highways.addEdge("Birmingham", "Atlanta", 147.0);
        highways.addEdge("Birmingham", "New Orleans", 344.0);
        highways.addEdge("New Orleans", "Birmingham", 344.0);
        highways.addEdge("Detroit", "Cincinnati", 263.0);
        highways.addEdge("Cincinnati", "Detroit", 263.0);
        highways.addEdge("St. Louis", "New Orleans", 676.0);
        highways.addEdge("New Orleans", "St. Louis", 676.0);
        //finish
        
        //Create a scene
        bridges.setDataStructure(highways);
        
        //Print the adjacency list of Chicago, using outgoingEdgeSetOf and a for loop
        //https://bridgesuncc.github.io/doc/java-api/current/html/classbridges_1_1base_1_1_graph_adj_list.html
        // Print the adjacency list of Chicago
        for (Edge<String, Double> e : highways.outgoingEdgeSetOf("Chicago")) {
            System.out.println("Chicago -> " + e.getTo());
        }

        
    } //main

} //class

