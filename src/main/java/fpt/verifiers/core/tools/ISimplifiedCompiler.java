package fpt.verifiers.core.tools;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

public interface ISimplifiedCompiler {
    ISimplifiedStandardJavaFileManager getSimplifiedStandardJavaFileManager(
            DiagnosticListener<? super JavaFileObject> diagnosticListener);

    ISimplifiedCompilationTask getSimplfiedCompilationTask(ISimplifiedStandardJavaFileManager fileManager,
            DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> options,
            Iterable<? extends JavaFileObject> compilationUnits);
}