package de.rabea.request;

public class ReadInstructions {

    private int start;
    private int end;
    private boolean reverse;
    private boolean partial;

    public ReadInstructions(int start, int end, boolean reverse, boolean partial) {
        this.start = start;
        this.end = end;
        this.reverse = reverse;
        this.partial = partial;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    public boolean partial() {
        return partial;
    }

    public boolean reverse() {
        return reverse;
    }
}
