package pp9;

import java.util.*;

public class ConvHull {
    private List<Point> cvPoints = new ArrayList<>(); 
    private List<Line> cvLines = new ArrayList<>(); 
    private float[] bbox = null; 

   
    public ConvHull() {}

   
    public ConvHull(List<Point> points) {
        this.cvPoints = points;
        this.bbox = calculateBoundingBox(points);
    }

   
    public List<Point> getCvPoints() {
        return cvPoints;
    }


    public void setCvPoints(List<Point> points) {
        this.cvPoints = points;
        this.bbox = calculateBoundingBox(points);
    }

    
    public List<Line> getCvLines() {
        return cvLines;
    }


    public void setCvLines(List<Line> lines) {
        this.cvLines = lines;
    }

 
    public float[] getBoundingBox() {
        return bbox;
    }

  
    private float[] calculateBoundingBox(List<Point> points) {
        if (points.isEmpty()) return new float[]{0, 0, 0, 0}; 
        
        float minX = points.get(0).getX();
        float maxX = points.get(0).getX();
        float minY = points.get(0).getY();
        float maxY = points.get(0).getY();

        for (Point p : points) {
            if (p.getX() < minX) minX = p.getX();
            if (p.getX() > maxX) maxX = p.getX();
            if (p.getY() < minY) minY = p.getY();
            if (p.getY() > maxY) maxY = p.getY();
        }

        return new float[]{minX, maxX, minY, maxY};
    }

   
    public List<Line> getConvexHullBruteForce() {
        cvLines.clear(); 

        int n = cvPoints.size();
        if (n < 3) return cvLines; 

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point pt1 = cvPoints.get(i);
                Point pt2 = cvPoints.get(j);

                //ax + by = c
                double a = pt2.getY() - pt1.getY();
                double b = pt1.getX() - pt2.getX();
                double c = pt1.getX() * pt2.getY() - pt1.getY() * pt2.getX();

                boolean hasPositive = false, hasNegative = false;

              
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        Point pt3 = cvPoints.get(k);
                        double side = a * pt3.getX() + b * pt3.getY() - c;

                        if (side > 0) hasPositive = true;
                        if (side < 0) hasNegative = true;
                        if (hasPositive && hasNegative) {
                            break;
                        }
                    }
                }
                if (!(hasPositive && hasNegative)) {
                    Line line = new Line(pt1, pt2);
                    cvLines.add(line);
                }
            }
        }

        return cvLines;
    }
}
