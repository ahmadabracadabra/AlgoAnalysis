package pp9;

import bridges.base.Circle;
import bridges.base.Polyline;
import bridges.base.SymbolCollection;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.City;
import bridges.validation.RateLimitException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class BridgesAppUSCities {
    /**
     * Runs all the logic for the BRIDGES assignment. Call from main.
     * @param bridges The initialized Bridges object
     * @throws java.io.IOException when there is a problem communicating
     *   with the BRIDGES server.
     */
    public void run(Bridges bridges) throws java.io.IOException, RateLimitException {
         //Set some information for the BRIDGES object
        bridges.setTitle("Convex Hull of US Cities");
	bridges.setDescription("PP9: Geometry Problems");
        
        ConvHull cv = new ConvHull();

        // Get US city locations
        DataSource ds = bridges.getDataSource();
        getInputPoints(ds, cv);


        float[] bbox = cv.getBoundingBox();


        Vector<Line> cvLines = new Vector<>(cv.getConvexHullBruteForce());


        // Draw the input points and lines that make up the convex hull
        SymbolCollection cvh = drawConvexHull(cv, cvLines);

        // Set the viewport based on the bounding box
        cvh.setViewport(bbox[0], bbox[2], bbox[1], bbox[3]);

        bridges.setDataStructure(cvh);
     
    }

   static void getInputPoints(DataSource ds, ConvHull cv) throws IOException {
    List<pp9.Point> dataPoints = new ArrayList<>();

    // Get the US Cities data
    HashMap<String, String> options = new HashMap<>();
    options.put("min_pop", "50000");  
    List<City> cities = ds.getUSCitiesData(options);

    for (City c : cities) {
        pp9.Point point = new pp9.Point(c.getLongitude(), c.getLatitude());
        dataPoints.add(point);
    }


    cv.setCvPoints(dataPoints);
}


    static SymbolCollection drawConvexHull(ConvHull cv, Vector<Line> cvLines) {
    SymbolCollection cvh = new SymbolCollection();

    // drawing city points
    List<pp9.Point> points = cv.getCvPoints();  
    for (pp9.Point p : points) {
        Circle cityCircle = new Circle((float) p.getX(), (float) p.getY(), 0.2f);  
        cityCircle.setFillColor("red");  
        cityCircle.setOpacity(0.6f);  
        cvh.addSymbol(cityCircle);  
    }

    // convex hull lines
    for (Line line : cvLines) {
        Polyline hullLine = new Polyline();
        hullLine.setStrokeColor("blue"); 
        hullLine.addPoint((float) line.x0, (float) line.y0);
        hullLine.addPoint((float) line.x1, (float) line.y1);
        cvh.addSymbol(hullLine);  
    }

    return cvh;
}


}
