package org.letunov.data;

import java.util.*;

public class Graph
{
    private List<Edge> edgeList = new ArrayList<>();
    private List<Vertex> vertexList = new ArrayList<>();
    public List<Edge> getEdgeList()
    {
        return edgeList;
    }
    public void setEdgeList(List<Edge> edgeList)
    {
        this.edgeList = edgeList;
    }
    public List<Vertex> getVertexList()
    {
        return vertexList;
    }
    public void setVertexList(List<Vertex> vertexList)
    {
        this.vertexList = vertexList;
    }
    public boolean isReachableByBFS(Vertex start, Vertex target) {
        Map<Vertex, Boolean> isReached = new HashMap<>();
        vertexList.forEach(x -> isReached.put(x, false));
        isReached.put(start, true);
        Queue<Vertex> vertexQueue = new LinkedList<>();
        vertexQueue.add(start);
        while (!vertexQueue.isEmpty()) {
            Vertex vertex = vertexQueue.poll();
            for (Edge edge : vertex.getIncidentEdges()) {
                Vertex adjacentVertex = edge.getFirstVertex().equals(vertex) ? edge.getSecondVertex() : edge.getFirstVertex();
                if (adjacentVertex.equals(target))
                    return true;
                if (!isReached.get(adjacentVertex)) {
                    isReached.put(adjacentVertex, true);
                    vertexQueue.add(adjacentVertex);
                }
            }
        }
        return false;
    }
    public int getShortestWayWithOneUnitEdgesByBFS(Vertex start, Vertex target) {
        if (start.equals(target))
            return 0;
        int l = 0;
        Map<Vertex, Boolean> isReached = new HashMap<>();
        vertexList.forEach(x -> isReached.put(x, false));
        isReached.put(start, true);
        Queue<Vertex> vertexQueue = new LinkedList<>();
        vertexQueue.add(start);
        while (!vertexQueue.isEmpty()) {
            l++;
            Vertex vertex = vertexQueue.poll();
            for (Edge edge : vertex.getIncidentEdges()) {
                Vertex adjacentVertex = edge.getFirstVertex().equals(vertex) ? edge.getSecondVertex() : edge.getFirstVertex();
                if (adjacentVertex.equals(target))
                    return l;
                if (!isReached.get(adjacentVertex)) {
                    isReached.put(adjacentVertex, true);
                    vertexQueue.add(adjacentVertex);
                }
            }
        }
        return -1;
    }
    public List<List<Vertex>> ucc() {
        List<List<Vertex>> cc = new ArrayList<>();
        Map<Vertex, Boolean> isReached = new HashMap<>();
        vertexList.forEach(x -> isReached.put(x, false));
        for (Vertex vertex : vertexList) {
            if (!isReached.get(vertex)) {
                cc.add(new ArrayList<>());
                Queue<Vertex> vertexQueue = new LinkedList<>();
                vertexQueue.add(vertex);
                while (!vertexQueue.isEmpty()) {
                    Vertex currentVertex = vertexQueue.poll();
                    cc.get(cc.size()-1).add(currentVertex);
                    for (Edge edge : vertex.getIncidentEdges()) {
                        Vertex adjacentVertex = edge.getFirstVertex().equals(vertex) ? edge.getSecondVertex() : edge.getFirstVertex();
                        if (!isReached.get(adjacentVertex)) {
                            isReached.put(adjacentVertex, true);
                            vertexQueue.add(adjacentVertex);
                        }
                    }
                }
            }
        }
        return cc;
    }
}
