package fpt.verifiers.core.io;

import java.nio.file.Path;

public interface IJavaFileFinder{
    public Path[] getAllFiles(Path srcDirectory);
}