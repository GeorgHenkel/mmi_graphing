package de.develman.mmi.algorithm;

import de.develman.mmi.model.Edge;
import de.develman.mmi.model.Graph;
import de.develman.mmi.model.Vertex;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Die Klasse NearestNeighbour implementiert den Nearest-Neighbour Algorithmus zur Berechnung der optimalen Tour in
 * einem vollständigen Graphen mit Kantengewichten (TSP)
 *
 * @author Georg Henkel <georg@develman.de>
 */
public class NearestNeighbour
{
    /**
     * Berechnung der optimalen Tour
     *
     * @param graph Vollständiger Graph mit Kantengewichten
     * @param startVertex Startknoten
     * @return Liste der Kanten der optimalen Tour
     */
    public static List<Edge> getHamilton(Graph graph, Vertex startVertex)
    {
        List<Edge> hamilton = new ArrayList<>();

        Vertex vertex = startVertex;
        do
        {
            vertex.setVisited(true);

            Edge bestEdge = getBestEdge(vertex);
            if (bestEdge == null)
            {
                Edge edge = graph.getEdge(vertex, startVertex);
                hamilton.add(edge);

                break;
            }

            vertex = bestEdge.getSink();
            hamilton.add(bestEdge);
        }
        while (!allVerticesVisited(graph));

        return hamilton;
    }

    private static boolean allVerticesVisited(Graph graph)
    {
        boolean allVisited = false;

        long unvisitedVertices = graph.getVertices().stream().filter(v -> !v.isVisited()).count();
        if (unvisitedVertices == 0)
        {
            allVisited = true;
        }

        return allVisited;
    }

    private static Edge getBestEdge(Vertex vertex)
    {
        Optional<Edge> foundEdge = vertex.getOutgoingEdges().stream().filter(e -> !e.getSink().isVisited()).sorted(
                Comparator.comparing(Edge::getWeight)).findFirst();

        Edge bestEdge = null;
        if (foundEdge.isPresent())
        {
            bestEdge = foundEdge.get();
        }

        return bestEdge;
    }
}