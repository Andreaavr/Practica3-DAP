package divconq.minmax;

import divconq.framework.DivConqTemplate;
import divconq.framework.Problem;
import divconq.framework.Solution;
import java.util.Comparator;
import java.util.Objects;

public class MinMax<T> extends DivConqTemplate {

    private final Comparator<? super T> comparator;

    public MinMax(Comparator<? super T> comparator) {
        this.comparator = Objects.requireNonNull(comparator, "comparator");
    }

    @SuppressWarnings("unchecked")
    private MinMaxProblem<T> problem(Problem p) {
        return (MinMaxProblem<T>) p;
    }

    @SuppressWarnings("unchecked")
    private MinMaxSolution<T> solution(Solution s) {
        return (MinMaxSolution<T>) s;
    }

    @Override
    protected boolean isSimple(Problem p) {
        return problem(p).slice().size() <= 2;
    }

    @Override
    protected Solution simplySolve(Problem p) {
        T a = problem(p).slice().get(0);
        T b = problem(p).slice().get(problem(p).slice().size() - 1);
        return comparator.compare(a, b) <= 0
                ? new MinMaxSolution<>(a, b)
                : new MinMaxSolution<>(b, a);
    }

    @Override
    protected Problem[] decompose(Problem p) {
        return problem(p).slice().splitInHalf().stream()
                .map(MinMaxProblem::new).toArray(Problem[]::new);
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        MinMaxSolution<T> l = solution(ss[0]), r = solution(ss[1]);
        return new MinMaxSolution<>(
                comparator.compare(l.min(), r.min()) <= 0 ? l.min() : r.min(),
                comparator.compare(l.max(), r.max()) >= 0 ? l.max() : r.max());
    }
}
