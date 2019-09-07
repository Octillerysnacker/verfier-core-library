package fpt.verifiers.core.tools;

import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;

public class SimplifiedCompilerWrapper implements ISimplifiedCompiler {

    private JavaCompiler compiler;
    public SimplifiedCompilerWrapper(JavaCompiler compiler){
        this.compiler = compiler;
    }
    @Override
    public ISimplifiedStandardJavaFileManager getSimplifiedStandardJavaFileManager(
            DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        return new SimplifiedStandardJavaFileManagerWrapper(compiler.getStandardFileManager(diagnosticListener, null, null));
    }

    @Override
    public ISimplifiedCompilationTask getSimplfiedCompilationTask(ISimplifiedStandardJavaFileManager fileManager,
            DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> options,
            Iterable<? extends JavaFileObject> compilationUnits) {
                //TODO: Find a way to fix the unused file manager parameter. I defined the interfaces in such a way that I can't actually pass ISimplifiedStandardJavaFileManager into the JavaCompiler,so I default to not using it and passing null. This is fine for now, but may cause problems in the future.
        return new SimplifiedCompilationTaskWrapper(compiler.getTask(null, null, diagnosticListener, options, null, compilationUnits));
    }

}