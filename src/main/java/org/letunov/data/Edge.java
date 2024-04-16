package org.letunov.data;

public class Edge
{
    private Vertex firstVertex;
    private Vertex secondVertex;
    public void setFirstVertex(Vertex firstVertex)
    {
        this.firstVertex = firstVertex;
    }
    public Vertex getFirstVertex()
    {
        return firstVertex;
    }
    public void setSecondVertex(Vertex secondVertex)
    {
        this.secondVertex = secondVertex;
    }
    public Vertex getSecondVertex()
    {
        return secondVertex;
    }
}
