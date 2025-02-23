//Starter code for BRIDGES projects (https://bridgesuncc.github.io)
//Created by James Vanderhyde, 13 February 2025
//Modified by Ahmad Abraham, 18 February 2025

package lab11;

import bridges.base.Circle;
import bridges.base.Polyline;
import bridges.base.SymbolCollection;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.City;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BridgesAppUSCities 
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
        bridges.setTitle("Accessing US Cities");
	bridges.setDescription("Long description");

        //Create the data connection object
        DataSource ds = bridges.getDataSource();
        
        //Use the DataSource object to download a data set from the server
         //US Cities data source
        HashMap<String, String> options = new HashMap<>();
        options.put ("min_pop", "500000");
        List<City> cities = ds.getUSCitiesData(options);
        
        //Read the data set into a data structure
          ArrayList<Point> dataPoints = new ArrayList<>();
        for (City c : cities) 
            dataPoints.add(new Point((int)c.getLongitude(), (int)c.getLatitude()));
        
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

       // Loop through points to find min and max
        for (Point p : dataPoints) {
           if (p.x < minX) minX = p.x;
           if (p.x > maxX) maxX = p.x;
           if (p.y < minY) minY = p.y;
           if (p.y > maxY) maxY = p.y;
        }
        
        //Set the window (the visible range of X and Y)
        Polyline boundingBox = new Polyline();
        boundingBox.setStrokeColor("blue");
        boundingBox.addPoint(minX, minY);
        boundingBox.addPoint(minX, maxY);
        boundingBox.addPoint(maxX, maxY);
        boundingBox.addPoint(maxX, minY);
        boundingBox.addPoint(minX, minY);
        scene.addSymbol(boundingBox);
        
        //  These numbers need to be set to something that works for the data set.
        scene.setViewport(minX, maxX, minY, maxY);
        
        //Add shapes to the SymbolCollection for the server to draw
        for (Point p : dataPoints)
        {
            Circle c = new Circle((float)p.x, (float)p.y, 1.0f);
            c.setFillColor("red");
            c.setOpacity(0.6f);
            scene.addSymbol(c);
        }
        
    }

}
