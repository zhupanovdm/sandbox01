package org.zhupanovdm.graph;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transition<V> {
    protected V destination;
    protected V origin;

    public Transition(V destination, V origin) {
        this.destination = destination;
        this.origin = origin;
    }

    @Override
    public String toString() {
        if (origin == null)
            return String.format("%s", destination);
        return String.format("%s >> %s", origin, destination);
    }
}
