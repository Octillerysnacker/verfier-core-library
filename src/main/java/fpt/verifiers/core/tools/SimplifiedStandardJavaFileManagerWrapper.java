package fpt.verifiers.core.tools;

import java.io.IOException;
import java.nio.file.Path;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

public class SimplifiedStandardJavaFileManagerWrapper implements ISimplifiedStandardJavaFileManager{

    private StandardJavaFileManager manager;
    public SimplifiedStandardJavaFileManagerWrapper(StandardJavaFileManager manager){
        this.manager = manager;
    }
	@Override
	public void close() throws IOException {
        manager.close();
	}

	@Override
	public Iterable<? extends JavaFileObject> getJavaFileObjects(Path... paths) {
        return manager.getJavaFileObjects(paths);
	}

}