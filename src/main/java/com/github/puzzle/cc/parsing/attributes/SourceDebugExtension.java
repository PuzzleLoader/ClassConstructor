package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SourceDebugExtension extends AttributeInfo {

    byte[] debugExtension;

    public SourceDebugExtension(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        debugExtension = new byte[length];
        for (int i = 0; i < length; i++) {
            debugExtension[i] = inp.readByte();
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);
        for (byte b : debugExtension) outputStream.writeByte(b);
    }
}
