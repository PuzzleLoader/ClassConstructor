package com.github.puzzle.cc.parsing.attributes.stackmap.verification;

import java.io.DataInputStream;
import java.io.IOException;

public class IntegerVariableInfo implements VerificationInfo {

    VerificationType type;

    public IntegerVariableInfo(VerificationType type, DataInputStream inp) throws IOException {
        this.type = type;
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
