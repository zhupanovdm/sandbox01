package org.zhupanovdm.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EvaluatedTransition<V, W> extends Transition<V> {
    protected W cost;

    public EvaluatedTransition(V destination, V origin, W cost) {
        super(destination, origin);
        this.cost = cost;
    }

    @Override
    public String toString() {
        if (origin == null)
            return String.format("%s", destination);
        return String.format("%s >> %s: %s", origin, destination, cost);
    }
}
