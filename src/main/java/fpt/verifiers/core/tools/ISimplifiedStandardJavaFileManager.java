package fpt.verifiers.core.tools;

import java.nio.file.Path;

import javax.tools.JavaFileObject;

public interface ISimplifiedStandardJavaFileManager{
    Iterable<? extends JavaFileObject> getJavaFileObjects(Path... paths);
}