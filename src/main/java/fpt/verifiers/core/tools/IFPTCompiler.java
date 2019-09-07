package fpt.verifiers.core.tools;

import java.io.IOException;
import java.nio.file.Path;

public interface IFPTCompiler {
    public boolean compileToDir(Path srcDir, Path targetDir) throws IOException;
}