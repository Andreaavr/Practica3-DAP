package divconq.mergesort;

import divconq.common.Slice;
import divconq.framework.Problem;
import java.util.List;

/** Problema: ordenar una porción de lista. */
public record SortProblem<T>(Slice<T> slice) implements Problem {
    public static <T> SortProblem<T> of(List<? extends T> items) {
        return new SortProblem<>(Slice.of(items));
    }
}
