package edu.ksu.canvas.interfaces;


import java.util.List;
import java.util.function.Consumer;

public interface CanvasReader<T, READERTYPE extends CanvasReader> {
    /*
     * Perform an operation with a callback. This is used to
     * to perform operations on paginated calls before the final
     * response is complete.
     */
    READERTYPE withCallback(Consumer<List<T>> responseConsumer);

    READERTYPE withCallback(CancellableCallback<List<T>> responseCallback);

    READERTYPE readAsCanvasUser(String masqueradeAs);

    READERTYPE readAsSisUser(String masqueradeAs);

    @FunctionalInterface
    public interface CancellableCallback<U> {
    	/**
    	 * @return true to continue if more data is available, or false to stop fetching more pages.
    	 */
    	public boolean accept(U value);
    }
}
