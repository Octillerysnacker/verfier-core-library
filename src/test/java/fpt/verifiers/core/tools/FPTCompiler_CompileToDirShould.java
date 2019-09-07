package fpt.verifiers.core.tools;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import fpt.verifiers.core.io.MockJavaFileFinder;


public class FPTCompiler_CompileToDirShould {

    @ParameterizedTest
    @MethodSource("PassCorrectParameters_Data")
    public void PassCorrectParameters(
        String targetDir
        ){
            
        //Reference equality the shit out of everything so we don't need parameters  
        //Unfortunately I can't do this for targetDir since it is intended to be converted into a string parameter
        var targetDirPath = Paths.get(targetDir);
        Path srcDir = Paths.get("arbritrary");
        Iterable<JavaFileObject> getJavaFileObjects_return = Arrays.asList();
        var manager = new MockSimplifiedStandardJavaFileManager(getJavaFileObjects_return); 
        var task = new MockSimplifiedCompilationTask(false);
        var listener = new DiagnosticCollector<>();
        var mockCompiler = new MockSimplifiedCompiler(manager, task);
        Path[] foundJavaFiles = new Path[0];
        var finder = new MockJavaFileFinder(foundJavaFiles);
        var fptCompiler = new FPTCompiler(mockCompiler,listener, finder);

        fptCompiler.compileToDir(srcDir, targetDirPath);

        assertTrue(manager.getSuppliedPaths() == foundJavaFiles);
        assertTrue(mockCompiler.getListenerForGetManager() == listener);
        assertTrue(mockCompiler.getListenerForGetTask() == listener);
        assertTrue(mockCompiler.getManagerForGetTask() == manager);
        assertTrue(Arrays.equals( StreamSupport.stream(mockCompiler.getOptionsForGetTask().spliterator(), false).toArray(String[]::new), new String[]{"-d",targetDirPath.toString()}));
        assertTrue(mockCompiler.getUnitsForGetTask() == getJavaFileObjects_return);
        assertTrue(finder.getSuppliedSrc() == srcDir);
    }
    public static Stream<String> PassCorrectParameters_Data(){
        var dataSetCount = 4;
        var generator = new RandomStringGenerator.Builder().build();
        Stream<String> stream = Stream.empty();
        for(int i = 0;i<dataSetCount;i++){
            stream = Stream.concat(stream, Stream.of(generator.generate(1, 15)));
        }
        return stream;
    }
}