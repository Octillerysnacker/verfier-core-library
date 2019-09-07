package fpt.verifiers.core.tools;

import java.nio.file.Path;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

import fpt.verifiers.core.io.IJavaFileFinder;

public class FPTCompiler {

	public FPTCompiler(ISimplifiedCompiler mockCompiler, DiagnosticListener<? super JavaFileObject> listener, IJavaFileFinder finder) {
    }
    public void compileToDir(Path srcDir, Path targetDir){
        
    }
    
}