package me.jiangcai.lib.seext.function;

/**
 * @author CJ
 * @since 1.3.3
 */
@FunctionalInterface
public interface AllSupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Exception;
}
