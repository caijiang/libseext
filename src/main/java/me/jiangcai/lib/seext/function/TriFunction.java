package me.jiangcai.lib.seext.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author CJ
 * @since 1.3.4
 */
@FunctionalInterface
public interface TriFunction<T, U, X, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @param x the third function argument
     * @return the function result
     */
    R apply(T t, U u, X x);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> TriFunction<T, U, X, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u, X x) -> after.apply(apply(t, u, x));
    }

}
