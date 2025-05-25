package pp9;

public class Point implements Comparable<Point> {
    float x, y;
    String name;

   
    public Point(float xx, float yy) {
        this.x = xx;
        this.y = yy;
        this.name = "";
    }


    public Point(float xx, float yy, String l) {
        this.x = xx;
        this.y = yy;
        this.name = l;
    }

   
    public Point() {
        this.x = 0;
        this.y = 0;
        this.name = "";
    }


    public String getName() {
        return name;
    }

   
    public float getX() {
        return x;
    }

   
    public float getY() {
        return y;
    }

  
    @Override
    public int compareTo(Point pt) {
        if (this.x < pt.x) return -1;
        else if (this.x > pt.x) return 1;
        else if (this.y < pt.y) return -1;
        else if (this.y > pt.y) return 1;
        return 0;
    }
}
