package divconq;

import divconq.framework.DivConqTemplate;
import divconq.mergesort.*;
import divconq.minmax.*;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> data = List.of(7, 3, 9, 1, 8, 2, 5, 4, 6, 0);

        DivConqTemplate asc = new MergeSort<Integer>(Comparator.naturalOrder());
        SortSolution<?> s = (SortSolution<?>) asc.solve(SortProblem.of(data));
        System.out.println("Asc:  " + s.sorted());

        DivConqTemplate desc = new MergeSort<Integer>(Comparator.reverseOrder());
        s = (SortSolution<?>) desc.solve(SortProblem.of(data));
        System.out.println("Desc: " + s.sorted());

        DivConqTemplate mm = new MinMax<String>(Comparator.naturalOrder());
        MinMaxSolution<?> r = (MinMaxSolution<?>) mm.solve(
                MinMaxProblem.of(List.of("kiwi", "pera", "manzana", "fresa")));
        System.out.println("Min/Max: " + r.min() + " / " + r.max());
    }
}
