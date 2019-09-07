package fpt.verifiers.core.tools;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

public class MockSimplifiedCompiler implements ISimplifiedCompiler {

    private ISimplifiedStandardJavaFileManager manager;
    private ISimplifiedCompilationTask task;

    private DiagnosticListener<? super JavaFileObject> getManager_listener;

    private ISimplifiedStandardJavaFileManager getTask_manager;
    private DiagnosticListener<? super JavaFileObject> getTask_listener;
    private Iterable<String> getTask_options;
    private Iterable<? extends JavaFileObject> getTask_units;

    public MockSimplifiedCompiler(ISimplifiedStandardJavaFileManager manager, ISimplifiedCompilationTask task) {
        this.manager = manager;
        this.task = task;
    }

    public DiagnosticListener<? super JavaFileObject> getListenerForGetManager() {
        return getManager_listener;
    }

    public ISimplifiedStandardJavaFileManager getManagerForGetTask() {
        return getTask_manager;
    }

    public DiagnosticListener<? super JavaFileObject> getListenerForGetTask() {
        return getTask_listener;
    }

    public Iterable<String> getOptionsForGetTask() {
        return getTask_options;
    }

    public Iterable<? extends JavaFileObject> getUnitsForGetTask() {
        return getTask_units;
    }

    @Override
    public ISimplifiedStandardJavaFileManager getSimplifiedStandardJavaFileManager(
            DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        getManager_listener = diagnosticListener;
        return manager;
    }

    @Override
    public ISimplifiedCompilationTask getSimplfiedCompilationTask(ISimplifiedStandardJavaFileManager fileManager,
            DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> options,
            Iterable<? extends JavaFileObject> compilationUnits) {
        getTask_manager = fileManager;
        getTask_listener = diagnosticListener;
        getTask_options = options;
        getTask_units = compilationUnits;
        return task;
    }

}