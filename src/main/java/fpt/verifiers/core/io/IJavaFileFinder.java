package fpt.verifiers.core.io;

import java.io.IOException;
import java.nio.file.Path;

public interface IJavaFileFinder{
    public Path[] getAllFiles(Path srcDirectory) throws IOException;
}