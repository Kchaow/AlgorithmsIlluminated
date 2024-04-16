package org.letunov.data;

import java.util.ArrayList;
import java.util.List;

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
}
