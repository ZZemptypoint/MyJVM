package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.VerificationTypeInfo;
import log.MyLog;

/**
 * @author 22454
 */
public interface VariableInfo {

    /**
     * 获取标记值
     *
     * @return 标记
     */
    int getTag();

    /**
     * 根据 tag 值创建一个 VariableInfo
     *
     * @param tag          标记值
     * @param byteCodeFile 字节码文件
     * @return 变量信息实体
     * @throws Exception ex
     */
    static VariableInfo createVariableInfo(int tag, ByteCodeFile byteCodeFile) throws Exception {
        MyLog.debug("Variable Info Tag : " + tag);
        switch (tag) {
            case VariableItemType.ITEM_TOP:
                return new VariableInfoTop();
            case VariableItemType.ITEM_INTEGER:
                return new VariableInfoInteger();
            case VariableItemType.ITEM_FLOAT:
                return new VariableInfoFloat();
            case VariableItemType.ITEM_DOUBLE:
                return new VariableInfoDouble();
            case VariableItemType.ITEM_LONG:
                return new VariableInfoLong();
            case VariableItemType.ITEM_NULL:
                return new VariableInfoNull();
            case VariableItemType.ITEM_UNINITIALIZED_THIS:
                return new VariableInfoUnInitializedThis();
            case VariableItemType.ITEM_OBJECT:
                int cPoolIndex = byteCodeFile.readTwoUint();
                return new VariableInfoObject(cPoolIndex);
            case VariableItemType.ITEM_UNINITIALIZED:
                int offset = byteCodeFile.readTwoUint();
                return new VariableInfoUninitialized(offset);
            default:
//                return new VariableInfoUnparsed();
                throw new Exception("Create Variable Info Fail");

        }
    }
}
