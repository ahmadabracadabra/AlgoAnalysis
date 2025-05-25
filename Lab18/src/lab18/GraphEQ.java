//Use a graph to visualize earthquakes on the globe.
//Copied from https://bridgesuncc.github.io/assignments/data//4-GraphEQ/java.zip
//Modified by James Vanderhyde, 26 March 2025
//Modified by Ahmad Abraham, 9 April 2025

package lab18;

import bridges.base.*;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.EarthquakeUSGS;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Stack;

public class GraphEQ extends GraphSearch {

    public static double calcDistance(double la1, double lo1, double la2, double lo2) {
        final int R = 6371; // Radius of the earth in km
        // Haversine formula to calculate a value between 0 and 1 between 2 points on a sphere, 1 being the
        // opposite side of the sphere
        double laDistance = Math.toRadians(la2 - la1);
        double loDistance = Math.toRadians(lo2 - lo1);
        double a = Math.sin(laDistance / 2) * Math.sin(laDistance / 2)
                + Math.cos(Math.toRadians(la1)) * Math.cos(Math.toRadians(la2))
                * Math.sin(loDistance / 2) * Math.sin(loDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c;    //convert to km
        return distance;
    }

    /**
     * Runs all the logic for the BRIDGES assignment. Call from main.
     *
     * @param bridges The initialized Bridges object
     * @throws java.io.IOException when there is a problem communicating with
     * the BRIDGES server.
     * @throws bridges.validation.RateLimitException if the BRIDGES server is
     * overloaded.
     */
    public void run(Bridges bridges) throws java.io.IOException, bridges.validation.RateLimitException {
        //Set some information for the BRIDGES object
        bridges.setTitle("Earthquake Map");
        bridges.setDescription("Grab recent earthquake data and build a graph representing the locations of the strongest earthquakes.");

        //Create the data connection object
        DataSource ds = bridges.getDataSource();

        //Use the DataSource object to download a data set from the server
        List<EarthquakeUSGS> eq_list_full = ds.getEarthquakeUSGSData(5000);

        //Print frequencies of earth quake magnitudes
        int[] histogram = new int[10];
        for (EarthquakeUSGS eq : eq_list_full) {
            for (int i = 0; i < histogram.length; i++) {
                if ((i <= eq.getMagnitude()) && (eq.getMagnitude() < i + 1)) {
                    histogram[i]++;
                }
            }
        }
        for (int i = 0; i < histogram.length; i++) {
            System.out.println(histogram[i]);
        }

        //Filter to use only the highest magnitude quakes
        double minMag = 4;
        List<EarthquakeUSGS> eq_list = new ArrayList<>();
        for (EarthquakeUSGS eq : eq_list_full) {
            if (eq.getMagnitude() >= minMag) {
                eq_list.add(eq);
            }
        }

        //Read the data set into a visual data structure
        GraphAdjList<String, String, Double> graph = new GraphAdjList<>();
        for (EarthquakeUSGS eq : eq_list) {
            graph.addVertex(eq.getTitle(), eq.getTitle());
            graph.getVisualizer(eq.getTitle()).setLocation(eq.getLongit(), eq.getLatit());
        }

        //Create a scene 
        bridges.setDataStructure(graph);
        bridges.setMap(new WorldMap());
        bridges.visualize();

        //Add edges (if two quakes are within 500 km of each other)
        for (int i = 0; i < eq_list.size(); i++) {
            EarthquakeUSGS eq1 = eq_list.get(i);
            for (int j = i + 1; j < eq_list.size(); j++) {
                EarthquakeUSGS eq2 = eq_list.get(j);
                double distance = calcDistance(eq1.getLatit(), eq1.getLongit(), eq2.getLatit(), eq2.getLongit());

                if (distance < 500) {
                    graph.addEdge(eq1.getTitle(), eq2.getTitle(), distance);
                }
            }
        }

        //Calculate labels for components and color vertices
        int compLabel = 0;
        HashMap<String, Integer> vertexMarks = new HashMap<>();
        for (String v : graph.getVertices().keySet()) {
            vertexMarks.put(v, 0);
        }

        for (String v : graph.getVertices().keySet()) {
            if (vertexMarks.get(v) == 0) {
                compLabel++;
                depthFirstSearch(graph, v, vertexMarks, compLabel);
            }
        }
        System.out.println("Total EQ clusters: " + compLabel);
        
        

        bridges.visualize();

        //Remove location information of vertices to see just the structure
        for (EarthquakeUSGS eq : eq_list) {
            graph.getVisualizer(eq.getTitle()).setLocation(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        }
        bridges.setMapOverlay(false);
        bridges.visualize();
    }

    private void depthFirstSearch(GraphAdjList<String, String, Double> g, String start, HashMap<String, Integer> vertexMarks, int label) {
        Stack<Edge<String, Void>> bag = new Stack<>();
        bag.push(new Edge<>(null, start, null));

        while (!bag.isEmpty()) {
            Edge<String, Void> e = bag.pop();
            String p = e.getFrom();
            String v = e.getTo();

            if (vertexMarks.get(v) == 0) {
                vertexMarks.put(v, label);
                g.getVertex(v).setColor(randomColor("" + label));

                if (g.getAdjacencyList(v) != null) {
                    for (Edge<String, Double> vw : g.getAdjacencyList(v)) {
                        bag.push(new Edge<>(v, vw.getTo(), null));
                    }
                }
            }
        }
    }

    private static Color randomColor(String s) {
        int hash = s.hashCode();
        float hue = (hash % 1000) / 1000.0f;
        java.awt.Color c555 = java.awt.Color.getHSBColor(hue, .5f, .8f);
        return new Color(c555.getRed(), c555.getGreen(), c555.getBlue());
    }

}
