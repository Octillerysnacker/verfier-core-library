package fpt.verifiers.core;

public class VerifierResult {
    public final boolean Success;
    public final FPTDiagnostic[] Diagnostics;
    public VerifierResult(boolean success, FPTDiagnostic[] diagnostics){
        Success = success;
        Diagnostics = diagnostics;
    }
    public VerifierResult(boolean success){
        this(success, new FPTDiagnostic[0]);
    }
}