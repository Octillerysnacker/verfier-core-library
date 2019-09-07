package fpt.verifiers.core.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultJavaFileFinder implements IJavaFileFinder {

    @Override
    public Path[] getAllFiles(Path srcDirectory) throws IOException {
        try(var stream = Files.walk(srcDirectory)){
            return stream
            .filter(Files::isRegularFile)
            .filter(file -> file.endsWith(".java"))
            .toArray(Path[]::new);
        }
    }

}