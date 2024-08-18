package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SameFrame implements StackMapFrame {

    Pair<Integer, StackMapFrameType> type;

    public SameFrame(Pair<Integer, StackMapFrameType> type, DataInputStream inp) throws IOException {
        this.type = type;
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.write(type.a);
    }

    @Override
    public Pair<Integer, StackMapFrameType> getType() {
        return type;
    }

}
