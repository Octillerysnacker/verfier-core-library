package fpt.verifiers.core.tools;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

import fpt.verifiers.core.io.IJavaFileFinder;

public class FPTCompiler implements IFPTCompiler{

    private ISimplifiedCompiler simplifiedCompiler;
    private DiagnosticListener<? super JavaFileObject> listener;
    private IJavaFileFinder finder;

    public FPTCompiler(ISimplifiedCompiler simplifiedCompiler, DiagnosticListener<? super JavaFileObject> listener,
            IJavaFileFinder finder) {
        this.simplifiedCompiler = simplifiedCompiler;
        this.listener = listener;
        this.finder = finder;
    }

    public boolean compileToDir(Path srcDir, Path targetDir) throws IOException{
        try (var manager = simplifiedCompiler.getSimplifiedStandardJavaFileManager(listener)) {
            var objects = manager.getJavaFileObjects(finder.getAllFiles(srcDir));
            var task = simplifiedCompiler.getSimplfiedCompilationTask(manager, listener,
                    Arrays.asList("-d", targetDir.toString()), objects);
            return task.call();
        }
    }

}