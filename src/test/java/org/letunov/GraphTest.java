package org.letunov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.letunov.data.Edge;
import org.letunov.data.Graph;
import org.letunov.data.Vertex;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    private Graph graph;
    @BeforeEach
    public void setupGraph() {
        graph = new Graph();
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Edge ab = new Edge();
        ab.setFirstVertex(a);
        ab.setSecondVertex(b);
        Edge ad = new Edge();
        ad.setFirstVertex(a);
        ad.setSecondVertex(d);
        Edge af = new Edge();
        af.setFirstVertex(a);
        af.setSecondVertex(f);
        Edge bc = new Edge();
        bc.setFirstVertex(b);
        bc.setSecondVertex(c);
        Edge bf = new Edge();
        bf.setFirstVertex(b);
        bf.setSecondVertex(f);
        Edge fe = new Edge();
        fe.setFirstVertex(f);
        fe.setSecondVertex(e);
        Edge ed = new Edge();
        ed.setFirstVertex(e);
        ed.setSecondVertex(d);
        Edge dc = new Edge();
        dc.setFirstVertex(d);
        dc.setSecondVertex(c);
        List<Edge> aE = new ArrayList<>();
        aE.add(ab);
        aE.add(af);
        a.setIncidentEdges(aE);
        List<Edge> bE = new ArrayList<>();
        bE.add(bc);
        bE.add(bf);
        b.setIncidentEdges(bE);
        List<Edge> fE = new ArrayList<>();
        fE.add(bf);
        fE.add(fe);
        fE.add(af);
        f.setIncidentEdges(fE);
        e.getIncidentEdges().add(fe);
        e.getIncidentEdges().add(ed);
        d.getIncidentEdges().add(ed);
        d.getIncidentEdges().add(dc);
        d.getIncidentEdges().add(ad);
        c.getIncidentEdges().add(bc);
        c.getIncidentEdges().add(dc);

        Vertex z = new Vertex("z");
        Vertex y = new Vertex("y");
        Vertex v = new Vertex("v");
        Vertex x = new Vertex("x");
        Edge zy = new Edge();
        zy.setFirstVertex(z);
        zy.setSecondVertex(y);
        Edge yx = new Edge();
        yx.setFirstVertex(y);
        yx.setSecondVertex(x);
        Edge xz = new Edge();
        xz.setFirstVertex(x);
        xz.setSecondVertex(z);
        Edge vy = new Edge();
        vy.setFirstVertex(v);
        vy.setSecondVertex(y);
        z.getIncidentEdges().add(zy);
        z.getIncidentEdges().add(xz);
        y.getIncidentEdges().add(zy);
        y.getIncidentEdges().add(yx);
        y.getIncidentEdges().add(vy);
        x.getIncidentEdges().add(yx);
        x.getIncidentEdges().add(xz);
        v.getIncidentEdges().add(vy);

        Vertex k = new Vertex("k");
        Vertex l = new Vertex("l");
        Edge kl = new Edge();
        kl.setFirstVertex(k);
        kl.setSecondVertex(l);
        k.getIncidentEdges().add(kl);
        l.getIncidentEdges().add(kl);

        Vertex m = new Vertex("m");

        graph.getVertexList().add(a);
        graph.getVertexList().add(b);
        graph.getVertexList().add(c);
        graph.getVertexList().add(d);
        graph.getVertexList().add(e);
        graph.getVertexList().add(f);
        graph.getVertexList().add(z);
        graph.getVertexList().add(y);
        graph.getVertexList().add(x);
        graph.getVertexList().add(v);
        graph.getVertexList().add(k);
        graph.getVertexList().add(l);
        graph.getVertexList().add(m);

        graph.getEdgeList().add(ab);
        graph.getEdgeList().add(ad);
        graph.getEdgeList().add(af);
        graph.getEdgeList().add(bc);
        graph.getEdgeList().add(bf);
        graph.getEdgeList().add(fe);
        graph.getEdgeList().add(ed);
        graph.getEdgeList().add(dc);
        graph.getEdgeList().add(zy);
        graph.getEdgeList().add(yx);
        graph.getEdgeList().add(xz);
        graph.getEdgeList().add(vy);
        graph.getEdgeList().add(kl);
    }

    @Test
    public void isReachableByBFSTest() {
        Vertex x = graph.getVertexList().stream().filter(vertex -> vertex.getName().equals("x")).findFirst().get();
        Vertex c = graph.getVertexList().stream().filter(vertex -> vertex.getName().equals("c")).findFirst().get();
        Vertex f = graph.getVertexList().stream().filter(vertex -> vertex.getName().equals("f")).findFirst().get();

        assertAll(
                () -> assertTrue(graph.isReachableByBFS(c, f)),
                () -> assertFalse(graph.isReachableByBFS(c, x))
        );
    }
}
