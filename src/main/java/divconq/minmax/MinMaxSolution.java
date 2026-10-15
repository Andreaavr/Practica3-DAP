package divconq.minmax;

import divconq.framework.Solution;

/** Solución: par (mínimo, máximo). */
public record MinMaxSolution<T>(T min, T max) implements Solution {
}
