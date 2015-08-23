package data_structures;

/*
 * A quadtree is a tree data structure where every internal node has four children
 * It is used to partition a two dimensional space into quadrants
 */

public class Quadtree {
    
    // object representing the rpot of the tree
    private Node root;
    
    // class that represents the nodes of the tree
    private class Node {
        // coordinates of the point and data
        int x, y, value;
        // four quadrants
        Node UL, UR, DL, DR;
        Node (int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    /**
     * Inserting a point into the quadtree
     * @param x     x-coordinate
     * @param y     y-coorindate
     * @param value data associated with the point
     */
    public void insert (int x, int y, int value) {
        root = insert(root, x, y, value);
    }
    private Node insert (Node n, int x, int y, int value) {
        if (n == null)
            return new Node(x, y, value);
        if (x < n.x && y < n.y)
            n.UL = insert(n.UL, x, y, value);
        else if (x >= n.x && y < n.y)
            n.UR = insert(n.UR, x, y, value);
        else if (x < n.x && y >= n.y)
            n.DL = insert(n.DL, x, y, value);
        else if (x >= n.x && y >= n.y)
            n.DR = insert(n.DR, x, y, value);
        return n;
    }
    /**
     * Finding all the points within a rectangle
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     */
    public void query (int minX, int maxX, int minY, int maxY) {
        query(root, minX, maxX, minY, maxY);
    }
    public void query (Node n, int minX, int maxX, int minY, int maxY) {
        if (n == null)
            return;
        if (minX <= n.x && n.x <= maxX && minY <= n.y && n.y <= maxY)
            System.out.println(n.x + " " + n.y + " " + n.value);
        if (minX < n.x && minY < n.y)
            query(n.UL, minX, maxX, minY, maxY);
        if (maxX >= n.x && minY < n.y)
            query(n.UR, minX, maxX, minY, maxY);
        if (minX < n.x && maxY >= n.y)
            query(n.DL, minX, maxX, minY, maxY);
        if (maxX >= n.x && maxY >= n.y)
            query(n.DR, minX, maxX, minY, maxY);
    }
}