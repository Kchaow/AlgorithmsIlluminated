package org.letunov.data;

import java.util.ArrayList;
import java.util.List;

public class Vertex
{
    private final String name;
    private List<Edge> incidentEdges = new ArrayList<>();
    public Vertex(String name)
    {
        this.name = name;
    }
    public void setIncidentEdges(List<Edge> incidentEdges)
    {
        this.incidentEdges = incidentEdges;
    }
    public List<Edge> getIncidentEdges()
    {
        return incidentEdges;
    }
    public String getName()
    {
        return name;
    }
}
