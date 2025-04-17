package search;

import game.Grid;
import game.State;
import heuristique.Heuristique;

import java.util.*;

public class BestFirstSearch extends SearchAlgorithm {

    private final Heuristique heuristique;
    private final Grid goal;
    private PriorityQueue<Node> priorityQueue;
    private Map<State, Integer> heuristicCache;

    public BestFirstSearch(Heuristique heuristique, Grid goal) {
        this.heuristique = heuristique;
        this.goal = goal;
    }

    private static class Node {
        final State state;
        final int h;

        Node(State state, int h) {
            this.state = state;
            this.h = h;
        }
    }

    @Override
    protected Collection<State> getOpenList() {
        heuristicCache = new HashMap<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(n -> n.h));

        return new AbstractQueue<>() {
            @Override
            public boolean offer(State state) {
                int h = heuristicCache.computeIfAbsent(state, s -> heuristique.evaluate((Grid) s, goal));
                return priorityQueue.offer(new Node(state, h));
            }

            @Override
            public State poll() {
                Node node = priorityQueue.poll();
                return node != null ? node.state : null;
            }

            @Override
            public State peek() {
                Node node = priorityQueue.peek();
                return node != null ? node.state : null;
            }

            @Override
            public Iterator<State> iterator() {
                return priorityQueue.stream().map(n -> n.state).iterator();
            }

            @Override
            public int size() {
                return priorityQueue.size();
            }
        };
    }

    @Override
    protected State removeFromOpenList(Collection<State> openList) {
        return ((Queue<State>) openList).poll();
    }
}
