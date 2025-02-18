package fpt.verifiers.core.tools;

import java.io.IOException;
import java.nio.file.Path;

import javax.tools.JavaFileObject;

public class MockSimplifiedStandardJavaFileManager implements ISimplifiedStandardJavaFileManager {

    private Path[] paths;
    private Iterable<? extends JavaFileObject> getJavaFileObjects_return;
    private boolean _hasBeenClosed;

    public boolean hasBeenClosed(){
        return _hasBeenClosed;
    }

    public Path[] getSuppliedPaths() {
        return paths;
    }

    public void setReturnForGetJavaFileObjects(Iterable<? extends JavaFileObject> getJavaFileObjects_return) {
        this.getJavaFileObjects_return = getJavaFileObjects_return;
    }

    public MockSimplifiedStandardJavaFileManager(Iterable<? extends JavaFileObject> getJavaFileObjects_return) {
        setReturnForGetJavaFileObjects(getJavaFileObjects_return);
    }

    @Override
    public Iterable<? extends JavaFileObject> getJavaFileObjects(Path... paths) {
        this.paths = paths;
        return getJavaFileObjects_return;
    }

    @Override
    public void close() throws IOException {
        _hasBeenClosed = true;
    }

}