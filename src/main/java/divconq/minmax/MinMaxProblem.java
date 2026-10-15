package divconq.minmax;

import divconq.common.Slice;
import divconq.framework.Problem;
import java.util.List;

/** Problema: mínimo y máximo de una porción no vacía. */
public record MinMaxProblem<T>(Slice<T> slice) implements Problem {
    public MinMaxProblem {
        if (slice.isEmpty()) {
            throw new IllegalArgumentException("La secuencia no puede estar vacía");
        }
    }

    public static <T> MinMaxProblem<T> of(List<? extends T> items) {
        return new MinMaxProblem<>(Slice.of(items));
    }
}
