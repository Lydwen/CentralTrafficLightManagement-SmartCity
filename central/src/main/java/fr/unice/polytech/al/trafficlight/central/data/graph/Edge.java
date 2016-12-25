package fr.unice.polytech.al.trafficlight.central.data.graph;

/**
 * Representation of an edge in a graph.
 *
 * An edge linked two node of type T in a graph, with
 * a weight to go from the begin node to the end node
 *
 * Created by tom dall'agnol on 25/12/16.
 */
public class Edge<T> {
    private T begin;        //the node where the edge begins
    private T end;          //the node where the edge ends

    private int weight;     //the cost to go from begin to end

    public Edge(T begin, T end, int weight){
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

//============================ GETTER & SETTERS ============================
    public T getBegin() {
        return begin;
    }

    public void setBegin(T begin) {
        this.begin = begin;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

//============================ EQUALS & HASHCODE ============================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?> edge = (Edge<?>) o;

        if (weight != edge.weight) return false;
        if (!begin.equals(edge.begin)) return false;
        return end.equals(edge.end);

    }

    @Override
    public int hashCode() {
        int result = begin.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + weight;
        return result;
    }
}
