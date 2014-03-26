package de.develman.mmi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Vertex repräsentiert einen Knoten im Graphen
 *
 * @author Georg Henkel <georg@develman.de>
 */
public class Vertex
{
    private final Integer key;
    private boolean visited = false;
    private final List<Edge> incomingEdges = new ArrayList<>();
    private final List<Edge> outgoingEdges = new ArrayList<>();

    public Vertex(Integer key)
    {
        this.key = key;
    }

    public Integer getKey()
    {
        return key;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public List<Edge> getIncomingEdges()
    {
        return new ArrayList<>(incomingEdges);
    }

    public void addIncomingEdge(Edge edge)
    {
        incomingEdges.add(edge);
    }

    public void removeIncomingEdge(Edge edge)
    {
        incomingEdges.remove(edge);
    }

    public List<Edge> getOutgoingEdges()
    {
        return new ArrayList<>(outgoingEdges);
    }

    public void addOutgoingEdge(Edge edge)
    {
        outgoingEdges.add(edge);
    }

    public void removeOutgoingEdge(Edge edge)
    {
        outgoingEdges.remove(edge);
    }

    public List<Vertex> getSuccessors()
    {
        List<Vertex> successors = new ArrayList<>();
        outgoingEdges.forEach(edge -> successors.add(edge.getSink()));

        return successors;
    }

    public List<Vertex> getPredecessors()
    {
        List<Vertex> predecessors = new ArrayList<>();
        incomingEdges.forEach(edge -> predecessors.add(edge.getSource()));

        return predecessors;
    }

    @Override
    public String toString()
    {
        return key.toString();
    }
}
