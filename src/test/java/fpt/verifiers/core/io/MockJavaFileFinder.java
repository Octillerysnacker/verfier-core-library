package fpt.verifiers.core.io;

import java.nio.file.Path;

public class MockJavaFileFinder implements IJavaFileFinder {

    private Path suppliedSrc;
    private Path[] getAllFiles_return;
    public MockJavaFileFinder(Path[] getAllFiles_return){
        this.getAllFiles_return = getAllFiles_return;
    }
    public Path getSuppliedSrc(){
        return suppliedSrc;
    }
    @Override
    public Path[] getAllFiles(Path srcDirectory) {
        suppliedSrc = srcDirectory;
        return getAllFiles_return;
    }

}