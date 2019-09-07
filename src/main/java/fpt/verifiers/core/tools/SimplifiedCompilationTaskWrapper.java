package fpt.verifiers.core.tools;

import javax.tools.JavaCompiler.CompilationTask;

public class SimplifiedCompilationTaskWrapper implements ISimplifiedCompilationTask {

    private CompilationTask task;

    public SimplifiedCompilationTaskWrapper(CompilationTask task){
        this.task = task;
    }
    @Override
    public Boolean call() {
        return task.call();
    }

}