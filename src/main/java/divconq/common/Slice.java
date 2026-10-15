package divconq.common;

import java.util.List;
import java.util.Objects;

/**
 * Vista inmutable [from, to) sobre una lista. Dividirla NO copia datos:
 * todas las porciones comparten la misma lista fuente (copiada una sola vez).
 */
public final class Slice<T> {
    private final List<T> source;
    private final int from;
    private final int to;

    private Slice(List<T> source, int from, int to) {
        this.source = source;
        this.from = from;
        this.to = to;
    }

    /** Única copia defensiva: se hace al crear el problema original. */
    public static <T> Slice<T> of(List<? extends T> items) {
        Objects.requireNonNull(items, "items");
        List<T> copy = List.copyOf(items);
        return new Slice<>(copy, 0, copy.size());
    }

    public int size() {
        return to - from;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        Objects.checkIndex(index, size());
        return source.get(from + index);
    }

    /** Divide en dos mitades en O(1), sin copiar. */
    public List<Slice<T>> splitInHalf() {
        int mid = from + size() / 2;
        return List.of(new Slice<>(source, from, mid), new Slice<>(source, mid, to));
    }
}
