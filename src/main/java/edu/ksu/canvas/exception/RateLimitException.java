package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a 429 (formerly 403) Rate Limit Exceeded response with the remaining quota exhausted.
 */
public class RateLimitException extends CanvasException {

    public RateLimitException() {
        super();
    }

    public RateLimitException(String canvasErrorString, String url) {
        super(canvasErrorString, url);
    }
}
