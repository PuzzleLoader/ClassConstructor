package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.parsing.attributes.stackmap.verification.VerificationInfo;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ChopFrame implements StackMapFrame {

    Pair<Integer, StackMapFrameType> type;
    int offsetDelta;

    public ChopFrame(Pair<Integer, StackMapFrameType> type, DataInputStream inp) throws IOException {
        this.type = type;
        offsetDelta = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(type.a);
        outputStream.writeShort(offsetDelta);
    }

    @Override
    public Pair<Integer, StackMapFrameType> getType() {
        return type;
    }

}
