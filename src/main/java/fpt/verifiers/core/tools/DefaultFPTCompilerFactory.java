package fpt.verifiers.core.tools;

import javax.tools.DiagnosticCollector;
import javax.tools.ToolProvider;

import fpt.verifiers.core.io.DefaultJavaFileFinder;

public class DefaultFPTCompilerFactory implements IFPTCompilerFactory {

    @Override
    public IFPTCompiler CreateCompiler() {
        return new FPTCompiler(
            new SimplifiedCompilerWrapper(ToolProvider.getSystemJavaCompiler()),
            new DiagnosticCollector<>(), 
            new DefaultJavaFileFinder());
    }
    
}