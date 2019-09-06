package fpt.verifiers.core.tools;

import java.nio.file.Path;

public interface ISimplifiedCompiler {
    void compileToDir(Path srcDir, Path outDir);
}