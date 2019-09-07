package fpt.verifiers.core.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import fpt.verifiers.core.io.MockJavaFileFinder;

public class FPTCompiler_CompileToDirShould {

    @ParameterizedTest
    @MethodSource("PassCorrectParameters_Data")
    public void PassCorrectParameters(String targetDir) throws IOException {

        // Reference equality the shit out of everything so we don't need parameters
        // Unfortunately I can't do this for targetDir since it is intended to be
        // converted into a string parameter
        var targetDirPath = Paths.get(targetDir);
        Path srcDir = Paths.get("arbritrary");
        Iterable<JavaFileObject> getJavaFileObjects_return = Arrays.asList();
        var manager = new MockSimplifiedStandardJavaFileManager(getJavaFileObjects_return);
        var task = new MockSimplifiedCompilationTask(false);
        var listener = new DiagnosticCollector<>();
        var mockCompiler = new MockSimplifiedCompiler(manager, task);
        Path[] foundJavaFiles = new Path[0];
        var finder = new MockJavaFileFinder(foundJavaFiles);
        var fptCompiler = new FPTCompiler(mockCompiler, listener, finder);

        fptCompiler.compileToDir(srcDir, targetDirPath);

        assertTrue(manager.getSuppliedPaths() == foundJavaFiles);
        assertTrue(mockCompiler.getListenerForGetManager() == listener);
        assertTrue(mockCompiler.getListenerForGetTask() == listener);
        assertTrue(mockCompiler.getManagerForGetTask() == manager);
        var result = StreamSupport.stream(mockCompiler.getOptionsForGetTask().spliterator(), false)
                .toArray(String[]::new);
        assertTrue(Arrays.equals(result, new String[] { "-d", targetDirPath.toString() }));
        assertTrue(mockCompiler.getUnitsForGetTask() == getJavaFileObjects_return);
        assertTrue(finder.getSuppliedSrc() == srcDir);
    }

    public static Stream<String> PassCorrectParameters_Data() {
        var dataSetCount = 4;
        var generator = new RandomStringGenerator.Builder().build();
        Stream<String> stream = Stream.empty();
        for (int i = 0; i < dataSetCount; i++) {
            stream = Stream.concat(stream, Stream.of(generator.generate(1, 15)));
        }
        return stream;
    }

    @Test
    public void CallCreatedCompilationTask() throws IOException {
        var task = new MockSimplifiedCompilationTask(true);
        var fptCompiler = new FPTCompiler(
            new MockSimplifiedCompiler(
                new MockSimplifiedStandardJavaFileManager(null), 
                task), 
                null, 
                new MockJavaFileFinder(null));
        
        fptCompiler.compileToDir(null, Paths.get(""));

        assertTrue(task.hasBeenCalled());
    }
    @Test
    public void ClosesFileManager() throws IOException {
        var manager = new MockSimplifiedStandardJavaFileManager(null);
        var fptCompiler = new FPTCompiler(
            new MockSimplifiedCompiler(
                manager,
                new MockSimplifiedCompilationTask(true)
            ), 
            null, 
            new MockJavaFileFinder(null));
        
        fptCompiler.compileToDir(null, Paths.get(""));

        assertTrue(manager.hasBeenClosed());
    }
    @ParameterizedTest
    @ValueSource(booleans = {true,false})
    public void ReturnsCompilationTaskResult(boolean callResult) throws IOException {
        var fptCompiler = new FPTCompiler(
            new MockSimplifiedCompiler(
                new MockSimplifiedStandardJavaFileManager(null), 
                new MockSimplifiedCompilationTask(callResult)),
                null, 
                new MockJavaFileFinder(null));

        var result = fptCompiler.compileToDir(null, Paths.get(""));
        
        assertEquals(callResult, result);
    }
}