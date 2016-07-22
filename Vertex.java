package graph;

/**
 * Created by Josiah Smith on 7/21/2016.
 */
public class Vertex implements Comparable<Vertex>{
    private Object object;
    private int distance;
    private Vertex parentVertex;

    @Override
    public boolean equals(Object other) {
        if (other == null) {return false;}
        if (this == other) {return true;}
        if (!(other instanceof Vertex)) {return false;}
        Vertex that = (Vertex) other;
        return (this.object.equals(that.object));
    }

    @Override
    public int compareTo(Vertex other) {
        this.object.compareTo(other.object);
    }
    @Override
    public int hashCode() {
        return 1;
    }

    public Vertex(Object object) {this.object = object;}
    public int getObject() {return  object;}
    public int getDistance() {return distance;}
    public Vertex getParentVertex() {return parentVertex;}
    public void setDistance(int d) {distance = d;}
    public void setParentVertex(Vertex pv) {parentVertex = pv;}
}