package search;

import game.State;

class Node {
    State state;
    int h;

    Node(State state, int h) {
        this.state = state;
        this.h = h;
    }
}
