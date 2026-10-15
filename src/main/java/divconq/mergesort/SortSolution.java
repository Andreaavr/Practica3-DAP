package divconq.mergesort;

import divconq.framework.Solution;
import java.util.Collections;
import java.util.List;

/** Solución: lista ordenada (vista de solo lectura, sin copia extra). */
public record SortSolution<T>(List<T> sorted) implements Solution {
    public SortSolution {
        sorted = Collections.unmodifiableList(sorted);
    }
}
