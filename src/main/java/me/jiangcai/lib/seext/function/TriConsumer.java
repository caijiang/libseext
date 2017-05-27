package me.jiangcai.lib.seext.function;

import java.util.Objects;

/**
 * @author CJ
 * @since 1.3.4
 */
@FunctionalInterface
public interface TriConsumer<T, U, X> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @param x the third input argument
     */
    void accept(T t, U u, X x);

    /**
     * Returns a composed {@code BiConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code BiConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, X> andThen(java.util.function.BiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);

        return (l, r, x) -> {
            accept(l, r, x);
            after.accept(l, r);
        };
    }
}

