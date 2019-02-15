package ca.mcgill.ecse409.a2;

import java.util.ArrayList;

public class q2 {
    public static void main(String[] args){
        Node n1 = new Node(1, 1.0, 1.0);
        Node n2 = new Node(2, 0.0, 1.0);
        Node n3 = new Node(3, 0.5, 0.5);
        Node n4 = new Node(4, 0.0, 0.0);
        Node n5 = new Node(4, 0.5, 0.0);

        Node[] nodes = {n1, n2, n3, n4, n5};
        Edge[] edges = basicTriangulation(nodes);

        System.out.println(edges.length);
    }

    private static class Node {
        private int id;
        private double x;
        private double y;

        public Node(int id, double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge {
        private Node n1;
        private Node n2;

        public Edge(Node n1, Node n2){
            this.n1 = n1;
            this.n2 = n2;
            // Add some code to set lower id as n1
        }
    }

    /**
     * This method will check if edge1 and edge2 are crossing one another
     * True if the cross, False o.t.w.
     * */
    public static boolean isCrossing(Edge edge1, Edge edge2){
        return true;
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
                    System.out.println("Adding... " + hullNode.id);
                    edges.add(newEdge);
                }
            }
            hull.add(node);
        }

        // Get the edged into an Edge array
        Edge[] sol = new Edge[edges.size()];
        for(int i = 0; i < sol.length; i++){
            sol[i] = edges.get(i);
        }
        return sol;
    }
}
