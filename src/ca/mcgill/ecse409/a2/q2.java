package ca.mcgill.ecse409.a2;

public class q2 {
    public static void main(String[] args){
        Node n1 = new Node(1, 1.0, 1.0);
        Node n2 = new Node(2, 0.0, 1.0);
        Node n3 = new Node(3, 0.5, 0.5);
        Node n4 = new Node(4, 0.0, 0.0);

        Edge e1 = new Edge(n1, n2);
        Edge e2 = new Edge(n1, n3);
        Edge e3 = new Edge(n2, n3);
        Edge e4 = new Edge(n2, n4);
        Edge e5 = new Edge(n1, n2);

        System.out.println("This worked!");
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
}
