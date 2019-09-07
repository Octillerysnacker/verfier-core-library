package fpt.verifiers.core;

public class FileLocation {
    public final int Line;
    public final int Position;

    public FileLocation(int line, int position) {
        Line = line;
        Position = position;
    }

    public FileLocation() {
        this(-1, -1);
    }
}