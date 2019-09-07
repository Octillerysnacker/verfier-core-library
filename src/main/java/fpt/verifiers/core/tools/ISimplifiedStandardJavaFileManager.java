package fpt.verifiers.core.tools;

import java.io.Closeable;
import java.nio.file.Path;

import javax.tools.JavaFileObject;

public interface ISimplifiedStandardJavaFileManager extends Closeable{
    Iterable<? extends JavaFileObject> getJavaFileObjects(Path... paths);
}