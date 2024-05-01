package org.letunov.data;

import java.util.*;

public class Vertex
{
    private String name;
    private List<Edge> incidentEdges = new ArrayList<>();
    public Vertex(String name) {this.name = name;}
    public void setIncidentEdges(List<Edge> incidentEdges) {this.incidentEdges = incidentEdges;}
    public List<Edge> getIncidentEdges()
    {
        return incidentEdges;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name) {this.name = name;}
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof Vertex other) {
            return Objects.equals(name, other.name);
        }
        else
            return false;
    }
    @Override
    public int hashCode() { return (name != null ? name.hashCode() : 10); }
}
