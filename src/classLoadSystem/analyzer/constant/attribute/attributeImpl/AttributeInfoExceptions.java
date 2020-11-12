package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoExceptions extends AttributeInfo {
    private int numberOfExceptions;
    private int[] exceptionIndexTable;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.numberOfExceptions = byteCodeFile.readTwoUint();
        this.exceptionIndexTable = new int[this.numberOfExceptions];
        for (int i = 0; i < numberOfExceptions; i++) {
            exceptionIndexTable[i] = byteCodeFile.readTwoUint();
        }
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\tNumber Of Exceptions: ").append(numberOfExceptions).append("\n")
                .append("\tExceptions:\n");
        for (int index : exceptionIndexTable) {
            try {
                builder.append("\t").append(index).append("   --->   < ").append(constantPool.getUtf8(index)).append(" >");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        builder.append("\n");
        return builder.toString();
    }
}
