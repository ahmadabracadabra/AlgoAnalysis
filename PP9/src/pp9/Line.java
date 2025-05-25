package pp9;

public class Line {
    float x0, y0, x1, y1;
    String label1, label2;

    boolean conv_hull = false, upper_hull = false, 
            lower_hull = false, dividing = false, extreme = false;

  
    public Line(float xx0, float yy0, float xx1, float yy1) {
        this.x0 = xx0; 
        this.y0 = yy0; 
        this.x1 = xx1; 
        this.y1 = yy1;
    }

 
    public Line(Point pt1, Point pt2) {
        this.x0 = pt1.getX(); 
        this.y0 = pt1.getY(); 
        this.x1 = pt2.getX(); 
        this.y1 = pt2.getY();
    }
}



