package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.ExceptionInfo;

/**
 * @author 22454
 */
public class AttributeInfoCode extends AttributeInfo {

    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private byte[] code;
    private int exceptionTableLength;
    private ExceptionInfo[] exceptionTable;
    private int attributeCount;
    private AttributeInfo[] attributes;
    private ConstantPool constantPool;

    public AttributeInfoCode(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }


    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        maxStack = byteCodeFile.readTwoUint();
        maxLocals = byteCodeFile.readTwoUint();
        codeLength = (int) byteCodeFile.readFourUint();
        code = byteCodeFile.getByteByCnt(codeLength);
        exceptionTableLength = byteCodeFile.readTwoUint();
        exceptionTable = new ExceptionInfo[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionInfo(
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint());
        }
        this.attributeCount = byteCodeFile.readTwoUint();
        this.attributes = AttributeInfo.readAttributes(byteCodeFile, attributeCount, constantPool);
//        MyLog.print(this.toString());
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public byte[] getCode() {
        return code;
    }

    public int getExceptionTableLength() {
        return exceptionTableLength;
    }

    public ExceptionInfo[] getExceptionTable() {
        return exceptionTable;
    }

    public int getAttributeCount() {
        return attributeCount;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public AttributeInfoLineNumberTable getLineNumberTableAttribute() {
        for (AttributeInfo attribute : this.attributes) {
            if (attribute instanceof AttributeInfoLineNumberTable) {
                return (AttributeInfoLineNumberTable) attribute;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Code: \n");
        builder.append("stack=").append(maxStack)
                .append(", locals=").append(maxLocals).append("\n")
                .append("Assembly Codes: \n");
        for (byte b : code) {
            try {
                builder.append("\t").append(b).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        builder.append("Attributes: \n");
        for (AttributeInfo attribute : attributes) {
            builder.append("\t").append(attribute).append("\n");
        }
        return builder.toString();

    }
}
