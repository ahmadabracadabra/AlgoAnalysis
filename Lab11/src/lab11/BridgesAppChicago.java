//Starter code for BRIDGES projects (https://bridgesuncc.github.io)
//Created by James Vanderhyde, 13 February 2025
//Modified by Ahmad Abraham, 18 February 2025

package lab11;

import bridges.base.Circle;
import bridges.base.Polyline;
import bridges.base.SymbolCollection;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.OsmData;
import bridges.data_src_dependent.OsmVertex;
import java.awt.Point;
import java.util.ArrayList;


public class BridgesAppChicago 
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
        bridges.setTitle("Accessing Chicago Intersections");
	bridges.setDescription("Long description");

        //Create the data connection object
        DataSource ds = bridges.getDataSource();
        
        //Use the DataSource object to download a data set from the server
         OsmData osm_data = ds.getOsmData("Chicago, Illinois", "secondary");
        OsmVertex[] ov = osm_data.getVertices();
        
        //Read the data set into a data structure
         ArrayList<Point.Float> dataPoints = new ArrayList<>();
            for (OsmVertex v : ov)
               dataPoints.add(new Point.Float((float)v.getLongitude(), (float)v.getLatitude()));

        
        //Process the data
        System.out.println(dataPoints.size()+" data points.");
        
        
        //Create a scene graph (SymbolCollection) object
        SymbolCollection scene = new SymbolCollection();
        bridges.setDataStructure(scene);
        
        //Loop through points to find min and max
        float minX = dataPoints.get(0).x;
        float maxX = dataPoints.get(0).x;
        float minY = dataPoints.get(0).y;
        float maxY = dataPoints.get(0).y;

       //Loop through points to find min and max
        for (Point.Float p : dataPoints) {
           if (p.x < minX) minX = p.x;
           if (p.x > maxX) maxX = p.x;
           if (p.y < minY) minY = p.y;
           if (p.y > maxY) maxY = p.y;
        }
        
        //Set the window (the visible range of X and Y)
        Polyline boundingBox = new Polyline();
        boundingBox.setStrokeColor("blue");
        boundingBox.setStrokeWidth(0.001f);
        boundingBox.addPoint(minX, minY);
        boundingBox.addPoint(minX, maxY);
        boundingBox.addPoint(maxX, maxY);
        boundingBox.addPoint(maxX, minY);
        boundingBox.addPoint(minX, minY);
        scene.addSymbol(boundingBox);
        
        //  These numbers need to be set to something that works for the data set.
        scene.setViewport(minX, maxX, minY, maxY);
        
        //Add shapes to the SymbolCollection for the server to draw
        for (Point.Float p : dataPoints)
        {
            Circle c = new Circle((float)p.x, (float)p.y, 0.0005f);
            c.setFillColor("red");
            c.setOpacity(0.6f);
            scene.addSymbol(c);
        }
        
    }

}
