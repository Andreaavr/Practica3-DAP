package divconq.mergesort;

import divconq.framework.DivConqTemplate;
import divconq.framework.Problem;
import divconq.framework.Solution;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MergeSort<T> extends DivConqTemplate {

    private final Comparator<? super T> comparator;

    public MergeSort(Comparator<? super T> comparator) {
        this.comparator = Objects.requireNonNull(comparator, "comparator");
    }

    @SuppressWarnings("unchecked")
    private SortProblem<T> problem(Problem p) {
        return (SortProblem<T>) p;
    }

    @SuppressWarnings("unchecked")
    private SortSolution<T> solution(Solution s) {
        return (SortSolution<T>) s;
    }

    @Override
    protected boolean isSimple(Problem p) {
        return problem(p).slice().size() <= 1;
    }

    @Override
    protected Solution simplySolve(Problem p) {
        List<T> single = new ArrayList<>(1);
        if (!problem(p).slice().isEmpty()) single.add(problem(p).slice().get(0));
        return new SortSolution<>(single);
    }

    @Override
    protected Problem[] decompose(Problem p) {
        return problem(p).slice().splitInHalf().stream()
                .map(SortProblem::new).toArray(Problem[]::new);
    }

    @Override
    protected Solution combine(Problem p, Solution[] ss) {
        List<T> left = solution(ss[0]).sorted();
        List<T> right = solution(ss[1]).sorted();
        List<T> merged = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            merged.add(comparator.compare(left.get(i), right.get(j)) <= 0
                    ? left.get(i++) : right.get(j++));
        }
        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));
        return new SortSolution<>(merged);
    }
}
