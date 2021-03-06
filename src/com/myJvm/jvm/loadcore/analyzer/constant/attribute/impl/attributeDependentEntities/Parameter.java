package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities;

/**
 * @author 22454
 */
public class Parameter {
    private int nameIndex;
    private int accessFlags;

    public Parameter(int nameIndex, int accessFlags) {
        this.nameIndex = nameIndex;
        this.accessFlags = accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getAccessFlags() {
        return accessFlags;
    }
}
