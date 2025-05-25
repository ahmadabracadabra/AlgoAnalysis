//NAME: Ahmad Abraham
//CMPSC 351-01
//PP 21: Paths in graphs - Easiest mountain paths

package pp21;

import bridges.connect.Bridges;
import bridges.base.Color;
import bridges.base.ColorGrid;
import bridges.connect.DataSource;
import bridges.data_src_dependent.*;
import java.util.PriorityQueue;
import java.util.Arrays;

import java.lang.String;

public class PP21 {

    public static void main(String[] args) throws Exception {

        // bridges object initialization
        Bridges bridges = new Bridges(23, "abrahama02", "1070568054913");

        // datasource initialization
        DataSource ds = bridges.getDataSource();

        // get the elevation data - pass in lat/long rectangular bounding box
        // lat min, longit min, lat max, longit max
        ElevationData ele_data = ds.getElevationData(6.02, 44.10,
                9.70, 47.77, 0.02);

        // set title
        bridges.setTitle("Mountain Paths - Greedy Algorithms Example");
        bridges.setDescription("Your goal is to find a path that takes you through the points with the lowest elevation changes, in an effort to minimize the overall effort in walking through the path.");

        // get the path written into a color grid for visualization
        ColorGrid cg = getImage(ele_data);

        // find path by applying a greedy algorithm
        findPath(ele_data, ele_data.getRows() / 2, cg);

        // visualize
        bridges.setDataStructure(cg);
        bridges.visualize();
    }

    public static ColorGrid getImage(ElevationData elev) {
        // Use elev.getRows(), elev.getCols(), elev.get(r,c) to access data.
        int[][] data = elev.getData();
        int rows = elev.getRows();
        int cols = elev.getCols();

        // create colorgrid with ColorGrid(nbrows, nbcols)
        ColorGrid cg = new ColorGrid(rows, cols);

        //pick gray scale color interpolated from elev.getMinVal() to elev.getMaxVal()   
        int min = data[0][0];
        int max = data[0][0];

        // fill with .set(row, col, Color())
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int val = data[r][c];
                if (val < min) {
                    min = val;
                }
                if (val > max) {
                    max = val;
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int val = data[r][c];
                int gray = (int) (255.0 * (val - min) / (max - min));
                cg.set(r, c, new Color(gray, gray, gray));
            }
        }

        return cg;
    }

// determines the least effort path through the mountain starting a point on
// the left edge of the image
    public static void findPath(ElevationData elev, int startRow, ColorGrid cg) {
        // Run the greedy path from (0, startRow) to the right of the image
        int[][] data = elev.getData();
        int rows = elev.getRows();
        int cols = elev.getCols();

        int[][] dist = new int[rows][cols];
        int[][] prevRow = new int[rows][cols];
        int[][] prevCol = new int[rows][cols];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[startRow][0] = 0;
        pq.add(new Node(startRow, 0, 0));

        int[] dr = {-1, 0, 1};

        // always move right, but select the right cell, the top right cell, or bottom right cell
        // by minimizing the difference of elevation.
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int r = curr.row, c = curr.col;

            if (c == cols - 1) {
                break;
            }

            for (int d : dr) {
                int nr = r + d;
                int nc = c + 1;
                if (nr >= 0 && nr < rows) {
                    int cost = Math.abs(data[r][c] - data[nr][nc]);
                    if (dist[r][c] + cost < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + cost;
                        prevRow[nr][nc] = r;
                        prevCol[nr][nc] = c;
                        pq.add(new Node(nr, nc, dist[nr][nc]));
                    }
                }
            }
        }

        int minRow = 0;
        for (int r = 1; r < rows; r++) {
            if (dist[r][cols - 1] < dist[minRow][cols - 1]) {
                minRow = r;
            }
        }

        int r = minRow;
        int c = cols - 1;

        // Write path to the colorgrid
        while (c > 0) {
            cg.set(r, c, new Color(255, 0, 0));
            int pr = prevRow[r][c];
            int pc = prevCol[r][c];
            r = pr;
            c = pc;
        }
        cg.set(r, c, new Color(255, 0, 0));
    }

}
