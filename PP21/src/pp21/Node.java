package pp21;

/**
 *
 * @author ahmadabraham
 */

class Node implements Comparable<Node> {

    int row;
    int col;
    int cost;

    Node(int r, int c, int cost) {
        this.row = r;
        this.col = c;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}
