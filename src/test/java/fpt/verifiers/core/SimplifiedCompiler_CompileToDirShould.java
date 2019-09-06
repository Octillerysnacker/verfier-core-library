package fpt.verifiers.core;

import java.nio.file.Paths;

import javax.tools.StandardJavaFileManager;

import org.junit.jupiter.api.Test;

public class SimplifiedCompiler_CompileToDirShould{

    @Test
    public void PassCorrectParameters(){
        var manager = new MockStandardJavaFileManager();
        var mockCompiler = new MockJavaCompiler();
        var simplifiedCompiler = new SimplifiedCompiler();
        StandardJavaFileManager m = null;
        m.getJavaFileObjectsFromPaths(Paths.get("first", "ore"));
        manager.get

    }
}