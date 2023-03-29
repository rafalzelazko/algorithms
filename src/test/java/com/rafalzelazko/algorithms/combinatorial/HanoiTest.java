package com.rafalzelazko.algorithms.combinatorial;

import com.rafalzelazko.algorithms.combinatorial.Hanoi.Action;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HanoiTest {
    @Test
    public void hanoi__typicalCase() {
        List<Action> actions = List.of(
            new Action(1, 1, 3),
            new Action(2, 1, 2),
            new Action(1, 3, 2),
            new Action(3, 1, 3),
            new Action(1, 2, 1),
            new Action(2, 2, 3),
            new Action(1, 1, 3)
        );
        assertEquals(actions, Hanoi.hanoi(3, 1, 3, 2));
    }

    @Test
    public void hanoi__singleItem() {
        List<Action> actions = List.of(new Action(1, 1, 3));
        assertEquals(actions, Hanoi.hanoi(1, 1, 3, 2));
    }

    @Test
    public void hanoi__noItems() {
        List<Action> actions = List.of();
        assertEquals(actions, Hanoi.hanoi(0, 1, 3, 2));
    }
}
