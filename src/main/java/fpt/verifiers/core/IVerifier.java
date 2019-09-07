package fpt.verifiers.core;

import java.nio.file.Path;

public interface IVerifier {
    public VerifierResult Verify(Path userDirectory, Path projectDirectory) throws Exception;
}