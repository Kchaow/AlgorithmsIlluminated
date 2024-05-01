package org.letunov.data;

public class Edge
{
    private int weight = 1;
    private Vertex firstVertex;
    private Vertex secondVertex;
    public void setFirstVertex(Vertex firstVertex) {this.firstVertex = firstVertex;}
    public Vertex getFirstVertex() {return firstVertex;}
    public void setSecondVertex(Vertex secondVertex) {this.secondVertex = secondVertex;}
    public Vertex getSecondVertex() {return secondVertex;}
    public int getWeight() {return weight;}
    public void setWeight(int weight) {this.weight = weight;}
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof Edge otherEdge) {
            return weight == otherEdge.weight && firstVertex.equals(otherEdge.firstVertex) && secondVertex.equals(otherEdge.secondVertex);
        }
        else
            return false;
    }
    @Override
    public int hashCode() { return 31*weight + (firstVertex != null ? firstVertex.hashCode() : 10) + (secondVertex != null ? secondVertex.hashCode() : 12); }
}
