package me.jiangcai.lib.seext.function;

/**
 * @author CJ
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
