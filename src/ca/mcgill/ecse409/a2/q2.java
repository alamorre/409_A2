package ca.mcgill.ecse409.a2;

import java.util.ArrayList;

public class q2 {
    public static void main(String[] args){
        Node n1 = new Node(1, 1.0, 1.0); // (1.0, 1.0)
        Node n2 = new Node(2, 0.0, 1.0); // (0.0, 1.0)
        Node n3 = new Node(3, 0.9, 0.5); // (0.9, 0.5)
        Node n4 = new Node(4, 0.0, 0.0); // (0.0, 0.0)
        Node n5 = new Node(5, 0.5, 0.0); // (0.5, 0.0)

        Node[] nodes = {n1, n2, n3, n4, n5};
        Edge[] edges = basicTriangulation(nodes);
        Edge[] hull = getHull(edges);

        for(Edge e : edges){
            System.out.println( e.n1.id+ ", " + e.n2.id);
        }
    }

    private static class Node {
        private int id;
        private double x;
        private double y;

        public Node(int id, double x, double y){
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge {
        private Node n1;
        private Node n2;

        public Edge(Node n1, Node n2){
            // Add some code to set lower id as n1
            if(n1.id <= n2.id){
                this.n1 = n1;
                this.n2 = n2;
            }else{
                this.n1 = n2;
                this.n2 = n1;
            }
        }
    }

    public static double getSlope(Edge edge){
        return (edge.n1.y - edge.n2.y)/(edge.n1.x - edge.n2.x);
    }

    public static double getLength(Edge edge){
        double dx = edge.n1.x = edge.n2.x;
        double dy = edge.n1.y = edge.n2.y;
        return Math.sqrt((dx*dx) + (dy*dy));
    }

    /**
     * This method tell is a node is above or below the edges line
     * True is above, False is equal or below.
     * */
    public static boolean isAbove(Edge edge, Node node){
        // Create a line function
        double slope = getSlope(edge);
        double yInt = edge.n1.y - (slope * edge.n1.x);
        // Is node's x, y is above that function True, else False
        if(node.y >= (yInt + slope * node.x)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * This method will check if edge1 and edge2 are crossing one another
     * If one node is above edge1 and one node is below edge1 then they're crossing
     * True if the cross, False o.t.w.
     * */
    public static boolean isCrossing(Edge edge1, Edge edge2){
        if((isAbove(edge1, edge2.n1) && !isAbove(edge1, edge2.n2))){
            System.out.println(edge2.n1.id + " id over and " + edge2.n2.id + " is under: " + edge1.n1.id + ", " + edge1.n2.id);
            return true;
        } else if(!isAbove(edge1, edge2.n1) && isAbove(edge1, edge2.n2)){
            System.out.println(edge2.n2.id + " id over and " + edge2.n1.id + " is under: " + edge1.n1.id + ", " + edge1.n2.id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method tries to make a new edge and validate it
     * If valid, keep the edge else delete it
     * Return boolean per if the new edge is legal
     * */
    public static boolean isLegalEdge(Node node, Node newNode, ArrayList<Edge> hullEdges){
        Edge temp = new Edge(newNode, node);
        for(Edge edge : hullEdges){
            if(isCrossing(temp, edge)){
                return false;
            }
        }
        return true;
    }

    public static Edge[] basicTriangulation(Node[] nodes){
        // Create a hull and edges data structure
        ArrayList<Edge> edges = new ArrayList<Edge>();
        ArrayList<Node> hull = new ArrayList<Node>();

        // For the nodes
        for(Node node : nodes){
            for(Node hullNode : hull){
                if(isLegalEdge(hullNode, node, edges)){
                    Edge newEdge = new Edge(hullNode, node);
                    edges.add(newEdge);
                }
            }
            hull.add(node);
        }

        // Get the edged into an Edge array
        Edge[] sol = new Edge[edges.size()];
        for(int i = 0; i < sol.length; i++){
            boolean groomed = true;
            for(int j = 0; j < sol.length; j++){
                Edge temp = sol[j];
                Edge thisEdge = edges.get(i);
                if(temp != null){
                    if(thisEdge.n1.id == temp.n1.id && thisEdge.n2.id == temp.n2.id) {
                        groomed = false;
                    } else if( thisEdge.n1.id == thisEdge.n2.id){
                        groomed = false;
                    }
                }
            }
            if(groomed){
                sol[i] = edges.get(i);
            }
        }
        return sol;
    }

    /**
     * Thid method takes a bunch of edges and returns one that are just on the hull
     * */
    public static Edge[] getHull(Edge[] edged){
        return null;
    }
}
