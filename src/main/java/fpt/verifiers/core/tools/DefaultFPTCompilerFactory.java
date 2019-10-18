package fpt.verifiers.core.tools;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import fpt.verifiers.core.io.DefaultJavaFileFinder;

public class DefaultFPTCompilerFactory implements IFPTCompilerFactory {
    private DiagnosticCollector<? super JavaFileObject> collector;
    public DiagnosticCollector<? super JavaFileObject> getDefaultDiagnosticCollector(){
        return collector;
    }
    @Override
    public IFPTCompiler CreateCompiler() {
        collector = new DiagnosticCollector<>();
        return new FPTCompiler(
            new SimplifiedCompilerWrapper(ToolProvider.getSystemJavaCompiler()),
            collector, 
            new DefaultJavaFileFinder());
    }
    
}