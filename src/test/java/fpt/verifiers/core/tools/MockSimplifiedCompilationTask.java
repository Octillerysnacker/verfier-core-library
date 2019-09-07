package fpt.verifiers.core.tools;

public class MockSimplifiedCompilationTask implements ISimplifiedCompilationTask{

    private boolean _hasBeenCalled;
    private boolean callResult;
    public boolean hasBeenCalled(){
        return _hasBeenCalled;
    }
    public MockSimplifiedCompilationTask(boolean callResult){
        this.callResult = callResult;
    }
    @Override
    public Boolean call() {
        _hasBeenCalled = true;
        return callResult;
    }

}