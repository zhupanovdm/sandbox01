package org.zhupanovdm.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WeightedTransition<V, W> extends Transition<V> {
    protected W weight;

    public WeightedTransition(V destination, V origin, W weight) {
        super(destination, origin);
        this.weight = weight;
    }

    @Override
    public String toString() {
        if (origin == null)
            return String.format("%s", destination);
        return String.format("%s >> %s: %s", origin, destination, weight);
    }
}
