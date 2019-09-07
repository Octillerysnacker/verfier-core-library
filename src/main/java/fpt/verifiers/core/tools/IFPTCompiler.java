package fpt.verifiers.core.tools;

import java.io.IOException;
import java.nio.file.Path;

import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

public interface IFPTCompiler {
    public DiagnosticListener<? super JavaFileObject> getDiagnostics();
    public boolean compileToDir(Path srcDir, Path targetDir) throws IOException;
}