package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.access.AccessFlag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodParametersAttribute extends AttributeInfo {

    MethodParameter[] parameters;

    public MethodParametersAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        parameters = new MethodParameter[inp.readUnsignedShort()];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = new MethodParameter(inp);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(parameters.length);
        for (MethodParameter parameter : parameters) {
            parameter.writeToStream(outputStream);
        }
    }

    public static class MethodParameter {

        int nameIndex;
        AccessFlag[] accessFlags;

        public MethodParameter(DataInputStream inp) throws IOException {
            nameIndex = inp.readUnsignedShort();
            accessFlags = MethodParameterAccessFlag.getFromFlags(inp.readUnsignedShort());
        }

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeShort(nameIndex);
            int accFlags = 0;
            for (AccessFlag flag : accessFlags) accFlags |= flag.getMask();
            outputStream.writeShort(accFlags);
        }

    }

    public enum MethodParameterAccessFlag implements AccessFlag {

        ACC_FINAL(0x0010),
        ACC_SYNTHETIC(0x1000),
        ACC_MANDATED(0x8000);

        final int flag;

        MethodParameterAccessFlag(int num) {
            this.flag = (byte) num;
            init(this, (byte) num);
        }


        static Map<Integer, MethodParameterAccessFlag> intTagTypeMap;

        static void init(MethodParameterAccessFlag flag, int num) {
            if (intTagTypeMap == null)
                intTagTypeMap = new HashMap<>();
            intTagTypeMap.put(num, flag);
        }

        public static AccessFlag[] getFromFlags(int u2) {
            List<AccessFlag> accessFlags = new ArrayList<>();
            AccessFlag[] values = MethodParameterAccessFlag.values();
            for (int i = values.length - 1; i >= 0; i--) {
                if ((values[i].getMask() & u2) == values[i].getMask()) accessFlags.add(values[i]);
            }
            return accessFlags.toArray(new AccessFlag[0]);
        }
        public static MethodParameterAccessFlag fromU2(int u2) {
            return intTagTypeMap.getOrDefault(u2, ACC_MANDATED);
        }

        @Override
        public int getMask() {
            return flag;
        }
    }
    
}
