package fpt.verifiers.core;

import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

public class FPTDiagnosticUtil{
    public static FPTDiagnostic[] fromDiagnosticCollector(DiagnosticCollector<? super JavaFileObject> collector){
        return collector.getDiagnostics().stream().map(FPTDiagnosticUtil::fromDiagnostic).toArray(FPTDiagnostic[]::new);
    }
    public static FPTDiagnostic.DiagnosticKind fromJavaDiagnosticKind(Kind kind){
        if(kind == Kind.ERROR){
            return FPTDiagnostic.DiagnosticKind.Error;
        }else if(kind == Kind.WARNING){
            return FPTDiagnostic.DiagnosticKind.Warning;
        }else{
            return FPTDiagnostic.DiagnosticKind.Info;
        }
    }
    public static FPTDiagnostic fromDiagnostic(Diagnostic<? extends Object> diagnostic){
            return new FPTDiagnostic(
                diagnostic.getMessage(null), 
                fromJavaDiagnosticKind(diagnostic.getKind()), 
                new FileLocation(diagnostic.getLineNumber(), diagnostic.getColumnNumber()),
                null);
    }
}