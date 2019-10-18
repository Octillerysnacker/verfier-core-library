package fpt.verifiers.core;

public class FileLocation {
    public final long Line;
    public final long Position;

    public FileLocation(long line, long position) {
        Line = line;
        Position = position;
    }

    public FileLocation() {
        this(-1, -1);
    }
}