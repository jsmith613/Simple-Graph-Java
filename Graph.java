package graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class representing a simple directed graph and specializes in connecting all vertices to a specified root vertex
 * Created by Josiah Smith on 7/21/2016.
 */
public class Graph {
    //Map from the vertex to its corresponding LinkedList
    private HashMap<Vertex,LinkedList<Vertex>> adjLists;

    /**
     * Constructor
     */
    public Graph() {
      adjLists = new HashMap<>();
    }

    /**
     * Constructor with vertices
     * @param vertices vertices in graph
     */
    public Graph(Vertex ... vertices) {
        this();
        add(vertices);
    }

    /**
     * adds the vertex to the graph
     * @param vertex vertex to be added
     */
    public void add(Vertex vertex) {
        LinkedList<Vertex> newVertexList = new LinkedList<>();
        newVertexList.addFirst(vertex);
        adjLists.put(vertex, newVertexList);
    }

    /**
     * adds the vertices to the graph
     * @param vertices vertices to be added
     */
    public void add(Vertex ... vertices) {
        for (Vertex vertex : vertices) {
            add(vertex);
        }
    }

    /**
     * Checks if the graph contains a vertex
     * @param vertex the vertex to be checked
     * @return whether the graph contains the specified vertex
     */
    public boolean contains(Vertex vertex) {
        for (Vertex existing : adjLists.keySet()) {
            if (vertex.equals(existing)) {return true;}
        }
        return false;
    }

    /**
     * removes a vertex and all its connections from the graph
     * @param vertex the vertex to be removed
     */
    public void remove(Vertex vertex) {
        adjLists.remove(vertex);
        for (LinkedList<Vertex> list : adjLists.values()) {
           for(Vertex element : list) {
               if (element.equals(vertex)) {
                   list.remove(element);
               }
           }
        }
    }

    /**
     * Removes a connection from a to b
     * @param a vertex a
     * @param b vertex b
     */
    public void removeConnection(Vertex a, Vertex b) {
        adjLists.get(a).remove(b);
    }

    /**
     * adds edge between vertices in both directions
     * @param a vertex a
     * @param b vertex b
     */
    public void addUndirectedEdge(Vertex a, Vertex b) {
        adjLists.get(a).add(b);
        adjLists.get(b).add(a);
    }

    /**
     * Adds directed edge from vertex a to vertex b
     * @param a vertex a
     * @param b vertex b
     */
    public void addDirectedEdge (Vertex a, Vertex b) {
        adjLists.get(a).add(b);
    }

    /**
     * finds all the vertices which are not immediately connected to the root vertex
     * @param rootVertex the vertex which other vertices should be connected to
     * @return  a hashmap with the keys representing the vertices needing to be connected and the values representing the path to the root (from vertex to root)
     */
    public HashMap<Vertex, ArrayList<Vertex>> connectEdgesToRootBFS(Vertex rootVertex){
        for (Vertex vertex : adjLists.keySet()) {
            vertex.setDistance(Integer.MAX_VALUE);
            vertex.setParentVertex(null);
        }
        ArrayList<Vertex> toBeConnected = new ArrayList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        rootVertex.setDistance(0);
        queue.addFirst(rootVertex);
        while (!queue.isEmpty()) {
            Vertex current = queue.getFirst();
            for (Vertex vertex : adjLists.get(current)){
                if (vertex.getDistance() == Integer.MAX_VALUE) {
                    vertex.setDistance(current.getDistance() + 1);
                    vertex.setParentVertex(current);
                    queue.add(vertex);
                    if (vertex.getDistance() > 1) {
                        toBeConnected.add(vertex);
                    }
                }
            }
            queue.removeFirst();
        }
        HashMap<Vertex, ArrayList<Vertex>> returnHashMap = new HashMap<>();
        for (Vertex vertex : toBeConnected) {
            ArrayList<Vertex> pathToRoot = new ArrayList<>();
            Vertex current = vertex;
            while (current.getParentVertex() != rootVertex) {
                pathToRoot.add(current.getParentVertex());
                current = current.getParentVertex();
            }
            pathToRoot.add(rootVertex);
            returnHashMap.put(vertex, pathToRoot);
        }
        return returnHashMap;
    }
}


